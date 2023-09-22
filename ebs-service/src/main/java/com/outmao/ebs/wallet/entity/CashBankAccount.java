package com.outmao.ebs.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Embeddable
public class CashBankAccount implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="bank_name")
	private String bankName;

	@Column(name="bank_account_number")
	private String accountNumber;

	@Column(name="bank_account_name")
	private String accountName;


}
