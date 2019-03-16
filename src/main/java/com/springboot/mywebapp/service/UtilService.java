package com.springboot.mywebapp.service;

import java.util.HashMap;
import java.util.Map;
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
		if(!user.getPassword().equals(user.getConfirmationtoken()))
		{
			registermessage=messageSource.getMessage("register.error.passwordsnotequal",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else if(!checkEmailAddressIsValid(user.getEmail()))
		{
			registermessage=messageSource.getMessage("register.error.entervaliemailaddress",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="ERROR";
		}
		else
		{
			registermessage=messageSource.getMessage("register.ok.registrationsccussfully",new Object[0],LocaleContextHolder.getLocale());
			registerstatus="OK";
		}
		result.put("registermessage",registermessage);
		result.put("registerstatus",registerstatus);
		return result;
	}
}
