package com.springboot.mywebapp.service;

import java.util.UUID;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
	@Autowired
	Environment environment;
	
	private final JavaMailSender sender;
	
	@Autowired
	public EmailService(JavaMailSender sender)
	{
		this.sender=sender;
	}
	
	public void send(String from,String emailTo,String subject,String body)
	{
		MimeMessagePreparator message=newMessage->
		{
			newMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));
			newMessage.setFrom(from);
			newMessage.setReplyTo(InternetAddress.parse(environment.getProperty("spring.mail.username")));
			newMessage.setContentID(UUID.randomUUID().toString());
			newMessage.setSubject(subject,environment.getProperty("spring.mail.default-encoding"));
			newMessage.setText(body);
		};
		this.sender.send(message);
	}
}
