package com.springboot.mywebapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(value=0)
public class SecurityConfiguration_H2 extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.antMatcher("/h2-console/*")
		    .authorizeRequests()
		    .antMatchers("/h2-console","/h2-console/*","/h2-console/**","/**/h2-console/**")
		    .access("hasRole('ADMIN')")
		    .anyRequest()
		    .authenticated();
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();
		// http.headers().frameOptions().disable();
	}
}
