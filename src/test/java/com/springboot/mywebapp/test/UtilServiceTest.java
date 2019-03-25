package com.springboot.mywebapp.test;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.springboot.mywebapp.model.MessageInfo;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.UserService;
import com.springboot.mywebapp.service.UtilService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@PropertySource("file:application.properties")
public class UtilServiceTest
{
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	Environment environment;
	
	@Value("${server.port}")
	private String serverport;
	
	@Test
	public void testEmailAddressIsValid()
	{
		System.out.println("mail@email.com\t:\t"+utilService.checkEmailAddressIsValid("mail@email.com"));
		System.out.println("mail@email\t:\t"+utilService.checkEmailAddressIsValid("mail@email"));
		System.out.println("@email.com\t:\t"+utilService.checkEmailAddressIsValid("@email.com"));
		System.out.println("mail mail2@email.com\t:\t"+utilService.checkEmailAddressIsValid("mail mail2@email.com"));
	}
	
	@Test
	public void testRegisterUser()
	{
		com.springboot.mywebapp.model.User user=new com.springboot.mywebapp.model.User();
		user.setActive(false);
		user.setName("xxname");
		user.setSurname("xxsurname");
		user.setUsername("xxusername");
		user.setEmail("xxemail");
		user.setCreatedate(new java.sql.Date(new java.util.Date().getTime()));
		user.setPassword("xxpass");
		user.setConfirmationtoken("xxpass");
		MessageInfo checkUser;
		// ###TEST-1
		user.setName("");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-1:\t"+checkUser.isStatus()+"\t:\t"+checkUser.getMessage());
		user.setName("xxname");
		// ###TEST-2
		user.setConfirmationtoken("xxpass2");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-2:\t"+checkUser.isStatus()+"\t:\t"+checkUser.getMessage());
		user.setConfirmationtoken("xxpass");
		// ###TEST-3
		user.setUsername("admin");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-3:\t"+checkUser.isStatus()+"\t:\t"+checkUser.getMessage());
		user.setUsername("xxusername");
		// ###TEST-4
		user.setEmail("user@email.com");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-4:\t"+checkUser.isStatus()+"\t:\t"+checkUser.getMessage());
		user.setEmail("xxemail");
		// ###TEST-5
		user.setEmail("user@email.com");
		user.setUsername("admin");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-5:\t"+checkUser.isStatus()+"\t:\t"+checkUser.getMessage());
		user.setEmail("xxemail");
		user.setUsername("xxusername");
		// ###TEST-6
		user.setEmail("user2@email.com");
		user.setUsername("admin2");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-6:\t"+checkUser.isStatus()+"\t:\t"+checkUser.getMessage());
		user.setEmail("xxemail");
		user.setUsername("xxusername");
		//
		getBaseEnvLinkURL();
		
	}
	
	protected String getBaseEnvLinkURL()
	{
		String baseEnvLinkURL=null;
		HttpServletRequest currentRequest=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		// lazy about determining protocol but can be done too
		baseEnvLinkURL="http://"+currentRequest.getLocalName();
		if(currentRequest.getLocalPort()!=80)
		{
			baseEnvLinkURL+=":"+currentRequest.getLocalPort();
		}
		if(!StringUtils.isEmpty(currentRequest.getContextPath()))
		{
			baseEnvLinkURL+=currentRequest.getContextPath();
		}
		System.out.println("x0:"+baseEnvLinkURL);
		System.out.println("x1:"+currentRequest.getServerName());
		System.out.println("x2:"+currentRequest.getServerPort());
		System.out.println("x3:"+currentRequest.getServletPath());
		System.out.println("x4:"+currentRequest.getServerPort());
		System.out.println("x5:"+currentRequest.getContextPath());
		System.out.println("x6:"+currentRequest.getLocalAddr());
		System.out.println("x7:"+currentRequest.getPathInfo());
		System.out.println("x8:"+environment.getProperty("local.server.port"));
		System.out.println("x9:"+serverport);
		
		return baseEnvLinkURL;
	}
}
