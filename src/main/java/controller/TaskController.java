package controller;


import bean.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    @GetMapping("/createTaskForm")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "createTaskForm";
    }

    @PostMapping("/createTask")
    public String createTask(Task task, RedirectAttributes redirectAttributes) {
        // Here you can save the task to a list or perform other operations
        redirectAttributes.addFlashAttribute("successMessage", "Task created successfully!");
        return "redirect:/success";
    }
}
