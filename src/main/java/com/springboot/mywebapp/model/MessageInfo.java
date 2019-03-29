package com.springboot.mywebapp.model;

public class MessageInfo
{
	String message;
	boolean status;
	User user;
	
	public MessageInfo()
	{
		super();
		message="";
		status=false;
		user=emptyUser();
	}
	
	public MessageInfo(String message,boolean status)
	{
		super();
		this.message=message;
		this.status=status;
	}
	
	public MessageInfo(String message,boolean status,User user)
	{
		super();
		this.message=message;
		this.status=status;
		this.user=user;
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
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user=user;
	}
	
	private User emptyUser()
	{
		User user=new User();
		user.setActive(false);
		user.setUserId(Long.parseLong("0"));
		user.setConfirmationtoken("");
		user.setPassword("");
		user.setEmail("");
		user.setName("");
		user.setSurname("");
		user.setUsername("");
		return user;
	}
}
