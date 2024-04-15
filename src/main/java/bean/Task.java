package bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Task {

	int taskId;
	String taskName;
	String taskType;
	String clientName;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Override
	public String toString() {
		return String.format(
				"Task [taskId=%s, taskName=%s, taskType=%s, clientName=%s]", taskId,
				taskName, taskType, clientName);
	}
	
}