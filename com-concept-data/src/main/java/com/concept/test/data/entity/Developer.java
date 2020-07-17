package com.concept.test.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@DiscriminatorValue("DEV")
public class Developer extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String toString() 
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .toString();
    }
}
