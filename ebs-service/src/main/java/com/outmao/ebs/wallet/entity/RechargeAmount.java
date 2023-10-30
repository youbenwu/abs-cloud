package com.outmao.ebs.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Embeddable
public class RechargeAmount implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="recharge_currencyId")
	private String currencyId;

	@Column(name="recharge_amount")
	private double amount;



}
