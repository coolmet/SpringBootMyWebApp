package com.springboot.mywebapp;

import java.util.HashMap;
import java.util.Map;
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
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true,jsr250Enabled=true)
@EnableWebSecurity
public class SecurityConfiguration
{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		// auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("user1")).roles("USER").and()
		// .withUser("user2").password(passwordEncoder().encode("user2")).roles("USER").and().withUser("admin")
		// .password(passwordEncoder().encode("admin")).roles("ADMIN");
		//
		// auth.jdbcAuthentication().dataSource(dataSource);
		//
		auth
		    .jdbcAuthentication()
		    .dataSource(dataSource)
		    .usersByUsernameQuery("select username,password,active from DB_USERS where username=?")
		    .authoritiesByUsernameQuery("select username, authority from DB_AUTHORITIES where username=?")
		    .passwordEncoder(passwordEncoder())
		// .withDefaultSchema().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
		;
		
	}
	
	@Configuration
	@Order(99)
	class THSecurity extends WebSecurityConfigurerAdapter
	{
		public THSecurity()
		{
			super();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			http.authorizeRequests()
			    .antMatchers(// @formatter:off
					       "/**/favicon.ico", 
					       "/**/css/**", 
					       "/**/js/**", 
					       "/**/images/**", 
					       "/**/fonts/**", 
					       "/**/webfonts/**", 
					       "/**/webjars/**",
					       "/myweb/**",
					       "/login", 
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
				
			http.formLogin().loginPage("/login")// .loginPage("/login")
			    .loginProcessingUrl("/login")// .loginProcessingUrl("/login")
			    .defaultSuccessUrl("/default",true)// .defaultSuccessUrl("/default",true)
			    .failureUrl("/login?loginFailed=true")// .failureUrl("/login?loginFailed=true")
			    .permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
			    .deleteCookies("remember_me_cookie").logoutRequestMatcher(new AntPathRequestMatcher("/**/logout"))
			    .logoutSuccessUrl("/login?logout").permitAll().and().requestCache().and().exceptionHandling()
			    .accessDeniedPage("/403").and().csrf().disable();
			
			http.rememberMe().userDetailsService(userDetailsService).rememberMeServices(rememberMeServices());
			http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
			// http.httpBasic();
			// http.csrf().disable();
			// http.headers().frameOptions().sameOrigin();
		}
	}
	
	@Bean
	public RememberMeServices rememberMeServices()
	{
		// Key must be equal to rememberMe().key()
		TokenBasedRememberMeServices rememberMeServices=new TokenBasedRememberMeServices("your_key",
		                                                                                 userDetailsService);
		rememberMeServices.setCookieName("remember_me_cookie");
		rememberMeServices.setParameter("remember_me_checkbox");
		rememberMeServices.setTokenValiditySeconds(2678400); // 1month
		return rememberMeServices;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		Map encoders=new HashMap<>();
		encoders.put("bcrypt",new BCryptPasswordEncoder());
		encoders.put("noop",NoOpPasswordEncoder.getInstance());
		encoders.put("pbkdf2",new Pbkdf2PasswordEncoder());
		encoders.put("scrypt",new SCryptPasswordEncoder());
		encoders.put("sha256",new StandardPasswordEncoder());
		// return new BCryptPasswordEncoder();
		PasswordEncoder passwordEncoder=new DelegatingPasswordEncoder("bcrypt",encoders);
		return passwordEncoder;
	}
	
	@Bean
	public SessionRegistry sessionRegistry()
	{
		return new SessionRegistryImpl();
	}
}
