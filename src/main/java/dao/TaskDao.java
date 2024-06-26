//package org.comit.spring.dao;
//
//import java.util.List;
//
//import org.comit.spring.bean.Task;
//import org.comit.spring.dao.mapper.TaskMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.support.DataAccessUtils;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class TaskDao {
//
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//	
//	public List<Task> listTasks(){
//	
//		String sql = "SELECT * FROM TASK";
//		
//		return this.jdbcTemplate.query(sql, new TaskMapper());
//	}
//
//	public void createTask(Task task){
//		
//		String sql = "INSERT INTO TASK (TASK_NAME, TASK_DESCRIPTION, TASK_TYPE, CLIENT_NAME, USER_USER_ID)" 
//	               + "VALUES(?,?,?,?,?)";
//		
//		this.jdbcTemplate.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskType(), task.getClientName(), "1");
//	}
//
//	public Task findTask(Task task){
//		
//		String sql = "SELECT * FROM TASK WHERE TASK_ID != ? AND TASK_NAME)"; 
//		
//		return DataAccessUtils.singleResult(this.jdbcTemplate.query(sql, new TaskMapper(), 
//				                   task.getTaskId(), task.getTaskName()));
//	}
//
//	public Task findTask(int taskId){
//		
//		String sql = "SELECT * FROM TASK WHERE TASK_ID = ?";
//		
//		return DataAccessUtils.singleResult(this.jdbcTemplate.query(sql, new TaskMapper(),taskId));
//	}
//	
//	public void updateTask(Task task){
//		
//		String sql = "UPDATE TASK SET TASK_NAME = ?, TASK_DESCRIPTION = ?, TASK_TYPE = ?, CLIENT_NAME = ?" 
//			       + " WHERE TASK_ID = ?";
//		
//		this.jdbcTemplate.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskType(), 
//				task.getClientName(), task.getTaskId());
//	}
//	
//	public List<Task> getAllTasks() {
//	    String sql = "SELECT TASK_ID, TASK_NAME FROM TASK";
//	    return this.jdbcTemplate.query(sql, new TaskMapper());
//	}
//	
//}
package dao;

import java.util.List;

import bean.Task;
import dao.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Task> listTasks() {
        String sql = "SELECT * FROM TASK";
        return jdbcTemplate.query(sql, new TaskMapper());
    }

    public void createTask(Task task) {
        String sql = "INSERT INTO TASK (TASK_NAME, TASK_DESCRIPTION, TASK_TYPE, CLIENT_NAME, USER_USER_ID) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskType(), task.getClientName(), "1");
    }

    public Task findTask(Task task) {
        String sql = "SELECT * FROM TASK WHERE TASK_ID != ? AND TASK_NAME = ?";
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, new TaskMapper(), task.getTaskId(), task.getTaskName()));
    }

    public Task findTask(int taskId) {
        String sql = "SELECT * FROM TASK WHERE TASK_ID = ?";
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, new TaskMapper(), taskId));
    }

    public void updateTask(Task task) {
        String sql = "UPDATE TASK SET TASK_NAME = ?, TASK_DESCRIPTION = ?, TASK_TYPE = ?, CLIENT_NAME = ? WHERE TASK_ID = ?";
        jdbcTemplate.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskType(), task.getClientName(), task.getTaskId());
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM TASK";
        return jdbcTemplate.query(sql, new TaskMapper());
    }
    
    public Task getTaskById(int taskId) {
        String sql = "SELECT * FROM TASK WHERE TASK_ID = ?";
        return DataAccessUtils.singleResult(this.jdbcTemplate.query(sql, new TaskMapper(), taskId));
    }
}
