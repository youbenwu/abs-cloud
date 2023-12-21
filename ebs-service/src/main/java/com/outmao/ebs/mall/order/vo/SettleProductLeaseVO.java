package com.outmao.ebs.mall.order.vo;

import com.outmao.ebs.common.vo.TimeSpan;
import lombok.Data;

import java.util.Date;

@Data
public class SettleProductLeaseVO extends TimeSpan {

    private Date startTime;

    private Date endTime;

}
