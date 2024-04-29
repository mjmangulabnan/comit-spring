package dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import bean.Activity;
import dao.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Activity> listActivity() {
        String sql = "SELECT * FROM ACTIVITY";
        return this.jdbcTemplate.query(sql, new ActivityMapper());
    }

    public void createActivity(Activity activity) {
        String sql = "INSERT INTO ACTIVITY (LOCATION, NOTES, ACTIVITY_STATUS, DATE_CREATED, TIME_START, TIME_END, DATE_UPDATED, TASK_TASK_ID, USER_USER_ID, USERNAME, TOTAL_HOURS) " 
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Calculate total hours
        BigDecimal totalHours = calculateTotalHours(activity.getTimeStart(), activity.getTimeEnd());
        
        this.jdbcTemplate.update(sql, 
            activity.getLocation(), 
            activity.getNotes(), 
            activity.getActivityStatus(), 
            activity.getDateCreated(), 
            activity.getTimeStart(), 
            activity.getTimeEnd(), 
            activity.getDateUpdated(),
            activity.getTaskId(),
            activity.getUserId(),
            activity.getUsername(),
            totalHours); // Include the totalHours field in the SQL query
    }


    private BigDecimal calculateTotalHours(LocalTime timeStart, LocalTime timeEnd) {
        long seconds = Duration.between(timeStart, timeEnd).getSeconds();
        BigDecimal totalHours = BigDecimal.valueOf(seconds).divide(BigDecimal.valueOf(3600), 2, RoundingMode.HALF_UP);
        return totalHours;
    }

    public Activity findActivity(Activity activity) {
        String sql = "SELECT * FROM ACTIVITY WHERE ACTIVITY_ID != ? AND UPPER(LOCATION) = UPPER(?)";
        return DataAccessUtils.singleResult(this.jdbcTemplate.query(sql, new ActivityMapper(), 
                                                   activity.getActivityId(), activity.getLocation()));
    }

    public Activity findActivity(int activityId) {
        String sql = "SELECT * FROM ACTIVITY WHERE ACTIVITY_ID = ?";
        return DataAccessUtils.singleResult(this.jdbcTemplate.query(sql, new ActivityMapper(), activityId));
    }
    

    public void updateActivity(Activity activity) {
        // Calculate total hours
        BigDecimal totalHours = calculateTotalHours(activity.getTimeStart(), activity.getTimeEnd());
        activity.setTotalHours(totalHours);

        String sql = "UPDATE ACTIVITY SET LOCATION = ?, NOTES = ?, ACTIVITY_STATUS = ?, DATE_CREATED = ?, "
                   + "TIME_START = ?, TIME_END = ?, DATE_UPDATED = ?, TOTAL_HOURS = ? WHERE ACTIVITY_ID = ?";

        // Update the DATE_UPDATED and TOTAL_HOURS fields
        this.jdbcTemplate.update(sql, activity.getLocation(), activity.getNotes(),
                activity.getActivityStatus(), activity.getDateCreated(), activity.getTimeStart(),
                activity.getTimeEnd(), LocalDateTime.now(), activity.getTotalHours(), activity.getActivityId());
    }

    public List<Activity> findActivitiesBetweenDates(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM ACTIVITY WHERE DATE_CREATED BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[] { startDate, endDate }, new ActivityMapper());
    }
    
    public void deleteActivity(int activityId) {
        String sql = "DELETE FROM ACTIVITY WHERE ACTIVITY_ID = ?";
        jdbcTemplate.update(sql, activityId);
    }
}

