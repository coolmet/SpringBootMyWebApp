package com.springboot.mywebapp.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.springboot.mywebapp.dao.UserRepository;
import com.springboot.mywebapp.model.MessageInfo;
import com.springboot.mywebapp.model.ROLES;

@Service
public class UtilService
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	public EmailService emailService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	Environment environment;
	
	private static final String EMAIL_PATTERN=
	"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public String getApplicationPropertiesValue(String key)
	{
		return environment.getProperty(key);
	}
	
	public boolean checkEmailAddressIsValid(String email)
	{
		return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
	}
	
	public void authenticateUser(HttpServletRequest request,com.springboot.mywebapp.model.User user)
	{
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken(request,user));
	}
	
	public void authenticateUser(HttpServletRequest request,String token)
	{
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken(request,userService.findByConfirmationToken(token)));
	}
	
	public UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(HttpServletRequest request,com.springboot.mywebapp.model.User user)
	{
		List<GrantedAuthority> grantedAuthorities=authService.findAllByUserNameGrantedAuthority(user.getUsername());
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(user.getUsername(),
		                                                                                                                                                       user.getPassword(),
		                                                                                                                                                       user.isActive(),
		                                                                                                                                                       true,
		                                                                                                                                                       true,
		                                                                                                                                                       true,
		                                                                                                                                                       grantedAuthorities),
		                                                                                                null,grantedAuthorities);
		authenticationToken.setDetails(new WebAuthenticationDetails(request));
		return authenticationToken;
	}
	
	public MessageInfo updateUser(com.springboot.mywebapp.model.User user)
	{
		user.setUserId(getCurrentUser().getUserId());
		MessageInfo result=new MessageInfo();
		if(user.getName().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.entername",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.getSurname().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.entersurname",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.getUsername().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.enterusername",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.getEmail().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.enteremail",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.getPassword().equals("")||user.getConfirmationtoken().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.enterpassword",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(!user.getPassword().equals(user.getConfirmationtoken()))
		{
			result.setMessage(messageSource.getMessage("register.error.passwordsnotequal",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(!checkEmailAddressIsValid(user.getEmail()))
		{
			result.setMessage(messageSource.getMessage("register.error.entervaliemailaddress",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		//
		com.springboot.mywebapp.model.User user1;
		user1=userService.findByUserName(user.getUsername());
		if(user1.getUserId()!=0&&user1.getUserId()!=user.getUserId())
		{
			result.setMessage(messageSource.getMessage("register.error.usernameused",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		user1=userService.findByEmail(user.getEmail());
		if(user1.getUserId()!=0&&user1.getUserId()!=user.getUserId())
		{
			result.setMessage(messageSource.getMessage("register.error.emailused",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		//
		if(result.getMessage().equals(""))
		{
			user.setActive(true);
			user.setPassword(user.getPassword().contains("{bcrypt}")?user.getPassword():"{bcrypt}"+new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setConfirmationtoken("");
			userService.update(user);
			result.setMessage(messageSource.getMessage("register.ok.settingssuccessful",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(true);
		}
		return result;
	}
	
	public MessageInfo activateUser(HttpServletRequest request,String token)
	{
		MessageInfo result=new MessageInfo();
		com.springboot.mywebapp.model.User user=userService.findByConfirmationToken(token);
		if(user.getUsername().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.activation.invalidtoken",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.isActive())
		{
			result.setMessage(messageSource.getMessage("register.activation.alreadyactivated",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		if(result.getMessage().equals(""))
		{
			user.setActive(true);
			user.setPassword(user.getPassword().contains("{bcrypt}")?user.getPassword():"{bcrypt}"+new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setConfirmationtoken("");
			userService.update(user);
			this.authenticateUser(request,user);
			emailService.send("SpringBootMyWebApp-Activation",
			                  user.getEmail(),
			                  "SpringBootMyWebApp-Activation",
			                  "\n\n"+
			                  messageSource.getMessage("register.activation.mail.content1",new Object[0],LocaleContextHolder.getLocale())+"\n\n"+
			                  messageSource.getMessage("register.username",new Object[0],LocaleContextHolder.getLocale())+":"+user.getUsername());
			result.setMessage(messageSource.getMessage("register.activation.mail.content1",new Object[0],LocaleContextHolder.getLocale())+","+
			messageSource.getMessage("register.activation.successful",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(true);
		}
		return result;
	}
	
	public com.springboot.mywebapp.model.User getCurrentUser()
	{
		return userService.findByUserName(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
	}
	
	public List<com.springboot.mywebapp.model.User> getAllUsers()
	{
		return userService.findAll();
	}
	
	public MessageInfo registerUser(com.springboot.mywebapp.model.User user)
	{
		MessageInfo result=new MessageInfo();
		//
		if(user.getName().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.entername",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.getSurname().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.entersurname",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.getUsername().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.enterusername",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.getEmail().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.enteremail",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(user.getPassword().equals("")||user.getConfirmationtoken().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.enterpassword",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(!user.getPassword().equals(user.getConfirmationtoken()))
		{
			result.setMessage(messageSource.getMessage("register.error.passwordsnotequal",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(!checkEmailAddressIsValid(user.getEmail()))
		{
			result.setMessage(messageSource.getMessage("register.error.entervaliemailaddress",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		//
		com.springboot.mywebapp.model.User u1=userService.findByUserName(user.getUsername());
		com.springboot.mywebapp.model.User u2=userService.findByEmail(user.getEmail());
		if(!u1.getUsername().equals("")&&!u2.getUsername().equals("")&&u1.getUserId()==u2.getUserId()&&!u1.isActive())
		{
			result.setMessage(messageSource.getMessage("register.error.confirmemail",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(!u1.getUsername().equals("")&&!u2.getUsername().equals("")&&u1.getUserId()==u2.getUserId()&&u1.isActive())
		{
			result.setMessage(messageSource.getMessage("register.error.useralreadycreated",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(!u1.getUsername().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.error.usernameused",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		else if(!u2.getEmail().equals(""))
		{
			result.setMessage(messageSource.getMessage("register.error.emailused",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(false);
		}
		//
		if(result.getMessage().equals(""))
		{
			user.setActive(false);
			user.setConfirmationtoken(UUID.randomUUID().toString());
			user.setPassword(user.getPassword().contains("{bcrypt}")?user.getPassword():"{bcrypt}"+new BCryptPasswordEncoder().encode(user.getPassword()));
			userService.create(user);
			authService.create(user.getUsername(),ROLES.ROLE_USER.stringValue);
			emailService.send("SpringBootMyWebApp-Registration",
			                  user.getEmail(),
			                  "SpringBootMyWebApp-Registration",
			                  messageSource.getMessage("register.mail.content1",new Object[0],LocaleContextHolder.getLocale())+"\n\n"+
			                  getBaseLinkURL()+"/myweb/confirm-account?token="+user.getConfirmationtoken());
			result.setMessage(messageSource.getMessage("register.ok.registrationsuccessful",new Object[0],LocaleContextHolder.getLocale()));
			result.setStatus(true);
		}
		//
		return result;
	}
	
	protected String getBaseLinkURL()
	{
		HttpServletRequest currentRequest=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		return "http://"+currentRequest.getServerName()+":"+currentRequest.getServerPort();
	}
	
	protected String getCurrentLinkURL()
	{
		HttpServletRequest currentRequest=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		return "http://"+currentRequest.getServerName()+":"+currentRequest.getServerPort()+currentRequest.getServletPath();
	}
}
