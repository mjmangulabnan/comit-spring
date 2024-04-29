package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		
		user.setUserId(rs.getInt("USER_ID"));
		user.setUsername(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setEmail(rs.getString("EMAIL"));
		user.setStatus(rs.getString("STATUS"));
		user.setRole(rs.getString("ROLE"));
		
		return user;
	}

}
