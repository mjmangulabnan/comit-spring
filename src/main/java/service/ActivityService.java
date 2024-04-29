package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import bean.Activity;
import dao.ActivityDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.format.annotation.DateTimeFormat;

@Service
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private UserDao userDao;

    public List<Activity> listActivity() {
        List<Activity> activities = activityDao.listActivity();
        for (Activity activity : activities) {
            // Fetch the username based on the userId and set it in the activity object
            String username = userDao.findUsernameByUserId(activity.getUserId());
            activity.setUsername(username);
        }
        return activities;
    }
    
    public void createActivity(Activity activity){
        // Calculate total hours
        BigDecimal totalHours = calculateTotalHours(activity.getTimeStart(), activity.getTimeEnd());
        activity.setTotalHours(totalHours);
        
        this.validateActivity(activity);
        
        this.activityDao.createActivity(activity);
    }
    
    // Method to calculate total hours
    private BigDecimal calculateTotalHours(LocalTime timeStart, LocalTime timeEnd) {
        Duration duration = Duration.between(timeStart, timeEnd);
        long seconds = duration.getSeconds();
        BigDecimal totalHours = BigDecimal.valueOf(seconds)
                                          .divide(BigDecimal.valueOf(3600), 2, RoundingMode.HALF_UP)
                                          .multiply(BigDecimal.valueOf(2))
                                          .setScale(0, RoundingMode.HALF_UP)
                                          .divide(BigDecimal.valueOf(2), 1, RoundingMode.HALF_UP);
        return totalHours;
    }

    public void updateActivity(Activity activity) {
        // Recalculate total hours based on the updated time
        BigDecimal totalHours = calculateTotalHours(activity.getTimeStart(), activity.getTimeEnd());
        activity.setTotalHours(totalHours);

        // Set the dateUpdated field to the current date
        activity.setDateUpdated(LocalDate.now());

        // Validate activity and update in the DAO
        validateActivity(activity);
        activityDao.updateActivity(activity);
    }

    public Activity findActivity(Activity activity) {
        return activityDao.findActivity(activity);
    }

    public Activity findActivity(int activityId) {
        return activityDao.findActivity(activityId);
    }

    private void validateActivity(Activity activity) {
        if (activity.getLocation().isEmpty() ||
                activity.getNotes().isEmpty() ||
                activity.getActivityStatus().isEmpty()) {
            throw new RuntimeException("Invalid Activity Data: " + activity);
        }
    }
    
    public void deleteActivity(int activityId) {
        activityDao.deleteActivity(activityId);
    }
}
