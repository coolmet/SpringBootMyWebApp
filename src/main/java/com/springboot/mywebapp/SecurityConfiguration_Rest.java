package com.springboot.mywebapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(value=2)
@Configuration
public class SecurityConfiguration_Rest extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		super.configure(http);
		http.antMatcher("/rest/*")
		    .authorizeRequests()
		    .antMatchers("/rest","/rest/*","/rest/**","/**/rest/**")
		    .access("hasRole('ADMIN')")
		    .anyRequest()
		    .authenticated();
		http.csrf().disable();
		http.httpBasic();
	}
}
