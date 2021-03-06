package com.batch.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.batch.model.User;

@Component
public class JobCompletionListener implements JobExecutionListener {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Executing job id " + jobExecution.getId());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	        List<User> result = jdbcTemplate.query("SELECT userId, firstName, lastName, phone, mialId FROM user", 
	        		new RowMapper<User>() {
	            @Override
	            public User mapRow(ResultSet rs, int row) throws SQLException {
	                return new User();
	            }
	        });
	        System.out.println("Number of Records:"+result.size());
		}
	}
} 