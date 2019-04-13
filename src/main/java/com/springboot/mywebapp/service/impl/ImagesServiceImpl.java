package com.springboot.mywebapp.service.impl;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.mywebapp.dao.ImagesRepository;
import com.springboot.mywebapp.dao.UserRepository;
import com.springboot.mywebapp.model.Images;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.ImagesService;
import com.springboot.mywebapp.service.UserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ImagesServiceImpl implements ImagesService
{
	private ImagesRepository imagesRepository;
	
	public ImagesRepository getImagesRepository()
	{
		return imagesRepository;
	}
	
	@Autowired
	public void setImagesRepository(ImagesRepository imagesRepository)
	{
		this.imagesRepository=imagesRepository;
	}
	
	@Override
	public Images findRandomNative()
	{
		return imagesRepository.findRandomNative();
	}
	
	@Override
	public Images findRandomFunction()
	{
		return imagesRepository.findRandomFunction();
	}
	
	@Override
	public List<Images> findRandomFunctionAll()
	{
		return imagesRepository.findRandomFunctionAll();
	}
	
	@Override
	public List<Images> findAll()
	{
		return imagesRepository.findAll();
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Images findById(Long id)
	{
		return imagesRepository.findById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Images> findAllByName(String name)
	{
		return imagesRepository.findAllByName(name);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Images> findAllByType(String imagetype)
	{
		return imagesRepository.findAllByType(imagetype);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public void create(Images images)
	{
		imagesRepository.create(images);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public Images update(Images images)
	{
		return imagesRepository.update(images);
	}
	
	@Override
	@Secured(value=
	{"ROLE_ADMIN"})
	public void delete(Long id)
	{
		imagesRepository.delete(id);
	}
	
}
