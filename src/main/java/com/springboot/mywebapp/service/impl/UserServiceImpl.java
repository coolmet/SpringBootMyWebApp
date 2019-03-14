package com.springboot.mywebapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.mywebapp.dao.UserRepository;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.UserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService
{
	private UserRepository userRepository;
	
	public UserRepository getUserRepository()
	{
		return userRepository;
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public User findByUserId(Long userid)
	{
		return userRepository.findByUserId(userid);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<User> findAllByUserId(Long userid)
	{
		return userRepository.findAllByUserId(userid);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public User findByUserName(String userName)
	{
		return userRepository.findByUserName(userName);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<User> findAllByUserName(String userName)
	{
		return userRepository.findAllByUserName(userName);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public User findByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<User> findAllByEmail(String email)
	{
		return userRepository.findAllByEmail(email);
	}
	
	@Override
	public void create(User user)
	{
		userRepository.create(user);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN","ROLE_USER"})
	public User update(User user)
	{
		return userRepository.update(user);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public void delete(Long id)
	{
		userRepository.delete(id);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public void deleteByUserName(String userName)
	{
		userRepository.deleteByUserName(userName);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public void deleteByEmail(String email)
	{
		userRepository.deleteByEmail(email);
	}
	
}
