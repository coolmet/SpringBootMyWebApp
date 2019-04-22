package com.springboot.mywebapp.model;

import java.util.Locale;

public class Language
{
	private int id;
	private String imagePath;
	private String displayName;
	private String localeName;
	private String localeLongName;
	private Locale locale;
	
	public Language()
	{
	}
	
	public Language(int id,String imagePath,String displayName,String localeName,String localeLongName,Locale locale)
	{
		super();
		this.id=id;
		this.imagePath=imagePath;
		this.displayName=displayName;
		this.localeName=localeName;
		this.localeLongName=localeLongName;
		this.locale=locale;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getImagePath()
	{
		return imagePath;
	}
	
	public void setImagePath(String imagePath)
	{
		this.imagePath=imagePath;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public void setDisplayName(String displayName)
	{
		this.displayName=displayName;
	}
	
	public String getLocaleName()
	{
		return localeName;
	}
	
	public void setLocaleName(String localeName)
	{
		this.localeName=localeName;
	}
	
	public String getLocaleLongName()
	{
		return localeLongName;
	}
	
	public void setLocaleLongName(String localeLongName)
	{
		this.localeLongName=localeLongName;
	}
	
	public Locale getLocale()
	{
		return locale;
	}
	
	public void setLocale(Locale locale)
	{
		this.locale=locale;
	}
	
}
