package com.springboot.mywebapp;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true,jsr250Enabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
		    .withUser("user1").password(passwordEncoder().encode("user1")).roles("USER")
		    .and()
		    .withUser("user2").password(passwordEncoder().encode("user2")).roles("USER")
		    .and()
		    .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		    .antMatchers("/**/favicon.ico","/css/**","js/**","/images/**","/webjars/**","/login").permitAll()
		    .antMatchers("/admin","/admin/*","/admin/**").access("hasRole('ADMIN')")
		    .anyRequest().authenticated();
		
		http.formLogin()
		    .loginPage("/login")
		    .loginProcessingUrl("/login")
		    .defaultSuccessUrl("/default")
		    .failureUrl("/login?loginFailed=true");
		http.rememberMe().userDetailsService(userDetailsService);
		http
		    .sessionManagement()
		    .maximumSessions(1).sessionRegistry(sessionRegistry());
		// http.httpBasic();
		// http.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SessionRegistry sessionRegistry()
	{
		return new SessionRegistryImpl();
	}
}
