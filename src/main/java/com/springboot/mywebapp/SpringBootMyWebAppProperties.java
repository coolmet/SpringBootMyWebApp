package com.springboot.mywebapp;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="myprop")
public class SpringBootMyWebAppProperties
{
	private boolean testBooleanValue=false;
	
	public boolean isTestBooleanValue()
	{
		return testBooleanValue;
	}
	
	public void setTestBooleanValue(boolean testBooleanValue)
	{
		this.testBooleanValue=testBooleanValue;
	}
	
}
