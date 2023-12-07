package com.outmao.ebs.mall.order.entity;


import com.outmao.ebs.common.vo.TimeSpan;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
public class OrderProductLease extends TimeSpan {

	private boolean lease;

    @Column(name = "lease_start_time")
	private Date startTime;

	@Column(name = "lease_end_time")
	private Date endTime;





}
