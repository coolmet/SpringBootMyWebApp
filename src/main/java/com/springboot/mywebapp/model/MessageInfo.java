package com.springboot.mywebapp.model;

public class MessageInfo
{
	String message;
	boolean status;
	
	public MessageInfo()
	{
		super();
	}
	
	public MessageInfo(String message,boolean status)
	{
		super();
		this.message=message;
		this.status=status;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message=message;
	}
	
	public boolean isStatus()
	{
		return status;
	}
	
	public void setStatus(boolean status)
	{
		this.status=status;
	}
	
}
