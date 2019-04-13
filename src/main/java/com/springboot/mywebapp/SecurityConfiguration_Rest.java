package com.springboot.mywebapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(value=2)
@Configuration
public class SecurityConfiguration_Rest extends AbstractSecurityConfiguration
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//super.configure(http);
		http.antMatcher("/restadmin/*")
		    .authorizeRequests()
		    .antMatchers("/restadmin","/restadmin/*","/restadmin/**","/**/restadmin/**")
		    .access("hasRole('ADMIN')")
		    .anyRequest()
		    .authenticated()
		    .and()
		    .authorizeRequests()
		    .antMatchers("/restuser","/restuser/*","/restuser/**","/**/restuser/**")
		    .access("hasRole('USER','ADMIN')")
		    .anyRequest()
		    .authenticated()
		    .and()
		    .authorizeRequests()
		    .antMatchers("/rest","/rest/*","/rest/**","/**/rest/**")
		    .permitAll();
		http.csrf().disable();
		http.httpBasic();
	}
}
