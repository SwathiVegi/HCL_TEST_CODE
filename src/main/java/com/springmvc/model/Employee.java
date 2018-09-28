package com.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min=3, max=50)
	@Column(name = "FNAME", nullable = false)
	private String firstName;
	
	@Size(min=3, max=50)
	@Column(name = "LNAME", nullable = false)
	private String lastName;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	@Column(name = "JOINING_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate enteringDate;

	@NotNull
	@Column(name = "nationality", nullable = false)
	private String nationality;
	
	@NotEmpty
	@Column(name = "EMP_NUMBER", unique=true, nullable = false)
	private String code;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setEnteringDate(LocalDate enteringDate) {
		this.enteringDate = enteringDate;
	}
	
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getCode() {
		return code;
	}
	
	public LocalDate getEnteringDate() {
		return enteringDate;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + firstName + ", enteringDate="
				+ enteringDate + ", nationality=" + nationality + ", code=" + code + "]";
	}
	
	
	

}
