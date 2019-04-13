package com.springboot.mywebapp.dao;

import java.util.List;
import com.springboot.mywebapp.model.Images;

public interface ImagesRepository
{
	List<Images> findAll();
	
	Images findRandomNative();
	
	Images findRandomFunction();
	
	List<Images> findRandomFunctionAll();
	
	Images findById(Long id);
	
	List<Images> findAllByName(String name);
	
	List<Images> findAllByType(String imagetype);
	
	void create(Images images);
	
	Images update(Images images);
	
	void delete(Long id);
	
}
