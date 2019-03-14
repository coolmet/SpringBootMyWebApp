package com.springboot.mywebapp.test;

import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest
{
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindUsers()
	{
		List<User> users=userService.findAll();
		System.out.println("Test:1#"+users.size());
		System.out.println("Test:2#"+users.toString());
		System.out.println("Test:3#"+userService.findByEmail("user1@email.com").getName());

		MatcherAssert.assertThat(users.size(),Matchers.equalTo(8));
	}
	
}
