package com.springboot.mywebapp.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.mywebapp.exception.UserNotFoundException;
import com.springboot.mywebapp.model.Images;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.ImagesService;

@Order(2)
@RestController
@RequestMapping("/rest/images")
public class RestControllerAnnoymus_Images
{
	@Autowired
	private ImagesService imagesService;
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/all",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/images/get/json/all
	public ResponseEntity<List<Images>> getImagesJson()
	{
		List<Images> images=imagesService.findAll();
		return ResponseEntity.ok(images);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/random/data") // http://localhost:8080/rest/images/get/random/data
	public ResponseEntity<byte[]> getRandomData()
	{
		Images images=imagesService.findRandomNative();
		return ResponseEntity.ok(images.getData());
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/random/base64data") // http://localhost:8080/rest/images/get/random/base64data
	public ResponseEntity<String> getRandomBase64Data()
	{
		Images images=imagesService.findRandomNative();
		return ResponseEntity.ok(images.getImageBase64Data());
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/random/native",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/images/get/json/random/native
	public ResponseEntity<Images> getRandomNativeJson()
	{
		Images images=imagesService.findRandomNative();
		return ResponseEntity.ok(images);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/random/function",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/images/get/json/random/function
	public ResponseEntity<Images> getRandomFunctionJson()
	{
		Images images=imagesService.findRandomFunction();
		return ResponseEntity.ok(images);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/random/functionall",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/images/get/json/random/functionall
	public ResponseEntity<List<Images>> getRandomFunctionAllJson()
	{
		List<Images> images=imagesService.findRandomFunctionAll();
		return ResponseEntity.ok(images);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/images/get/json/?imagetype=indexintro http://localhost:8080/rest/images/get/json/?name=image1
	public ResponseEntity<List<Images>> getImagesByImageTypeJson(@RequestParam(value="imagetype",required=false) String imagetype,@RequestParam(value="name",required=false) String name)
	{
		List<Images> images=imagetype!=null?imagesService.findAllByType(imagetype)
		:name!=null?imagesService.findAllByName(name):null;
		return ResponseEntity.ok(images);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/imagetype/{imagetype}",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/images/get/json/imagetype/indexintro
	public ResponseEntity<List<Images>> getImagesByImageTypeJson2(@PathVariable("imagetype") String imagetype)
	{
		List<Images> images=imagesService.findAllByType(imagetype);
		return ResponseEntity.ok(images);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/name/{name}",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/images/get/json/name/image1
	public ResponseEntity<List<Images>> getImagesByNameJson2(@PathVariable("name") String name)
	{
		List<Images> images=imagesService.findAllByName(name);
		return ResponseEntity.ok(images);
	}
}
