package service;

import java.util.List;

import bean.Task;
import dao.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	TaskDao taskDao;
	
	
	public List<Task> listTasks() {
		
		List<Task> tasks = this.taskDao.listTasks();
		
		tasks.forEach(System.out::println);
		/*
		 * Apply some business logic to the users list
		 */
		
		return tasks;
	}
	
	public void createTask(Task task){
		
		this.validateTask(task);
		
		this.taskDao.createTask(task);
	}

	public void updateTask(Task task){
		
		this.validateTask(task);
		
		this.taskDao.updateTask(task);
	}

	public Task findTask(Task task){
		
		return this.taskDao.findTask(task);
	}

	public Task findTask(int taskId){
		
		return this.taskDao.findTask(taskId);
	}
	
	private void validateTask(Task task) {
		
		if (task.getTaskName().isEmpty() || 
			 task.getTaskType().isEmpty() ||
			 task.getClientName().isEmpty()) {
			
			throw new RuntimeException("Invalid Task Data: " + task);
		}
			
	}
}
