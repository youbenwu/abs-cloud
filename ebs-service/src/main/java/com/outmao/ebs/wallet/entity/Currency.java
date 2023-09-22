package com.outmao.ebs.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/*
 *
 * 币种
 * 
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ew_Currency")
public class Currency implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 币种ID
	 */
	@Id
	private String id;

	/*
	 * 币种名称，如‘人民币’
	 */
	private String name;

	/*
	 * 币种单位，如‘元’
	 */
	private String unit;

	/*
	 * 单位数量，货币值/oneUnit=单位值
	 */
	private long oneUnit;

	/*
	 * 
	 * 转换成基准货币的单位价值
	 * 
	 */
	private long par;

	/*
	 * 
	 * 是否可提现
	 * 
	 */
	private boolean cash;

	/*
	 * 
	 * 提现费率
	 * 
	 */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "rate", column = @Column(name = "cash_fee_rate")),
			@AttributeOverride(name = "max", column = @Column(name = "cash_fee_max")),
			@AttributeOverride(name = "min", column = @Column(name = "cash_fee_min")),
	})
	private Fee cashFee;


	/*
	 * 创建时间
	 */
	private Date createTime;

	/*
	 * 更新时间
	 */
	private Date updateTime;


	public long getCashFee(long amount) {
		if(cashFee==null)
			return 0;
		return cashFee.getFee(amount);
	}

	@Override
	public String toString() {
		return id;
	}

	public int hashCode() {
		return (this.id == null) ? 0 : this.id.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof Currency) {
			final Currency obj = (Currency) object;
			return Objects.equals(this.id,obj.id);
		}
		if (object instanceof String) {
			return object.equals(id);
		}
		return false;
	}


}
