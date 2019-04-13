package com.springboot.mywebapp.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.springboot.mywebapp.dao.ImagesRepository;
import com.springboot.mywebapp.model.Images;

@Repository("imagesRepository")
public class ImageRepositoryImpl implements ImagesRepository
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Images> findAll()
	{
		return entityManager.createQuery("from Images",Images.class).getResultList();
	}
	
	@Override
	public Images findRandomNative()
	{
		return entityManager.createQuery("from Images ORDER BY RAND()",Images.class).setMaxResults(1).getSingleResult();
		
	}
	
	@Override
	public Images findRandomFunction()
	{
		return entityManager.createQuery("from Images order by function('RAND')",Images.class).setMaxResults(1).getSingleResult();
	}
	
	@Override
	public List<Images> findRandomFunctionAll()
	{
		return entityManager.createQuery("from Images order by function('RAND')",Images.class).getResultList();
	}
	
	@Override
	public Images findById(Long id)
	{
		try
		{
			return entityManager.find(Images.class,id);
		}
		catch(Exception rt)
		{
			return emptyImages();
		}
	}
	
	@Override
	public List<Images> findAllByName(String name)
	{
		return entityManager.createQuery("from Images where name = :pName",Images.class).setParameter("pName",name).getResultList();
	}
	
	@Override
	public List<Images> findAllByType(String imagetype)
	{
		return entityManager.createQuery("from Images where imagetype = :pImageType",Images.class).setParameter("pImageType",imagetype).getResultList();
		
	}
	
	@Override
	public void create(Images images)
	{
		entityManager.persist(images);
	}
	
	@Override
	public Images update(Images images)
	{
		return entityManager.merge(images);
	}
	
	@Override
	public void delete(Long id)
	{
		entityManager.remove(entityManager.getReference(Images.class,id));
	}
	
	private Images emptyImages()
	{
		Images images=new Images();
		images.setData(null);
		images.setImagetype("");
		images.setName("");
		return images;
	}
}
