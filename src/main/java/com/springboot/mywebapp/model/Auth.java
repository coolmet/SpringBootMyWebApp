package com.springboot.mywebapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="DB_AUTHORITIES")
public class Auth
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="authid",updatable=false,nullable=false)
	private Long authid;
	
	public Long getAuthId()
	{
		return authid;
	}
	
	public void setAuthId(Long authid)
	{
		this.authid=authid;
	}
	
	@NotEmpty
	@Column(name="username")
	private String username;
	
	@NotEmpty
	@Column(name="authority")
	private String authority;
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	public String getAuthority()
	{
		return authority;
	}
	
	public void setAuthority(String authority)
	{
		this.authority=authority;
	}
	
	@Override
	public String toString()
	{
		return "Auth [username="+username+", authority="+authority+"]";
	}
	
}
