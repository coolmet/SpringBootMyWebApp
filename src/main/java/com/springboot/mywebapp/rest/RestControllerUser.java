package com.springboot.mywebapp.rest;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.springboot.mywebapp.exception.InternalServerException;
import com.springboot.mywebapp.exception.UserNotFoundException;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.UserService;

// ResponseEntity.status(HttpStatus.CONFLICT).build();
// ResponseEntity.created(new URI("/user/"+newUser.getUserId())).build();
// ResponseEntity.ok(users);
// ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
// ResponseEntity.status(HttpStatus.CONFLICT).build();
// ResponseEntity.noContent().build();
// ResponseEntity.notFound().build();

@RestController
@RequestMapping("/rest/user")
public class RestControllerUser
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/all",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/user/get/json/all
	public ResponseEntity<List<User>> getUsersJson()
	{
		List<User> users=userService.findAll();
		return ResponseEntity.ok(users);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/xml/all",produces=MediaType.APPLICATION_XML_VALUE) // http://localhost:8080/rest/user/get/xml/all
	public UserList<User> getUsersXML()
	{
		List<User> users=userService.findAll();
		UserList<User> listOfUsers=new UserList<User>(users);
		return listOfUsers;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/{userid}",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/user/get/json/1000003
	public ResponseEntity<?> getUserByIdJson(@PathVariable("userid") Long userid)
	{
		try
		{
			User user=userService.findByUserId(userid);
			return ResponseEntity.ok(user);
		}
		catch(UserNotFoundException ex)
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/xml/{userid}",produces=MediaType.APPLICATION_XML_VALUE) // http://localhost:8080/rest/user/get/xml/1000003
	public ResponseEntity<?> getUserByIdXML(@PathVariable("userid") Long userid)
	{
		return getUserByIdJson(userid);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/rest/user/get/json/?un=admin http://localhost:8080/rest/user/get/json/?em=user@email.com
	public ResponseEntity<List<User>> getUsersByUserNameJson(@RequestParam(value="un",required=false) String userName,@RequestParam(value="em",required=false) String email)
	{
		List<User> users=userName!=null?userService.findAllByUserName(userName)
		:email!=null?userService.findAllByEmail(email):null;
		return ResponseEntity.ok(users);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/xml/",produces=MediaType.APPLICATION_XML_VALUE) // http://localhost:8080/rest/user/get/xml/?un=admin http://localhost:8080/rest/user/get/xml/?em=user@email.com
	public UserList<User> getUsersByUserNameXML(@RequestParam(value="un",required=false) String userName,@RequestParam(value="em",required=false) String email)
	{
		List<User> users=userName!=null?userService.findAllByUserName(userName)
		:email!=null?userService.findAllByEmail(email):null;
		
		UserList<User> listOfUsers=new UserList<User>(users);
		return listOfUsers;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/del/{userid}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> delUser(@PathVariable("userid") Long userid)
	{
		try
		{
			userService.findByUserId(userid);
			userService.delete(userid);
			return ResponseEntity.ok().build();
		}
		catch(UserNotFoundException ex)
		{
			throw ex;
		}
		catch(Exception ex)
		{
			// throw new InternalServerException(ex);
		}
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/create")
	public ResponseEntity<URI> createUser(@RequestBody User user)
	{
		try
		{
			userService.create(user);
			Long id=user.getUserId();
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/update/{userid}")
	public ResponseEntity<?> updateUser(@PathVariable("userid") Long userid,@RequestBody User userRequest)
	{
		try
		{
			User user=userService.findByUserId(userid);
			user.setActive(userRequest.isActive());
			user.setEmail(userRequest.getEmail());
			userService.update(user);
			return ResponseEntity.ok().build();
		}
		catch(UserNotFoundException ex)
		{
			return ResponseEntity.notFound().build();
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}