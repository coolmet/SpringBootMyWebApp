package com.springboot.mywebapp.service;

import java.util.List;
import com.springboot.mywebapp.model.Auth;
import com.springboot.mywebapp.model.User;

public interface AuthService
{
	Auth findByUserName(String userName);
	
	Auth create(String userName,String authority);
	
	Auth update(String userName,String authority);
	
	Auth update(Auth auth);
	
	Auth createOrUpdate(String userName,String authority);
	
	void deleteByUserName(String userName);
	
	void delete(Auth auth);
	
	void deleteByAuthId(String authId);
}
