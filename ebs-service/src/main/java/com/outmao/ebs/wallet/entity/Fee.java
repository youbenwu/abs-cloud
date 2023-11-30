package com.outmao.ebs.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Fee implements Serializable {

	//0-1000
	private int rate;

	private long max;

	private long min;


	public long getFee(long amount) {
		if (amount == 0)
			return 0;
		long fee = amount*rate/1000;
		if (min > 0) {
			fee = Math.max(min, fee);
		}
		if (max > 0) {
			fee = Math.min(max, fee);
		}
		return fee;
	}


}
