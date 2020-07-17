package com.concept.test.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity(name="ROLE")
public class Role implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CVE_ROLE")
	private String cveRole;
	
	@Column(name="DESC_ROLE")
	private String descRole;
		
	 public String getCveRole() {
		return cveRole;
	}

	public void setCveRole(String cveRole) {
		this.cveRole = cveRole;
	}

	public String getDescRole() {
		return descRole;
	}

	public void setDescRole(String descRole) {
		this.descRole = descRole;
	}

	@Override
	    public String toString() 
	    {
	        return new ToStringBuilder(this)
	                .append("descRol", descRole).toString();
	    }
}
