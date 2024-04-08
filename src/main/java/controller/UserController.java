package controller;

import bean.User;
import service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Mapping for displaying the user creation form
    @GetMapping("/createUser")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User()); // Add an empty user object to the model
        return "createUser"; // Return the view name for the user creation form
    }

    // Mapping for processing the user creation form submission
    @PostMapping("/createUser")
    public String createUser(User user, RedirectAttributes redirectAttributes) {
        // Call the userService to save the user
        userService.createUser(user);

        // Add a success message to the redirect attributes
        redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");

        // Redirect to a success page or another URL
        return "redirect:/success"; // Redirect to a success page
    }
}
