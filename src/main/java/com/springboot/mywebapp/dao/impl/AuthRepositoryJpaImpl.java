package com.springboot.mywebapp.dao.impl;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.springboot.mywebapp.dao.AuthRepository;
import com.springboot.mywebapp.model.Auth;
import com.springboot.mywebapp.model.User;

// @Primary
@Repository("authRepository")
public class AuthRepositoryJpaImpl implements AuthRepository
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Auth findByUserName(String userName)
	{
		try
		{
			return entityManager.createQuery("from Auth where username = :pUserName",Auth.class).setParameter("pUserName",userName).getSingleResult();
		}
		catch(Exception rt)
		{
			return emptyAuth();
		}
	}
	
	@Override
	public List<Auth> findAllByUserName(String userName)
	{
		try
		{
			return entityManager.createQuery("from Auth where username = :pUserName",Auth.class).setParameter("pUserName",userName).getResultList();
		}
		catch(Exception rt)
		{
			return (List<Auth>)Arrays.asList(emptyAuth());
		}
	}
	
	@Override
	public Auth create(String userName,String authority)
	{
		Auth auth=new Auth();
		auth.setUsername(userName);
		auth.setAuthority(authority);
		entityManager.persist(auth);
		return auth;
	}
	
	@Override
	public Auth update(String userName,String authority)
	{
		Auth auth=new Auth();
		auth.setUsername(userName);
		auth.setAuthority(authority);
		return entityManager.merge(auth);
	}
	
	@Override
	public Auth update(Auth auth)
	{
		return entityManager.merge(auth);
	}
	
	@Override
	public Auth createOrUpdate(String userName,String authority)
	{
		Auth auth;
		if(this.findByUserName(userName).getAuthId()==0)
		{
			auth=this.create(userName,authority);
		}
		else
		{
			auth=this.update(userName,authority);
		}
		return auth;
	}
	
	@Override
	public void deleteByUserName(String userName)
	{
		entityManager.createQuery("delete from Auth where username = :pUserName").setParameter("pUserName",userName).executeUpdate();
	}
	
	@Override
	public void delete(Auth auth)
	{
		entityManager.remove(entityManager.getReference(Auth.class,auth.getAuthId()));
	}
	
	@Override
	public void deleteByAuthId(String authId)
	{
		entityManager.remove(entityManager.getReference(Auth.class,authId));
	}
	
	private Auth emptyAuth()
	{
		Auth auth=new Auth();
		auth.setAuthId(Long.parseLong("0"));
		auth.setUsername("");
		auth.setAuthority("");
		return auth;
	}
	
}
