package com.concept.test.data.entity;

import org.hibernate.annotations.Fetch;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@MappedSuperclass
public abstract class Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Column(name="CREATED_DATETIME",nullable = false)
	@Basic(fetch = FetchType.LAZY)
	protected LocalDateTime createdDatetime;
	
	@Column(name="UPDATED_DATETIME")
	protected LocalDateTime updatedDatetime;
	
	@Column(name="CREATED_BY", nullable = false)
	protected String createdBy;
	
	@Column(name="UPDATED_BY")
	protected String updatedBy;

	@PrePersist
    public void prePersist() {
        setCreatedDatetime(LocalDateTime.now());
        setCreatedBy("SYSTEM");
    }
 
    @PreUpdate
    public void preUpdate() {
        setUpdatedDatetime(LocalDateTime.now());
        setUpdatedBy("SYSTEM");
    }

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public LocalDateTime getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(LocalDateTime updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
    
    
	
}
