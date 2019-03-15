package com.springboot.mywebapp.service;

import java.util.List;
import com.springboot.mywebapp.model.User;

public interface UserService 
{
	List<User> findAll();
	
	User findByUserId(Long userid);
	
	List<User> findAllByUserId(Long userid);
	
	User findByUserName(String userName);
	
	List<User> findAllByUserName(String userName);
	
	User findByEmail(String email);
	
	List<User> findAllByEmail(String email);
	
	void create(User user);
	
	User update(User user);
	
	void delete(Long id);
	
	void deleteByUserName(String userName);
	
	void deleteByEmail(String email);
}
