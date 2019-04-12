package com.springboot.mywebapp.test;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.logging.Logger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mywebapp.model.User;
import com.springboot.mywebapp.rest.UserList;
import com.springboot.mywebapp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
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
	private UserService userService;
	
	@Autowired
	private ObjectMapper jsonMapper;
	// jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
	// jsonMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true);
	// jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
	
	@Autowired
	private MappingJackson2XmlHttpMessageConverter xmlConverter;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp()
	{
		mvc=MockMvcBuilders
		                   .webAppContextSetup(context)
		                   .apply(springSecurity())
		                   .build();
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void getUsersJson()
	{
		try
		{
			String requestbody="";
			MvcResult mvcResult=mvc.perform(get("/rest/user/get/json/all").contentType(MediaType.APPLICATION_JSON).content(requestbody.getBytes()))
			                       .andDo(print())
			                       .andExpect(status().isOk())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			Logger.getGlobal().severe("getUsersJson >>>>>>: "+mvcResult.getResponse().getContentAsString());
			List<User> users=jsonMapper.readValue(mvcResult.getResponse().getContentAsString(),new TypeReference<List<User>>()
			{
			});
			Logger.getGlobal().severe("getUsersJson >>>>>>: "+users.get(0).getUserId());
			
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void getUsersXML()
	{
		try
		{
			String requestbody="";
			MvcResult mvcResult=mvc.perform(get("/rest/user/get/xml/all").contentType(MediaType.APPLICATION_XML).content(requestbody.getBytes()))
			                       .andDo(print())
			                       .andExpect(status().isOk())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			Logger.getGlobal().severe("getUsersXML >>>>>>: "+mvcResult.getResponse().getContentAsString());
			UserList<User> users=xmlConverter.getObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),new TypeReference<UserList<User>>()
			{
			});
			Logger.getGlobal().severe("getUsersXML >>>>>>: "+users.getUser().get(0).getUserId());
			
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void getUserByIdJson()
	{
		try
		{
			String requestbody="";
			MvcResult mvcResult=mvc.perform(get("/rest/user/get/json/1000005").contentType(MediaType.APPLICATION_JSON).content(requestbody.getBytes()))
			                       .andDo(print())
			                       .andExpect(status().isOk())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			Logger.getGlobal().severe("getUserByIdJson >>>>>>: "+mvcResult.getResponse().getContentAsString());
			User user=jsonMapper.readValue(mvcResult.getResponse().getContentAsString(),User.class);
			Logger.getGlobal().severe("getUserByIdJson >>>>>>: "+user.getUserId());
			
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void getUserByIdXML()
	{
		try
		{
			String requestbody="";
			MvcResult mvcResult=mvc.perform(get("/rest/user/get/xml/1000005").contentType(MediaType.APPLICATION_XML).content(requestbody.getBytes()))
			                       .andDo(print())
			                       .andExpect(status().isOk())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			Logger.getGlobal().severe("getUserByIdXML >>>>>>: "+mvcResult.getResponse().getContentAsString());
			User user=xmlConverter.getObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),User.class);
			Logger.getGlobal().severe("getUserByIdXML >>>>>>: "+user.getUserId());
			
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void getUsersByUserNameJson()
	{
		try
		{
			String requestbody="";
			MvcResult mvcResult=mvc.perform(get("/rest/user/get/json/?un=admin").contentType(MediaType.APPLICATION_JSON).content(requestbody.getBytes()))
			                       .andDo(print())
			                       .andExpect(status().isOk())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			Logger.getGlobal().severe("getUsersByUserNameJson >>>>>>: "+mvcResult.getResponse().getContentAsString());
			List<User> users=jsonMapper.readValue(mvcResult.getResponse().getContentAsString(),new TypeReference<List<User>>()
			{
			});
			Logger.getGlobal().severe("getUsersByUserNameJson >>>>>>: "+users.get(0).getUserId());
			
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void getUsersByUserNameXML()
	{
		try
		{
			String requestbody="";
			MvcResult mvcResult=mvc.perform(get("/rest/user/get/xml/?un=admin").contentType(MediaType.APPLICATION_XML).content(requestbody.getBytes()))
			                       .andDo(print())
			                       .andExpect(status().isOk())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			Logger.getGlobal().severe("getUsersByUserNameXML >>>>>>: "+mvcResult.getResponse().getContentAsString());
			UserList<User> users=xmlConverter.getObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),new TypeReference<UserList<User>>()
			{
			});
			Logger.getGlobal().severe("getUsersByUserNameXML >>>>>>: "+users.getUser().get(0).getUserId());
			
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void delUser()
	{
		try
		{
			Logger.getGlobal().severe("delUser >>>>>>: "+userService.findAll().size());
			MvcResult mvcResult=mvc.perform(delete("/rest/user/del/1000003"))
			                       .andDo(print())
			                       .andExpect(status().isOk())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			Logger.getGlobal().severe("delUser >>>>>>: "+mvcResult.getResponse().getContentAsString());
			Logger.getGlobal().severe("delUser >>>>>>: "+userService.findAll().size());
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void createUser()
	{
		try
		{
			User user=new User();
			user.setActive(true);
			user.setEmail("a@b.com");
			user.setName("name");
			user.setSurname("surname");
			user.setUsername("username");
			user.setPassword("password");
			//
			Logger.getGlobal().severe("createUser >>>>>>: "+userService.findAll().size());
			String requestbody="";
			MvcResult mvcResult=mvc.perform(post("/rest/user/create")
			                                                         .header("Accept","application/json")
			                                                         .content(jsonMapper.writeValueAsBytes(user))
			                                                         .contentType(MediaType.APPLICATION_JSON))
			                       .andDo(print())
			                       .andExpect(status().isCreated())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(201));
			Logger.getGlobal().severe("createUser >>>>>>: "+mvcResult.getResponse().getContentAsString());
			Logger.getGlobal().severe("createUser >>>>>>: "+userService.findAll().size());
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username="admin",password="admin",roles=
	{"USER","ADMIN"})
	public void updateUser()
	{
		try
		{
			User user=userService.findByUserId(1000003L);
			user.setEmail("test@test.com");
			//
			Logger.getGlobal().severe("updateUser >>>>>>: "+userService.findByUserId(1000003L).getEmail());
			String requestbody="";
			MvcResult mvcResult=mvc.perform(put("/rest/user/update/1000003")
			                                                                 .header("Accept","application/json")
			                                                                 .content(jsonMapper.writeValueAsBytes(user))
			                                                                 .contentType(MediaType.APPLICATION_JSON))
			                       .andDo(print())
			                       .andExpect(status().isOk())
			                       .andReturn();
			MatcherAssert.assertThat(mvcResult.getResponse().getStatus(),Matchers.equalTo(200));
			Logger.getGlobal().severe("updateUser >>>>>>: "+mvcResult.getResponse().getContentAsString());
			Logger.getGlobal().severe("updateUser >>>>>>: "+userService.findByUserId(1000003L).getEmail());
		}
		catch(Exception rt)
		{
			rt.printStackTrace();
		}
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
			jsonMapper.readValue(mvcResult.getResponse().getContentAsString(),User.class);
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
