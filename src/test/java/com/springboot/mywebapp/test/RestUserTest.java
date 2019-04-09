package com.springboot.mywebapp.test;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.springboot.mywebapp.model.User;

@RunWith(SpringRunner.class)
// @ContextConfiguration(locations = "classpath:application-dev.properties")
// @SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
// @ActiveProfiles("dev")

public class RestUserTest
{
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Before
	public void setUp()
	{
		restTemplate=restTemplate.withBasicAuth("admin","admin");
		// restTemplate=new RestTemplate();
		// BasicAuthenticationInterceptor basicAuthenticationInterceptor=new BasicAuthenticationInterceptor("admin","admin");
		// restTemplate.setInterceptors(Arrays.asList(basicAuthenticationInterceptor));
	}
	
	@Test
	public void testUserById()
	{
		ResponseEntity<User> response=restTemplate.getForEntity("http://localhost:8080/rest/user/get/json/1000003",User.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getUsername(),Matchers.equalTo("admin3"));
	}
}
