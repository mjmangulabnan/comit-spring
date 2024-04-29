package dao;

import java.util.List;

import bean.User;
import dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<User> listUsers(){
	
		String sql = "SELECT * FROM USER";
		
		return this.jdbcTemplate.query(sql, new UserMapper());
	}

	public void createUser(User user){
		
		String sql = "INSERT INTO USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ROLE, STATUS) " 
	               + "VALUES(?,?,?,?,?,?,?)";
		
		this.jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getFirstName(), 
				user.getLastName(), user.getEmail(), user.getRole(), "A");
	}

	public User findUser(User user){
		
		String sql = "SELECT * FROM USER WHERE USER_ID != ? AND UPPER(USERNAME) = UPPER(?)";
		
		return DataAccessUtils.singleResult(this.jdbcTemplate.query(sql, new UserMapper(), 
				                   user.getUserId(), user.getUsername()));
	}

	public User findUser(int userId){
		
		String sql = "SELECT * FROM USER WHERE USER_ID = ?";
		
		return DataAccessUtils.singleResult(this.jdbcTemplate.query(sql, new UserMapper(),userId));
	}
	
	public User findUser(String username){
		
		String sql = "SELECT * FROM USER WHERE UPPER(USERNAME) = UPPER(?)";
		
		return DataAccessUtils.singleResult(this.jdbcTemplate.query(sql, new UserMapper(),username));
	}
	
	public void updateUser(User user){
		
		String sql = "UPDATE USER SET USERNAME = ?, FIRST_NAME = ?, LAST_NAME = ?" 
			       + ", EMAIL = ?, ROLE = ? WHERE USER_ID = ?";
		
		this.jdbcTemplate.update(sql, user.getUsername(), user.getFirstName(), 
				user.getLastName(), user.getEmail(), user.getRole(), user.getUserId());
	}
	
	public int findUserIdByUsername(String username) {
	    String sql = "SELECT USER_ID FROM USER WHERE USERNAME = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, Integer.class, username);
	    } catch (EmptyResultDataAccessException e) {
	        // Handle case where username does not exist
	        return -1;
	    }
	}

	//public String findUsernameByUserId(int userId);
	

	public String findUsernameByUserId(int userId) {
	    String sql = "SELECT USERNAME FROM USER WHERE USER_ID = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, String.class, userId);
	    } catch (EmptyResultDataAccessException e) {
	        // Handle case where user ID does not exist
	        return null;
	    }
	}


}
