package com.springboot.mywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value=SpringBootMyWebAppProperties.class)
@ServletComponentScan
public class SpringBootWebAppApplicationStarter
{
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(SpringBootWebAppApplicationStarter.class,args);
		
		// new SpringApplicationBuilder()
		// .sources(SpringBootWebAppApplicationStarter.class)
		// .profiles("prod")
		// .run(args);
		
	}
}
