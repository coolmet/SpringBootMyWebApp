package com.springboot.mywebapp;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
		    .withUser("user1").password(passwordEncoder().encode("user1")).roles("USER")
		    .and()
		    .withUser("user2").password(passwordEncoder().encode("user2")).roles("USER")
		    .and()
		    .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
	}
	
	@Configuration
	@Order(1)
	class THSecurity extends WebSecurityConfigurerAdapter
	{
		public THSecurity()
		{
			super();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			http
			    .authorizeRequests()
			    .antMatchers("/**/favicon.ico","/css/**","js/**","/images/**","/webjars/**","/login/th").permitAll()
			    .antMatchers("/admin","/admin/*","/admin/**").access("hasRole('ADMIN')")
			    .anyRequest().authenticated();
			
			http.formLogin()
			    .loginPage("/login/th")// .loginPage("/login")
			    .loginProcessingUrl("/login/th")// .loginProcessingUrl("/login")
			    .defaultSuccessUrl("/default/th",true)// .defaultSuccessUrl("/default",true)
			    .failureUrl("/login/th?loginFailed=true")// .failureUrl("/login?loginFailed=true")
			    .permitAll()
			    .and()
			    .logout()
			    .invalidateHttpSession(true)
			    .clearAuthentication(true)
			    .deleteCookies("remember_me_cookie")
			    .logoutRequestMatcher(new AntPathRequestMatcher("/**/logout"))
			    .logoutSuccessUrl("/login?logout")
			    .permitAll()
			    .and()
			    .exceptionHandling()
			    .accessDeniedPage("/403")
			    .and()
			    .csrf()
			    .disable();
			
			http
			    .rememberMe()
			    .userDetailsService(userDetailsService)
			    .rememberMeServices(rememberMeServices());
			http
			    .sessionManagement()
			    .maximumSessions(1).sessionRegistry(sessionRegistry());
			// http.httpBasic();
			// http.csrf().disable();
		}
	}
	
	@Configuration
	@Order(2)
	class JSPSecurity extends WebSecurityConfigurerAdapter
	{
		public JSPSecurity()
		{
			super();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			http
			    .authorizeRequests()
			    .antMatchers("/**/favicon.ico","/css/**","js/**","/images/**","/webjars/**","/login").permitAll()
			    .antMatchers("/admin","/admin/*","/admin/**").access("hasRole('ADMIN')")
			    .anyRequest().authenticated();
			
			http.formLogin()
			    .loginPage("/login")// .loginPage("/login")
			    .loginProcessingUrl("/login")// .loginProcessingUrl("/login")
			    .defaultSuccessUrl("/default",true)// .defaultSuccessUrl("/default",true)
			    .failureUrl("/login?loginFailed=true")// .failureUrl("/login?loginFailed=true")
			    .permitAll()
			    .and()
			    .logout()
			    .invalidateHttpSession(true)
			    .clearAuthentication(true)
			    .deleteCookies("remember_me_cookie")
			    .logoutRequestMatcher(new AntPathRequestMatcher("/**/logout"))
			    .logoutSuccessUrl("/login?logout")
			    .permitAll()
			    .and()
			    .exceptionHandling()
			    .accessDeniedPage("/403")
			    .and()
			    .csrf()
			    .disable();
			
			http
			    .rememberMe()
			    .userDetailsService(userDetailsService)
			    .rememberMeServices(rememberMeServices());
			http
			    .sessionManagement()
			    .maximumSessions(1).sessionRegistry(sessionRegistry());
			// http.httpBasic();
			// http.csrf().disable();
		}
	}
	
	@Bean
	public RememberMeServices rememberMeServices()
	{
		// Key must be equal to rememberMe().key()
		TokenBasedRememberMeServices rememberMeServices=new TokenBasedRememberMeServices("your_key",userDetailsService);
		rememberMeServices.setCookieName("remember_me_cookie");
		rememberMeServices.setParameter("remember_me_checkbox");
		rememberMeServices.setTokenValiditySeconds(2678400); // 1month
		return rememberMeServices;
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
