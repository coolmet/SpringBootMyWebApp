package com.springboot.mywebapp.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid",updatable=false,nullable=false)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="userSeqGen")
	//@SequenceGenerator(name="userSeqGen",sequenceName="users_id_seq")
	private Long userid;
	
	public Long getUserId()
	{
		return userid;
	}

	public void setUserId(Long userid)
	{
		this.userid=userid;
	}

	@CreatedBy
	@Column(name="created_by")
	private String createdBy;
	
	@CreatedDate
	@Column(name="created_date")
	private Date createdDate;
	
	@LastModifiedBy
	@Column(name="updated_by")
	private String updatedBy;
	
	@LastModifiedDate
	@Column(name="updated_date")
	private Date updatedDate;
}
