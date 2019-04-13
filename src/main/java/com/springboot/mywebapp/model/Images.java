package com.springboot.mywebapp.model;

import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="DB_IMAGES")
@XmlRootElement
public class Images
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",updatable=false,nullable=false)
	private Long id;
	
	@NotEmpty
	@Column(name="imagetype")
	private String imagetype;
	
	@NotEmpty
	@Column(name="name")
	private String name;
	
	@Lob
	@Column(name="data",columnDefinition="BLOB")
	private byte[] data;
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id=id;
	}
	
	public String getImagetype()
	{
		return imagetype;
	}
	
	public void setImagetype(String imagetype)
	{
		this.imagetype=imagetype;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public byte[] getData()
	{
		return data;
	}
	
	public void setData(byte[] data)
	{
		this.data=data;
	}
	
	@Override
	public String toString()
	{
		return "Images [id="+id+", imagetype="+imagetype+", name="+name+", data="+Arrays.toString(data)+"]";
	}
	
}
