package com.outmao.ebs.mall.order.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
public class OrderProductLease {

	@Column(name = "is_lease")
	private boolean lease;

    @Column(name = "lease_start_time")
	private Date startTime;

	@Column(name = "lease_end_time")
	private Date endTime;

	@ApiModelProperty(name = "field", value = "1--分钟 2--小时 3--天 4--月 5--年")
	private Integer field=5;
	private Integer value=1;



}
