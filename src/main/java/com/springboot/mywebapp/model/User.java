package com.springboot.mywebapp.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="DB_USERS")
@XmlRootElement
public class User extends BaseEntity
{
	@NotEmpty
	@Column(name="username")
	private String username;
	private String password;
	private boolean active=false;
	
	// @Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	// @Column(name="userid",updatable=false,nullable=false)
	// private Long userid;
	//
	// public Long getUserId()
	// {
	// return userid;
	// }
	//
	// public void setUserId(Long userid)
	// {
	// this.userid=userid;
	// }
	
	private String name;
	private String surname;
	
	@NotEmpty
	@Column(name="email")
	private String email;
	private String confirmationtoken;
	private Date createdate=new Date();
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username=username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void setActive(boolean active)
	{
		this.active=active;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public void setSurname(String surname)
	{
		this.surname=surname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}
	
	public String getConfirmationtoken()
	{
		return confirmationtoken;
	}
	
	public void setConfirmationtoken(String confirmationtoken)
	{
		this.confirmationtoken=confirmationtoken;
	}
	
	public Date getCreatedate()
	{
		return createdate;
	}
	
	public void setCreatedate(Date createdate)
	{
		this.createdate=createdate;
	}

	@Override
	public String toString()
	{
		return "User [username="+username+", password="+password+", active="+active+", name="+name+", surname="+surname+", email="+email+", confirmationtoken="+confirmationtoken+"]";
	}
	
	
	
}
