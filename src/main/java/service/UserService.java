package service;

import java.util.List;

import bean.User;
import dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public List<User> listUsers() {
        List<User> users = this.userDao.listUsers();
        logger.debug("Retrieved {} users", users.size());
        return users;
    }

    public void createUser(User user) {
        validateUser(user);
        userDao.createUser(user);
        logger.info("Created user: {}", user.getUsername());
    }

    public void updateUser(User user) {
        validateUser(user);
        userDao.updateUser(user);
        logger.info("Updated user: {}", user.getUsername());
    }

    public User findUser(User user) {
        return userDao.findUser(user);
    }

    public User findUser(int userId) {
        return userDao.findUser(userId);
    }

    private void validateUser(User user) {
        if (StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName())
                || StringUtils.isEmpty(user.getUsername())) {
            throw new IllegalArgumentException("Invalid User Data: " + user);
        }
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return null;
    }
}
