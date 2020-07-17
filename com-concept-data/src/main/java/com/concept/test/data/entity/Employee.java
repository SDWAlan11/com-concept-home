package com.concept.test.data.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlRootElement (name = "employee")
@Entity(name="USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CVE_ROLE" ,discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("EMP")
public class Employee extends User 
{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="CVE_ROLE",insertable=false,updatable=false,nullable=true)
	private Role rol;

	@ManyToOne
	@JoinColumn(name="SUPERIOR_ID")
	private Employee superior;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="superior")
	private transient List<Employee> employeesInCharge;
	
	public Employee() {
		// Default
	}
	
	public Employee(String id, String firstName, String lastName, LocalDate birthDate) {
		super(id, firstName, lastName, birthDate);
	}

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	public Employee getSuperior() {
		return superior;
	}

	public void setSuperior(Employee superior) {
		this.superior = superior;
	}

	public List<Employee> getEmployeesInCharge() {
		return employeesInCharge;
	}

	public void setEmployeesInCharge(List<Employee> employeesInCharge) {
		this.employeesInCharge = employeesInCharge;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("rol", rol)
				.toString();
	}

	
	
}
