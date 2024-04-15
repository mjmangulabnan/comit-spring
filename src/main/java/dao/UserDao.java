package dao;

import java.util.List;

import bean.User;
import dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		String sql = "INSERT INTO USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, STATUS) " 
	               + "VALUES(?,?,?,?,?,?)";
		
		this.jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getFirstName(), 
				user.getLastName(), user.getEmail(), "A");
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
	
	public void updateUser(User user){
		
		String sql = "UPDATE USER SET USERNAME = ?, FIRST_NAME = ?, LAST_NAME = ?" 
			       + ", EMAIL = ? WHERE USER_ID = ?";
		
		this.jdbcTemplate.update(sql, user.getUsername(), user.getFirstName(), 
				user.getLastName(), user.getEmail(), user.getUserId());
	}
	
}
