package com.springboot.mywebapp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoderTest
{
	@Test
	public void generateEncodedPasswords()
	{
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		System.out.println("{bcrypt}"+passwordEncoder.encode("secret"));
		System.out.println("{bcrypt}"+passwordEncoder.encode("secret"));
		System.out.println("{bcrypt}"+passwordEncoder.encode("secret"));
		
	}
}
