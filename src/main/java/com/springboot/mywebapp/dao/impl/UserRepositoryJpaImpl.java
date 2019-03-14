package com.springboot.mywebapp.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.springboot.mywebapp.dao.UserRepository;
import com.springboot.mywebapp.model.User;

//@Primary
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
	public User findByUserId(Long userid)
	{
		return entityManager.find(User.class,userid);
	}
	
	@Override
	public List<User> findAllByUserId(Long userid)
	{
		return entityManager.createQuery("from User where userid = :userid",User.class).setParameter("userid",userid).getResultList();
	}
	
	@Override
	public User findByUserName(String userName)
	{
		return entityManager.createQuery("from User where username = :username",User.class).setParameter("userName",userName).getSingleResult();
	}
	
	@Override
	public List<User> findAllByUserName(String userName)
	{
		return entityManager.createQuery("from User where username = :username",User.class).setParameter("userName",userName).getResultList();
	}
	
	@Override
	public User findByEmail(String email)
	{
		return entityManager.createQuery("from User where email = :email",User.class).setParameter("email",email).getSingleResult();
	}
	
	@Override
	public List<User> findAllByEmail(String email)
	{
		return entityManager.createQuery("from User where email = :email",User.class).setParameter("email",email).getResultList();
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
		entityManager.createQuery("delete from User where username = :userName").setParameter("userName",userName).executeUpdate();
	}
	
	@Override
	public void deleteByEmail(String email)
	{
		entityManager.createQuery("delete from User where email = :email").setParameter("email",email).executeUpdate();
	}
	
}
