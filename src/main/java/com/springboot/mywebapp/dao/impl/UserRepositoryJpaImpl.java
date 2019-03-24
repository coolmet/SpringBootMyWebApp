package com.springboot.mywebapp.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.springboot.mywebapp.dao.UserRepository;
import com.springboot.mywebapp.model.User;

// @Primary
@Repository("userRepository")
public class UserRepositoryJpaImpl implements UserRepository
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<User> findAll()
	{
		return entityManager.createQuery("from User",User.class).getResultList();
	}
	
	@Override
	public User findByUserId(Long userId)
	{
		try
		{
			return entityManager.find(User.class,userId);
		}
		catch(Exception rt)
		{
			return emptyUser();
		}
	}
	
	@Override
	public List<User> findAllByUserId(Long userId)
	{
		return entityManager.createQuery("from User where userid = :pUserId",User.class).setParameter("pUserId",userId).getResultList();
	}
	
	@Override
	public User findByUserName(String userName)
	{
		try
		{
			return entityManager.createQuery("from User where username = :pUserName",User.class).setParameter("pUserName",userName).getSingleResult();
		}
		catch(Exception rt)
		{
			return emptyUser();
		}
	}
	
	@Override
	public List<User> findAllByUserName(String userName)
	{
		return entityManager.createQuery("from User where username = :pUserName",User.class).setParameter("pUserName",userName).getResultList();
	}
	
	@Override
	public User findByConfirmationToken(String confirmationToken)
	{
		try
		{
			return entityManager.createQuery("from User where confirmationtoken = :pConfirmationToken",User.class).setParameter("pConfirmationToken",confirmationToken).getSingleResult();
		}
		catch(Exception rt)
		{
			return emptyUser();
		}
	}
	
	@Override
	public List<User> findAllByConfirmationToken(String confirmationToken)
	{
		return entityManager.createQuery("from User where confirmationtoken = :pConfirmationtoken",User.class).setParameter("pConfirmationToken",confirmationToken).getResultList();
	}
	
	@Override
	public User findByEmail(String email)
	{
		try
		{
			return entityManager.createQuery("from User where email = :pEmail",User.class).setParameter("pEmail",email).getSingleResult();
		}
		catch(Exception rt)
		{
			return emptyUser();
		}
	}
	
	@Override
	public List<User> findAllByEmail(String email)
	{
		return entityManager.createQuery("from User where email = :pEmail",User.class).setParameter("pEmail",email).getResultList();
	}
	
	@Override
	public void create(User user)
	{
		entityManager.persist(user);
	}
	
	@Override
	public User update(User user)
	{
		return entityManager.merge(user);
	}
	
	@Override
	public void delete(Long userid)
	{
		entityManager.remove(entityManager.getReference(User.class,userid));
	}
	
	@Override
	public void deleteByUserName(String userName)
	{
		entityManager.createQuery("delete from User where username = :pUserName").setParameter("pUserName",userName).executeUpdate();
	}
	
	@Override
	public void deleteByEmail(String email)
	{
		entityManager.createQuery("delete from User where email = :pEmail").setParameter("pEmail",email).executeUpdate();
	}
	
	private User emptyUser()
	{
		User user=new User();
		user.setActive(false);
		user.setUserId(Long.parseLong("0"));
		user.setConfirmationtoken("");
		user.setPassword("");
		user.setEmail("");
		user.setName("");
		user.setSurname("");
		user.setUsername("");
		return user;
	}
	
}
