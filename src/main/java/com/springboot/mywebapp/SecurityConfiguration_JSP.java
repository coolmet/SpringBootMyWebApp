package com.springboot.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
@Order(value=1)
public class SecurityConfiguration_JSP extends WebSecurityConfigurerAdapter
{	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.antMatcher("/login/J").authorizeRequests()
		    .antMatchers(// @formatter:off
		                 "/**/favicon.ico",
		                 "/**/css/**",
		                 "/**/js/**",
		                 "/**/images/**",
				       "/**/fonts/**", 
				       "/**/webfonts/**", 
		                 "/**/webjars/**",
		                 "/login/J",
		                 "/**/logout",
		                 "logout",
		                 "/logout",
		                 "/",
		                 "/index",
		                 "/J",
		                 "/index/J"
		                 // @formatter:on
			)
		    .permitAll()
		    .antMatchers(// @formatter:off
		                 "/admin",
		                 "/admin/*",
		                 "/admin/**"
		                 // @formatter:on
			)
		    .access("hasRole('ADMIN')").anyRequest()
		    .authenticated()
		    .antMatchers(// @formatter:off
		 			  "/user",
					  "/user/*",
					  "/user/**"
					  // @formatter:on
			)
		    .access("hasRole('USER')").anyRequest()
		    .authenticated();
			
		http.formLogin().loginPage("/login/J")// .loginPage("/login")
		    .loginProcessingUrl("/login/J")// .loginProcessingUrl("/login")
		    .defaultSuccessUrl("/default/J",true)// .defaultSuccessUrl("/default",true)
		    .failureUrl("/login/J?loginFailed=true")// .failureUrl("/login?loginFailed=true")
		    .permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
		    .deleteCookies("remember_me_cookie").logoutRequestMatcher(new AntPathRequestMatcher("/**/logout"))
		    .logoutSuccessUrl("/login?logout").permitAll().and().requestCache().and().exceptionHandling()
		    .accessDeniedPage("/403").and().csrf().disable();
		
		// http.rememberMe().userDetailsService(userDetailsService).rememberMeServices(rememberMeServices());
		// http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
		// http.httpBasic();
		// http.csrf().disable();
		// http.headers().frameOptions().sameOrigin();
	}
	
}
