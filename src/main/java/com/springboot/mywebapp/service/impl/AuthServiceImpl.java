package com.springboot.mywebapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.mywebapp.dao.AuthRepository;
import com.springboot.mywebapp.dao.UserRepository;
import com.springboot.mywebapp.model.Auth;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.AuthService;
import com.springboot.mywebapp.service.UserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class AuthServiceImpl implements AuthService
{
	private AuthRepository authRepository;
	
	public AuthRepository getAuthRepository()
	{
		return authRepository;
	}
	
	@Autowired
	public void setAuthRepository(AuthRepository authRepository)
	{
		this.authRepository=authRepository;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Auth findByUserName(String userName)
	{
		return authRepository.findByUserName(userName);
	}
	
	@Override
	public Auth create(String userName,String authority)
	{
		return authRepository.create(userName,authority);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN","ROLE_USER"})
	public Auth update(String userName,String authority)
	{
		return authRepository.update(userName,authority);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN","ROLE_USER"})
	public Auth update(Auth auth)
	{
		return authRepository.update(auth);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN","ROLE_USER"})
	public Auth createOrUpdate(String userName,String authority)
	{
		return authRepository.update(userName,authority);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public void deleteByUserName(String userName)
	{
		authRepository.deleteByUserName(userName);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public void delete(Auth auth)
	{
		authRepository.delete(auth);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public void deleteByAuthId(String authId)
	{
		authRepository.deleteByAuthId(authId);		
	}
	
}
