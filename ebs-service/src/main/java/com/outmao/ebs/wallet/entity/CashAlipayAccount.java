package com.outmao.ebs.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Embeddable
public class CashAlipayAccount implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="alipay_realname")
	private String name;

	@Column(name="alipay_account")
	private String account;


}
