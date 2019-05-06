package com.springboot.mywebapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class SprinBootMyWebAppConfiguration
{
	@Autowired
	private SpringBootMyWebAppProperties springBootMyWebAppProperties;
	
	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	
	@PostConstruct
	public void init()
	{
		requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
		
		System.out.println(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE,"________________________________________\n",AnsiColor.DEFAULT));
		System.out.println("\u001b[40m \u001B[38;5;220m @@@@@: SprinBootMyWebAppConfiguration->init()->myPropValue \u001b[0m \u001B[31m"+springBootMyWebAppProperties.isTestBooleanValue()+"\t"+new SimpleDateFormat("dd.MM.yyyy hh:mm:ss.SSS").format(new Date()));
		System.out.println(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE,"________________________________________",AnsiColor.DEFAULT));
	}
}
