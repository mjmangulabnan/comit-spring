package service;

import org.springframework.stereotype.Service;

import bean.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    // Method to create a new user
    public void createUser(User user) {
        users.add(user); // Add the user to the list of users
    }

    // Other methods for user-related operations can be added here as needed
}