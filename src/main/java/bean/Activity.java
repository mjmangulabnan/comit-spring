package bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime; // Changed import
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Activity {

    int activityId;
    int taskId; // Added taskId field
    String location;
    String notes;
    String activityStatus;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateCreated;
    LocalDate dateUpdated;
    
    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime timeStart;
    LocalTime timeEnd;
    
    BigDecimal totalHours;
    
    String username;
    
    // New field for storing the user ID
    int userId;
    
    // Getter and Setter for activityId
    public int getActivityId() {
        return activityId;
    }
    
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
    
    // Getter and Setter for taskId
    public int getTaskId() {
        return taskId;
    }
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
    // Getter and Setter for location
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    // Getter and Setter for notes
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    // Getter and Setter for activityStatus
    public String getActivityStatus() {
        return activityStatus;
    }
    
    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }
    
    // Getter and Setter for dateCreated
    public LocalDate getDateCreated() {
        return dateCreated;
    }
    
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    // Getter and Setter for timeStart
    public LocalTime getTimeStart() {
        return timeStart;
    }
    
    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }
    
    // Getter and Setter for timeEnd
    public LocalTime getTimeEnd() {
        return timeEnd;
    }
    
    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }
    
    // Getter and Setter for totalHours
    public BigDecimal getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(BigDecimal totalHours) {
        this.totalHours = totalHours;
    }
    
    // Getter and Setter for dateUpdated
    public LocalDate getDateUpdated() {
        return dateUpdated;
    }
    
    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    
    // Getter and Setter for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    // Getter and Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Activity [activityId=%s, taskId=%s, location=%s, notes=%s, activityStatus=%s, dateCreated=%s, "
                + "timeStart=%s, timeEnd=%s, userId=%s, dateUpdated=%s]"
                , activityId, taskId, location, notes, activityStatus, dateCreated, timeStart, timeEnd, userId, dateUpdated);
    }   
}
