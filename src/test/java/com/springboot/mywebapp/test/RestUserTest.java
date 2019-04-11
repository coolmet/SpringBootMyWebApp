package com.springboot.mywebapp.test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.service.AuthService;
import com.springboot.mywebapp.service.UserService;
import com.springboot.mywebapp.service.UtilService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=SpringSecurityWebAuxTestConfig.class)
@AutoConfigureMockMvc
@PreAuthorize("hasRole('ADMIN')")

// @SpringBootTest(webEnvironment=WebEnvironment.MOCK)
// @ActiveProfiles("dev")
// @RunWith(SpringRunner.class)
// @ContextConfiguration(locations = "classpath:application-dev.properties")
public class RestUserTest
{
	@LocalServerPort
	int randomPort;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setUp()
	{
		
	}
	
	@Test
	@WithUserDetails(value="admin",userDetailsServiceBeanName="userDetailsService")
	public void test3()
	{
		try
		{
			Logger.getGlobal().severe("3_1>>>>>>: "+SecurityContextHolder.getContext().getAuthentication());
			Logger.getGlobal().severe("3_2>>>>>>: "+SecurityContextHolder.getContext().getAuthentication().isAuthenticated()+":"+randomPort);
			
			ResponseEntity<?> resp=testRestTemplate.getForEntity("/rest/user/get/json/1000003",String.class);
			Logger.getGlobal().severe("3_3>>>>>>: "+resp.getBody());
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",authorities="ADMIN")
	public void test2()
	{
		String body=testRestTemplate.getForObject("/rest/user/get/json/1000002",String.class);
		Logger.getGlobal().severe("2>>>>>>: "+body);
		
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void test1()
	{
		try
		{
			//
			MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get("/rest/user/get/json/1000001").accept(MediaType.APPLICATION_JSON);
			ResultActions resultActions=mockMvc.perform(requestBuilder);
			MvcResult mvcResult=resultActions.andReturn();
			Logger.getGlobal().severe("getContentAsString: "+mvcResult.getResponse().getContentAsString());
			Logger.getGlobal().severe("getContentType: "+mvcResult.getResponse().getContentType());
			Logger.getGlobal().severe("getErrorMessage: "+mvcResult.getResponse().getErrorMessage());
			Logger.getGlobal().severe("getForwardedUrl: "+mvcResult.getResponse().getForwardedUrl());
			
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			
			//
			objectMapper.readValue(mvcResult.getResponse().getContentAsString(),User.class);
			//
			HttpHeaders header=new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<?> responseEntity=new ResponseEntity<>(
			                                                      mvcResult.getResponse().getContentAsString(),
			                                                      header,
			                                                      HttpStatus.OK // Or HttpStatus.ACCEPTED
			);
			
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
}
