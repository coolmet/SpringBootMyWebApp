package com.springboot.mywebapp.test;

import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.UserService;
import com.springboot.mywebapp.service.UtilService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UtilServiceTest
{
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private UserService userService;
	
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
		Map<String,String> checkUser;
		// ###TEST-1
		user.setName("");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-1:\t"+checkUser.get("registerstatus")+"\t:\t"+checkUser.get("registermessage"));
		user.setName("xxname");
		// ###TEST-2
		user.setConfirmationtoken("xxpass2");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-2:\t"+checkUser.get("registerstatus")+"\t:\t"+checkUser.get("registermessage"));
		user.setConfirmationtoken("xxpass");
		// ###TEST-3
		user.setUsername("admin");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-3:\t"+checkUser.get("registerstatus")+"\t:\t"+checkUser.get("registermessage"));
		user.setUsername("xxusername");
		// ###TEST-4
		user.setEmail("user@email.com");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-4:\t"+checkUser.get("registerstatus")+"\t:\t"+checkUser.get("registermessage"));
		user.setEmail("xxemail");
		// ###TEST-5
		user.setEmail("user@email.com");
		user.setUsername("admin");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-5:\t"+checkUser.get("registerstatus")+"\t:\t"+checkUser.get("registermessage"));
		user.setEmail("xxemail");
		user.setUsername("xxusername");
		// ###TEST-6
		user.setEmail("user2@email.com");
		user.setUsername("admin2");
		checkUser=utilService.registerUser(user);
		System.out.println("TEST-6:\t"+checkUser.get("registerstatus")+"\t:\t"+checkUser.get("registermessage"));
		user.setEmail("xxemail");
		user.setUsername("xxusername");
		//
		
	}
}
