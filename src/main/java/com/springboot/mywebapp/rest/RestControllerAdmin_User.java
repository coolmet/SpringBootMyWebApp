package com.springboot.mywebapp.rest;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.springboot.mywebapp.model.ROLES;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.AuthService;
import com.springboot.mywebapp.service.UserService;

// ResponseEntity.status(HttpStatus.CONFLICT).build();
// ResponseEntity.created(new URI("/user/"+newUser.getUserId())).build();
// ResponseEntity.ok(users);
// ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
// ResponseEntity.status(HttpStatus.CONFLICT).build();
// ResponseEntity.noContent().build();
// ResponseEntity.notFound().build();

@Order(1)
@RestController
@RequestMapping("/restadmin/user")
public class RestControllerAdmin_User
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final String EMAIL_PATTERN=
	"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean checkEmailAddressIsValid(String email)
	{
		return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/all",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/restadmin/user/get/json/all
	public ResponseEntity<List<User>> getUsersJson()
	{
		List<User> users=userService.findAll();
		return ResponseEntity.ok(users);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/xml/all",produces=MediaType.APPLICATION_XML_VALUE) // http://localhost:8080/restadmin/user/get/xml/all
	public UserList<User> getUsersXML()
	{
		List<User> users=userService.findAll();
		UserList<User> listOfUsers=new UserList<User>(users);
		return listOfUsers;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/{userid}",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/restadmin/user/get/json/1000003
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
	
	@RequestMapping(method=RequestMethod.GET,value="/get/xml/{userid}",produces=MediaType.APPLICATION_XML_VALUE) // http://localhost:8080/restadmin/user/get/xml/1000003
	public ResponseEntity<?> getUserByIdXML(@PathVariable("userid") Long userid)
	{
		return getUserByIdJson(userid);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/json/",produces=MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/restadmin/user/get/json/?un=admin http://localhost:8080/restadmin/user/get/json/?em=user@email.com
	public ResponseEntity<List<User>> getUsersByUserNameJson(@RequestParam(value="un",required=false) String userName,@RequestParam(value="em",required=false) String email)
	{
		List<User> users=userName!=null?userService.findAllByUserName(userName)
		:email!=null?userService.findAllByEmail(email):null;
		return ResponseEntity.ok(users);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/xml/",produces=MediaType.APPLICATION_XML_VALUE) // http://localhost:8080/restadmin/user/get/xml/?un=admin http://localhost:8080/restadmin/user/get/xml/?em=user@email.com
	public UserList<User> getUsersByUserNameXML(@RequestParam(value="un",required=false) String userName,@RequestParam(value="em",required=false) String email)
	{
		List<User> users=userName!=null?userService.findAllByUserName(userName)
		:email!=null?userService.findAllByEmail(email):null;
		
		UserList<User> listOfUsers=new UserList<User>(users);
		return listOfUsers;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/del/{userid}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> delUser(@PathVariable("userid") Long userid) // http://localhost:8080/restadmin/user/del/1000003
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
	
	@RequestMapping(method=RequestMethod.DELETE,value="/del2/{userid}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> delUser2(@PathVariable("userid") Long userid) // http://localhost:8080/restadmin/user/del/1000003
	{
		try
		{
			User user=userService.findByUserId(userid);
			//
			if(user.getUserId()==0)
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.useridnotfound",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			//
			userService.delete(userid);
			return new ResponseEntity<String>(""+user.getUserId(),HttpStatus.OK);
		}
		catch(UserNotFoundException ex)
		{
			return new ResponseEntity<String>("ERROR",HttpStatus.BAD_REQUEST);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<String>("ERROR",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/create")
	public ResponseEntity<URI> createUser(@RequestBody User user) // http://localhost:8080/restadmin/user/create
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
	
	@RequestMapping(method=RequestMethod.POST,value="/create2")
	public ResponseEntity<String> createUser2(@RequestParam(value="username",required=false) String username,
	                                          @RequestParam(value="name",required=false) String name,
	                                          @RequestParam(value="surname",required=false) String surname,
	                                          @RequestParam(value="email",required=false) String email,
	                                          @RequestParam(value="password1",required=false) String password1,
	                                          @RequestParam(value="password2",required=false) String password2) // http://localhost:8080/restadmin/user/create/?username=xusername&name=xname
	{
		try
		{
			User user=new User();
			user.setUsername(username);
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			user.setPassword(password1);
			user.setConfirmationtoken(password2);
			//
			if(user.getUsername().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.enterusername",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getName().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.entername",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getSurname().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.entersurname",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getEmail().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.enteremail",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getPassword().equals("")||user.getConfirmationtoken().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.enterpassword",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(!user.getPassword().equals(user.getConfirmationtoken()))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.passwordsnotequal",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(!checkEmailAddressIsValid(user.getEmail()))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.entervaliemailaddress",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(userService.findByEmail(email).getUserId()!=0)
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.emailused",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(userService.findByUserName(username).getUserId()!=0)
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.usernameused",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			//
			user.setPassword(password1.contains("{bcrypt}")||password1.contains("{nope}")?password1:"{bcrypt}"+new BCryptPasswordEncoder().encode(password1));
			user.setConfirmationtoken(UUID.randomUUID().toString());
			user.setActive(true);
			userService.create(user);
			authService.create(user.getUsername(),ROLES.ROLE_USER.stringValue);
			return new ResponseEntity<String>(""+user.getUserId(),HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<String>("ERROR",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/update/{userid}")
	public ResponseEntity<?> updateUser(@PathVariable("userid") Long userid,@RequestBody User userRequest) // http://localhost:8080/restadmin/user/update
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
	
	@RequestMapping(method=RequestMethod.POST,value="/update2/{userid}/") // http://localhost:8080/restadmin/user/update/100001/?username=xusername&name=xname
	public ResponseEntity<String> updateUser2(@PathVariable("userid") Long userid,
	                                          @RequestParam(value="username",required=false) String username,
	                                          @RequestParam(value="name",required=false) String name,
	                                          @RequestParam(value="surname",required=false) String surname,
	                                          @RequestParam(value="email",required=false) String email,
	                                          @RequestParam(value="password1",required=false) String password1,
	                                          @RequestParam(value="password2",required=false) String password2)
	{
		try
		{
			User user=new User();
			user.setUserId(userid);
			user.setUsername(username);
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			user.setPassword(password1);
			user.setConfirmationtoken(password2);
			//
			if(user.getUsername().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.enterusername",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getName().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.entername",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getSurname().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.entersurname",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getEmail().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.enteremail",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getPassword().equals("")||user.getConfirmationtoken().equals(""))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.enterpassword",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(!user.getPassword().equals(user.getConfirmationtoken()))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.passwordsnotequal",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(!checkEmailAddressIsValid(user.getEmail()))
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.entervaliemailaddress",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			else if(user.getUserId()==0)
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.useridnotfound",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			// ###############
			User user1=userService.findByUserId(userid);
			if(user1.getUserId()==0)
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.useridnotfound",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			//
			user1=userService.findByUserName(user.getUsername());
			if(user1.getUserId()!=0&&user1.getUserId()!=user.getUserId())
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.usernameused",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			//
			user1=userService.findByEmail(user.getEmail());
			if(user1.getUserId()!=0&&user1.getUserId()!=user.getUserId())
			{
				return new ResponseEntity<String>(messageSource.getMessage("register.error.emailused",new Object[0],LocaleContextHolder.getLocale()),HttpStatus.BAD_REQUEST);
			}
			//
			user.setPassword(password1.contains("{bcrypt}")||password1.contains("{nope}")?password1:"{bcrypt}"+new BCryptPasswordEncoder().encode(password1));
			user.setConfirmationtoken(UUID.randomUUID().toString());
			userService.update(user);
			return new ResponseEntity<String>(""+user.getUserId(),HttpStatus.OK);
		}
		catch(UserNotFoundException ex)
		{
			return new ResponseEntity<String>("ERROR",HttpStatus.BAD_REQUEST);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<String>("ERROR",HttpStatus.BAD_REQUEST);
		}
	}
	
}
