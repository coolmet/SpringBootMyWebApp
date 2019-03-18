package com.springboot.mywebapp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import com.springboot.mywebapp.service.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest
{
	// @Autowired
	// public JavaMailSender mailSender;
	//
	// @Autowired
	// public EmailService emailService;
	//
	// @Test
	// public void mailTest()
	// {
	// SimpleMailMessage msg=new SimpleMailMessage();
	// msg.setFrom("k@s");
	// msg.setTo("metehanmeral@hotmail.com");
	// msg.setSubject("Owner created!");
	// msg.setText("Owner entity with id :xxx created successfully.");
	// mailSender.send(msg);
	// //
	// emailService.send("\"metehanmeral@hotmail.com\"","metehanmeral@hotmail.com","konu","body");
	// }
}
