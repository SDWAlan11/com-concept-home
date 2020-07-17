package com.concept.test.data.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.concept.test.data.entity.adapter.LocalDateAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class User extends Audit
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GenericGenerator(
	        name = "assigned-user-sequence",
	        strategy = "com.concept.test.data.entity.id.UserIdGenerator"
	    )
	    @GeneratedValue(
	        generator = "assigned-user-sequence",
	        strategy = GenerationType.SEQUENCE)
	protected String id;
	
	@Column(name="FIRST_NAME")
	protected String firstName;
	
	@Column(name="LAST_NAME")
	protected String lastName;
	
	@Column(name="BIRTH_DATE")
	protected LocalDate birthDate;
	
	@Column(name="EMAIL")
	protected String email;
	
	public User() {
		// Default constructor
	}
	
	public User(String id, String firstName, String lastName, LocalDate birthDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		 if (obj == this) {
	            return true;
	        }

	        if (!(obj instanceof User)) {
	            return false;
	        }	
	        
	        User that = (User) obj;
	        return new EqualsBuilder()
	        		.append(this.id, that.id)
	        		.isEquals();        
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
