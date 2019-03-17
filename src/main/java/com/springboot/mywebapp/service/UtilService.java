package com.springboot.mywebapp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import com.springboot.mywebapp.dao.UserRepository;

@Service
public class UtilService
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final String EMAIL_PATTERN=
	"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean checkEmailAddressIsValid(String email)
	{
		return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
	}
	
	public Map<String,String> registerUser(com.springboot.mywebapp.model.User user)
	{
		Map<String,String> result=new HashMap<String,String>();
		String registermessage="";
		String registerstatus="";
		//
		if(user.getName().equals(""))
		{
			registermessage=messageSource.getMessage("register.entername",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(user.getSurname().equals(""))
		{
			registermessage=messageSource.getMessage("register.entersurname",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(user.getUsername().equals(""))
		{
			registermessage=messageSource.getMessage("register.enterusername",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(user.getEmail().equals(""))
		{
			registermessage=messageSource.getMessage("register.enteremail",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(user.getPassword().equals("")||user.getConfirmationtoken().equals(""))
		{
			registermessage=messageSource.getMessage("register.enterpassword",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		//
		else if(!user.getPassword().equals(user.getConfirmationtoken()))
		{
			registermessage=messageSource.getMessage("register.error.passwordsnotequal",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(!checkEmailAddressIsValid(user.getEmail()))
		{
			registermessage=messageSource.getMessage("register.error.entervaliemailaddress",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		//
		com.springboot.mywebapp.model.User u1=userService.findByUserName(user.getUsername());
		com.springboot.mywebapp.model.User u2=userService.findByEmail(user.getEmail());
		if(!u1.getUsername().equals("")&&!u2.getUsername().equals("")&&u1.getUserId()==u2.getUserId()&&!u1.isActive())
		{
			registermessage=messageSource.getMessage("register.error.confirmemail",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(!u1.getUsername().equals("")&&!u2.getUsername().equals("")&&u1.getUserId()==u2.getUserId()&&u1.isActive())
		{
			registermessage=messageSource.getMessage("register.error.useralreadycreated",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(!u1.getUsername().equals(""))
		{
			registermessage=messageSource.getMessage("register.error.usernameused",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(!u2.getEmail().equals(""))
		{
			registermessage=messageSource.getMessage("register.error.emailused",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		//
		if(registerstatus.equals(""))
		{
			user.setActive(false);
			user.setCreatedate(new java.sql.Date(new java.util.Date().getTime()));
			user.setConfirmationtoken(UUID.randomUUID().toString());
			userService.create(user);
			registermessage=messageSource.getMessage("register.ok.registrationsccussfully",new Object[0],LocaleContextHolder.getLocale())+"\nUserId:"+user.getUserId();
			registerstatus="OK";
		}
		//
		result.put("registermessage",registermessage);
		result.put("registerstatus",registerstatus);
		return result;
	}
}
