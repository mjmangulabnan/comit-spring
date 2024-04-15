package controller;

import java.util.List;
import java.util.Optional;

import bean.Task;
import service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TaskService taskService;
	
	//@GetMapping("/")
	//String index() {
		
	//	return "index";
	//}
	
	@GetMapping("/tasklist")
	ModelAndView listTasks() {
		
		List<Task> tasks = this.taskService.listTasks();
		
		return new ModelAndView("tasklist","tasks",tasks);
	}

	@GetMapping("/taskcreate")
	String showCreate(Task task) {
		
		return "taskcreate";
	}

	@PostMapping("/taskcreate")
	String createTask(Task task, BindingResult binding) {
		
		this.logger.debug("Creating Task, {}", task.toString());
		
		//this.validateUsername(user, binding);
		
		if (binding.hasErrors()) {
			return "taskcreate";
		}
		
		this.taskService.createTask(task);
		
		return "redirect:/tasklist";
	}

	@PostMapping("/taskupdate")
	String updateTask(Task task, BindingResult binding) {
		
		this.logger.debug("Updating Task, {}", task.toString());
		
		//this.validateUsername(user, binding);
		
		if (binding.hasErrors()) {
			return "taskupdate";
		}
		
		this.taskService.updateTask(task);
		
		return "redirect:/tasklist";
	}
	
	@GetMapping("/taskupdate/{taskId}")
	ModelAndView showUpdate(@PathVariable int taskId) {
		
		Task task = taskService.findTask(taskId);
		
		return new ModelAndView("taskupdate","task",task);
	}

	private void validateTaskName(Task task, BindingResult binding) {
		
		/*
		if (this.userService.findUser(user) != null) {
			binding.addError(new FieldError("user","username",user.getUsername(), 
					 false, null, null, "* Username already taken."));
		}*/

		if (Optional.ofNullable(this.taskService.findTask(task)).isPresent()) {
			binding.addError(new FieldError("task","taskName",task.getTaskName(), 
					false, null, null, "* Task already created."));
		}
	}
}
