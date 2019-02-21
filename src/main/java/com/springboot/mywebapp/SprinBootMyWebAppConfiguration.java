package com.springboot.mywebapp;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SprinBootMyWebAppConfiguration
{
	@Autowired
	private SpringBootMyWebAppProperties springBootMyWebAppProperties;
	
	@PostConstruct
	public void init()
	{ 
		System.out.println(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE,"________________________________________",AnsiColor.DEFAULT));
		System.out.println("\u001B[31m @@@@@31: SprinBootMyWebAppConfiguration->init()->myPropValue :"+springBootMyWebAppProperties.isTestBooleanValue());
		System.out.println(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE,"________________________________________",AnsiColor.DEFAULT));
	}
}
