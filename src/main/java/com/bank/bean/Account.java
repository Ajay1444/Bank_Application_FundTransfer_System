package com.bank.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	@Max(value = 99999, message = "Account Number should be 5 digit !!")
	@Min(value = 9999, message = "Account Number should be 5 digit !!")
	private Long accountNumber;
	@NotNull(message = "Account Type shound not be Null!!")
	@Pattern(regexp = "^(?i)(Current|Saving|Salary)*$", message = " Account Type is not valid!!")
	private String accountType;
	@NotBlank(message = "Branch name shound not be Null or Empty!!")
	private String branchName;
	@NotBlank(message = "ifscCode shound not be Null or Empty!!")
	private String ifscCode;
	@Positive(message = "Please enter a valid amount !!")
	private Double balance;

	// @JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_cust")
	@JsonIgnoreProperties("account")
	@Valid
	private Customer customer;


}
