package com.bank.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	@NotNull(message = "Account Type shound not be Empty!!")
	@Pattern(regexp = "^(?i)(Permanent|Current)*$", message = " Address Type is not valid!!")
	private String addressType;
	@NotBlank(message = "buildingName shound not be Null or Empty!!")
	private String buildingName;
	@NotBlank(message = "street shound not be Null or Empty!!")
	private String street;
	@NotBlank(message = "city shound not be Empty!!")
	private String city;
	@NotBlank(message = "postalCode shound not be Null or Empty!!")
	private String postalCode;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("address")
	private Customer customer;

	public Address(Integer addressId, String addressType, String buildingName, String street, String city,
			String postalCode) {
		super();
		this.addressId = addressId;
		this.addressType = addressType;
		this.buildingName = buildingName;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
	}

}
