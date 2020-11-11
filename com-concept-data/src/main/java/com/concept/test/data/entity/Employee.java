package com.concept.test.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	private int employeeNumber;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String extension;

	@Column(nullable = false)
	private String firstName;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private JobTitle jobTitle;

	@Column(nullable = false)
	private String lastName;
	
	@Lob
	@ToString.Exclude
	@JsonIgnore
	private String password;
	
	//bi-directional many-to-one association to Customer
	@JsonIgnore
	@OneToMany(mappedBy="employee",fetch = FetchType.LAZY)
	private List<Customer> customers;

	//bi-directional many-to-one association to Employee	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="reportsTo")
	private Employee employee;

	//bi-directional many-to-one association to Employee
	@JsonIgnore
	@OneToMany(mappedBy="employee")
	private List<Employee> employees;

	//bi-directional many-to-one association to Office
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name="officeCode", nullable = false)
	private Office office;
	
	@JsonGetter
	public String getOfficeCode() {
		return office.getOfficeCode();
	}
	
	@JsonGetter
	public int getSuperior() {
		return employee.getEmployeeNumber();
	}

	public enum JobTitle {
		    PRESIDENT,
		    VPSALES,
		    VPMARKETING,
		    SALESMANAGER,
		    SALESREP
	 }
	 
	
	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setEmployee(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setEmployee(null);

		return customer;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEmployee(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEmployee(null);

		return employee;
	}


	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", email=" + email + ", extension=" + extension
				+ ", firstName=" + firstName + ", jobTitle=" + jobTitle + ", lastName=" + lastName + ", office="
				+ office.getOfficeCode() + "]";
	}
}