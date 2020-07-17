package com.concept.test.data.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@DiscriminatorValue("MAN")
public class Manager extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="superior")
	private List<Employee> empleadosACargo;

	public List<Employee> getEmpleadosACargo() {
		return empleadosACargo;
	}

	public void setEmpleadosACargo(List<Employee> empleadosACargo) {
		this.empleadosACargo = empleadosACargo;
	}
	
	@Override
    public String toString() 
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString()).toString();
    }
}
