package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Task;
import org.springframework.jdbc.core.RowMapper;

public class TaskMapper implements RowMapper<Task>{

	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Task task = new Task();
		
		task.setTaskId(rs.getInt("TASK_ID"));
		task.setTaskName(rs.getString("TASK_NAME"));
		task.setTaskType(rs.getString("TASK_TYPE"));
		task.setClientName(rs.getString("CLIENT_NAME"));
		//user.setLastName(rs.getString("LAST_NAME"));
		//user.setEmail(rs.getString("EMAIL"));
		//user.setStatus(rs.getString("STATUS"));
		
		return task;
	}

}
