package com.concept.test.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the offices database table.
 * 
 */
@Entity
@Table(name="offices")
@NamedQuery(name="Office.findAll", query="SELECT o FROM Office o")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class Office extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String officeCode;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String country;

	private String phone;

	private String postalCode;

	private String state;

	private String territory;

	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy="office" ,cascade = CascadeType.PERSIST,orphanRemoval = true)
	private List<Employee> employees = new ArrayList<Employee>();

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setOffice(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setOffice(null);

		return employee;
	}

}