package com.outmao.ebs.common.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@Data
@Embeddable
@MappedSuperclass
public class LogisticsInfo {

    /**
     *
     * 物流公司名称
     *
     */
    @ApiModelProperty(name = "logistics_company", value = "物流公司名称")
    private String company;
    /**
     * 物流公司ID
     */
    @ApiModelProperty(name = "logistics_company_id", value = "物流公司ID")
    private Long companyId;

    /**
     * 物流单号
     */
    @ApiModelProperty(name = "logistics_order_no", value = "物流单号")
    private String orderNo;

}
