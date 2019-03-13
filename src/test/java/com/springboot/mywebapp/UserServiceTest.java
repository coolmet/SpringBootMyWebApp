package com.springboot.mywebapp;

import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.UserService;

@RunWith(SpringRunner.class)

public class UserServiceTest
{
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindUsers()
	{
		List<User> users=userService.findAll();
		MatcherAssert.assertThat(users.size(),Matchers.equalTo(7));
	}
	
}
