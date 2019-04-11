package com.springboot.mywebapp.test;

import java.util.Arrays;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig
{
	@Bean
	@Primary
	public UserDetailsService userDetailsService()
	{
		// UserDetails ud=User.withDefaultPasswordEncoder()
		// .username("admin")
		// .password("admin")
		// .roles("ADMIN","USER")
		// .build();
		// return new InMemoryUserDetailsManager(Arrays.asList(ud));
		
		InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("admin").roles("ADMIN","USER").build());
		return manager;
		
	}
}
