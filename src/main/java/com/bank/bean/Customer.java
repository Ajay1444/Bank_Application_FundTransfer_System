package com.bank.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer custId;
	@NotNull(message = "customer Name shound not be Null!!")
	@Pattern(regexp = "^[A-Za-z ]{1,50}$", message = " customerName is not valid!!")
	private String customerName;
	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("customer")
	@JoinColumn(name = "cp_fk", referencedColumnName = "custId")
	@Valid
	private List<Address> address = new ArrayList<>();
	@NotNull(message = "mobileNumber shound not be Null!!")
	@Pattern(regexp = "^[0-9]{10}$", message = " mobileNumber is not valid!!")
	private String mobileNumber;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonIgnoreProperties("customer")
	private Account account;

	public Customer(Integer custId, String customerName, List<Address> address, String mobileNumber) {
		super();
		this.custId = custId;
		this.customerName = customerName;
		this.address = address;
		this.mobileNumber = mobileNumber;
	}

}
