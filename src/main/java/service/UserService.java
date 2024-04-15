package service;

import java.util.List;

import bean.User;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	
	public List<User> listUsers() {
		
		List<User> users = this.userDao.listUsers();
		
		users.forEach(System.out::println);
		/*
		 * Apply some business logic to the users list
		 */
		
		return users;
	}
	
	public void createUser(User user){
		
		this.validateUser(user);
		
		this.userDao.createUser(user);
	}

	public void updateUser(User user){
		
		this.validateUser(user);
		
		this.userDao.updateUser(user);
	}

	public User findUser(User user){
		
		return this.userDao.findUser(user);
	}

	public User findUser(int userId){
		
		return this.userDao.findUser(userId);
	}
	
	private void validateUser(User user) {
		
		if (user.getFirstName().isEmpty() || 
			 user.getLastName().isEmpty() ||
			 user.getUsername().isEmpty()) {
			
			throw new RuntimeException("Invalid User Data: " + user);
		}
			
	}
}
