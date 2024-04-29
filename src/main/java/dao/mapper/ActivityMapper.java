package dao.mapper;

import bean.Activity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.time.LocalDate;

public class ActivityMapper implements RowMapper<Activity> {

    @Override
    public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Activity activity = new Activity();

        activity.setActivityId(rs.getInt("ACTIVITY_ID"));
        activity.setLocation(rs.getString("LOCATION"));
        activity.setNotes(rs.getString("NOTES"));
        activity.setActivityStatus(rs.getString("ACTIVITY_STATUS"));

        // Handling nullable date
        Date dateCreated = rs.getDate("DATE_CREATED");
        activity.setDateCreated(dateCreated != null ? ((java.sql.Date) dateCreated).toLocalDate() : null);

        // Convert java.sql.Time to LocalTime
        Time timeStart = rs.getTime("TIME_START");
        activity.setTimeStart(timeStart != null ? timeStart.toLocalTime() : null);

        Time timeEnd = rs.getTime("TIME_END");
        activity.setTimeEnd(timeEnd != null ? timeEnd.toLocalTime() : null);

        Date dateUpdated = rs.getDate("DATE_UPDATED");
        activity.setDateUpdated(dateUpdated != null ? ((java.sql.Date) dateUpdated).toLocalDate() : null);

        activity.setTaskId(rs.getInt("TASK_TASK_ID"));
        activity.setUserId(rs.getInt("USER_USER_ID"));
        activity.setUsername(rs.getString("USERNAME"));
        activity.setTotalHours(rs.getBigDecimal("TOTAL_HOURS"));

        return activity;
    }

}
