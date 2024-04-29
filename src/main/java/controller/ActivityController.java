package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import bean.Activity;
import bean.Task;
import service.ActivityService;
import service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import dao.UserDao;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Validated
@Controller
public class ActivityController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ActivityService activityService;

    @Autowired
    TaskService taskService;
    
    @Autowired
    UserDao userDao;

    @GetMapping("/activitylist")
    ModelAndView listActivity() {
        List<Activity> activity = activityService.listActivity();
        return new ModelAndView("activitylist", "activity", activity);
    }

    @GetMapping("/activitycreate")
    String showCreate(Activity activity, Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("activity", activity);
        model.addAttribute("tasks", tasks);
        return "activitycreate";
    }

    @PostMapping("/activitycreate")
    String createActivity(@ModelAttribute("activity") Activity activity, BindingResult binding) {
        logger.debug("Creating Activity, {}", activity.toString());

        if (binding.hasErrors()) {
            return "activitycreate";
        }

        // Set the task ID based on the selected task
        Task selectedTask = taskService.getTaskById(activity.getTaskId());
        if (selectedTask == null) {
            binding.addError(new FieldError("activity", "taskId", "Invalid task selected"));
            return "activitycreate";
        }
        activity.setTaskId(selectedTask.getTaskId());

        // Retrieve the current user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName(); // Assuming the username is stored in the name field

        // Retrieve the userId based on the username
        int userId = userDao.findUserIdByUsername(currentUsername);

        if (userId == -1) {
            // Handle case where username does not exist
            // For example, return an error message or redirect to an error page
            return "error";
        }

        // Set the retrieved userId and username in the activity
        activity.setUserId(userId);
        activity.setUsername(currentUsername);

        // Save the activity
        activityService.createActivity(activity);

        return "redirect:/activitylist";
    }

    @PostMapping("/activityupdate")
    String updateActivity(@ModelAttribute("activity") @Validated Activity activity, BindingResult binding) {
        logger.debug("Updating Activity, {}", activity.toString());

        if (binding.hasErrors()) {
            // If there are validation errors, return the update form with errors
            return "activityupdateErrors"; // Adjust this to match the actual name of your error view
        }

        // Set the dateUpdated field to the current date
        activity.setDateUpdated(LocalDate.now());

        // Update the activity in the service layer
        activityService.updateActivity(activity);

        return "redirect:/activitylist";
    }


    @GetMapping("/activityupdate/{activityId}")
    ModelAndView showUpdate(@PathVariable int activityId) {
        Activity activity = activityService.findActivity(activityId);
        ModelAndView modelAndView = new ModelAndView("activityupdate");
        modelAndView.addObject("activity", activity);
        return modelAndView;
    }

    private void validateLocation(Activity activity, BindingResult binding) {
        if (Optional.ofNullable(activityService.findActivity(activity)).isPresent()) {
            binding.addError(new FieldError("activity", "location", activity.getLocation(),
                    false, null, null, "* Activity already created."));
        }
    }
    
    @DeleteMapping("/activitydelete/{activityId}")
    public String deleteActivity(@PathVariable int activityId) {
        activityService.deleteActivity(activityId);
        return "redirect:/activitylist";
    }

}

