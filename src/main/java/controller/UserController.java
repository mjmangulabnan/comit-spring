package controller;

import java.util.List;
import java.util.Optional;

import bean.User;
import service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @GetMapping("/")
    String index() {

        return "index";
    }

    @GetMapping("/fragments") 
    public String greetUser(Model model) {
        // Retrieve the logged-in user's username from the UserService
        String username = userService.getLoggedInUsername(); // Implement this method in your UserService

        // Add the username as a model attribute
        model.addAttribute("username", username);

        // Other logic if needed
        model.addAttribute("option", "index");

        // Return the view name
        return "fragments";
    }

    @GetMapping("/list")
    ModelAndView listUsers() {

        List<User> users = this.userService.listUsers();

        return new ModelAndView("list", "users", users);
    }

    @GetMapping("/create")
    String showCreate(User user) {

        return "create";
    }

    @PostMapping("/create")
    String createUser(User user, BindingResult binding) {

        this.logger.debug("Creating User, {}", user.toString());

        this.validateUsername(user, binding);

        if (binding.hasErrors()) {
            return "create";
        }

        this.userService.createUser(user);

        return "redirect:/list";
    }

    @PostMapping("/update")
    String updateUser(User user, BindingResult binding) {

        this.logger.debug("Updating User, {}", user.toString());

        this.validateUsername(user, binding);

        if (binding.hasErrors()) {
            return "update";
        }

        this.userService.updateUser(user);

        return "redirect:/list";
    }

    @GetMapping("/update/{userId}")
    ModelAndView showUpdate(@PathVariable int userId) {

        User user = userService.findUser(userId);

        return new ModelAndView("update", "user", user);
    }

    private void validateUsername(User user, BindingResult binding) {

        if (Optional.ofNullable(this.userService.findUser(user)).isPresent()) {
            binding.addError(new FieldError("user", "username", user.getUsername(),
                    false, null, null, "* Username already taken."));
        }
    }
}