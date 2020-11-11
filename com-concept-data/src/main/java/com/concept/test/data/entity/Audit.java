package com.concept.test.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.ToString;

@MappedSuperclass
@Data
@ToString
public abstract class Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Column(name="CREATED_DATETIME",nullable = false, updatable = false)
	@Basic(fetch = FetchType.LAZY)
	protected LocalDateTime createdDatetime;
	
	@Column(name="UPDATED_DATETIME")
	protected LocalDateTime updatedDatetime;
	
	@Column(name="CREATED_BY", nullable = false, updatable = false)
	protected String createdBy;
	
	@Column(name="UPDATED_BY")
	protected String updatedBy;

	@PrePersist
    public void prePersist() {
		this.createdBy = "SYSTEM";
        this.createdDatetime = LocalDateTime.now();
    }
 
    @PreUpdate
    public void preUpdate() {
        this.updatedBy = "SYSTEM";
        this.updatedDatetime = LocalDateTime.now();
    }	
}
