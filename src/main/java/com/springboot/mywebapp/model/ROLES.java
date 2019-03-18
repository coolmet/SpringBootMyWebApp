package com.springboot.mywebapp.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ROLES
{
	
	ROLE_ADMIN("ROLE_ADMIN",0),
	ROLE_EDITOR("ROLE_EDITOR",1),
	ROLE_USER("ROLE_USER",2);
	
	public String stringValue;
	public int intValue;
	
	ROLES(String stringValue,int intValue)
	{
		this.stringValue=stringValue;
		this.intValue=intValue;
	}
	
	public String getStringValue()
	{
		return stringValue;
	}
	
	public int getIntValue()
	{
		return intValue;
	}
	
	public String getRoleName()
	{
		return this.toString();
	}
	
	public static String[] getEnumNames()
	{
		String valuesStr=Arrays.toString(ROLES.values());
		return valuesStr.substring(1,valuesStr.length()-1).replace(" ","").split(",");
	}
	
	public static List<String> getEnumNameArray()
	{
		return Arrays.asList(getEnumNames());
	}
	
	public static List<ROLES> getEnums()
	{
		return (List<ROLES>)Arrays.asList(ROLES.class.getEnumConstants());
	}
	
	public static List<ROLES> getEnumsReversed()
	{
		List<ROLES> x=(List<ROLES>)Arrays.asList(ROLES.class.getEnumConstants());
		Collections.sort(x,Collections.reverseOrder());
		return x;
	}
	
	public static ROLES getEnum(String enumName)
	{
		int index=getEnumNameArray().indexOf(enumName);
		return index==-1?null:getEnums().get(index);
	}
	
	public static String getEnumStringValue(String enumName)
	{
		int index=getEnumNameArray().indexOf(enumName);
		return index==-1?"":getEnums().get(index).stringValue;
	}
	
	public static int getEnumIntValue(String path)
	{
		int index=getEnumNameArray().indexOf(path);
		return index==-1?null:getEnums().get(index).intValue;
	}
	
	public void setStringValue(String stringValue)
	{
		this.stringValue=stringValue;
	}
	
	public void setIntValue(int intValue)
	{
		this.intValue=intValue;
	}
}
