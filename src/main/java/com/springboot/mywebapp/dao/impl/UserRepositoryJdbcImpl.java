package com.springboot.mywebapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.springboot.mywebapp.dao.UserRepository;
import com.springboot.mywebapp.model.User;


public class UserRepositoryJdbcImpl implements UserRepository
{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> rowMapper=new RowMapper<User>()
	{
		@Override
		public User mapRow(ResultSet rs,int rowNum) throws SQLException
		{
			User user=new User();
			user.setUserId(rs.getLong("userid"));
			user.setActive(rs.getBoolean("active"));
			user.setConfirmationtoken(rs.getString("confirmationtoken"));
			user.setCreatedate(rs.getDate("createdate"));
			user.setEmail(rs.getString("email"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setSurname(rs.getString("surname"));
			user.setUsername(rs.getString("username"));
			return user;
		}
	};
	
	@Override
	public List<User> findAll()
	{
		return jdbcTemplate.query("select * from DB_USERS ",rowMapper);
	}
	
	@Override
	public User findByUserId(Long userid)
	{
		return DataAccessUtils.singleResult(jdbcTemplate.query("select * from DB_USERS where userid = ?",rowMapper,userid));
	}
	
	@Override
	public List<User> findAllByUserId(Long userid)
	{
		return jdbcTemplate.query("select * from DB_USERS",rowMapper);
	}
	
	@Override
	public User findByUserName(String userName)
	{
		return DataAccessUtils.singleResult(jdbcTemplate.query("select * from DB_USERS where username like ?",rowMapper,"%"+userName+"%"));
	}
	
	@Override
	public List<User> findAllByUserName(String userName)
	{
		return jdbcTemplate.query("select * from DB_USERS where username like ?",rowMapper,"%"+userName+"%");
	}
	
	@Override
	public User findByEmail(String email)
	{
		return DataAccessUtils.singleResult(jdbcTemplate.query("select * from DB_USERS where email like ?",rowMapper,"%"+email+"%"));
	}
	
	@Override
	public List<User> findAllByEmail(String email)
	{
		return jdbcTemplate.query("select * from DB_USERS where email like ?",rowMapper,"%"+email+"%");
	}
	
	@Override
	public void create(User user)
	{
		KeyHolder keyHolder=new GeneratedKeyHolder();
		PreparedStatementCreator psc=new PreparedStatementCreator()
		{
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException
			{
				PreparedStatement stmt=con.prepareStatement(
				                                            "insert into DB_USERS(username,password,active,userid,name,surname,email,confirmationtoken,createdate) "
				                                            +" values(?,?,?,default,?,?,?,?,?)");
				stmt.setString(1,user.getUsername());
				stmt.setString(2,user.getPassword());
				stmt.setBoolean(3,user.isActive());
				stmt.setString(5,user.getName());
				stmt.setString(6,user.getSurname());
				stmt.setString(7,user.getEmail());
				stmt.setString(8,user.getConfirmationtoken());
				stmt.setDate(9,new java.sql.Date(new java.util.Date().getTime()));
				return stmt;
			}
		};
		int count=jdbcTemplate.update(psc,keyHolder);
		if(count!=1)
		{
			throw new RuntimeException("Unable to create user :"+user);
		}
		user.setUserId((Long)keyHolder.getKey());
	}
	
	@Override
	public User update(User user)
	{
		int count=jdbcTemplate.update("update DB_USERS "
		+"set username = ?, password = ?, active = ?, name = ?, surname = ?, email = ?, confirmationtoken = ?, createdate = ? "
		+"where id = ?",user.getUsername(),user.getPassword(),user.isActive(),user.getName(),user.getSurname(),user.getEmail(),user.getConfirmationtoken(),user.getCreatedate(),user.getUserId());
		if(count!=1)
		{
			throw new RuntimeException("Unable to update user :"+user);
		}
		return user;
	}
	
	@Override
	public void delete(Long userid)
	{
		jdbcTemplate.update("delete from DB_USERS where userid = ?",userid);
	}
	
	@Override
	public void deleteByUserName(String userName)
	{
		jdbcTemplate.update("delete from DB_USERS where userName like ?","%"+userName+"%");
	}
	
	@Override
	public void deleteByEmail(String email)
	{
		jdbcTemplate.update("delete from DB_USERS where email like ?","%"+email+"%");
	}
	
}
