package com.outmao.ebs.mall.takeLook.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "TakeLookDTO", description = "创建带看")
@Data
public class TakeLookDTO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

   /**
     *
     * 经纪人ID
     *
     */
    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;

    /**
     *
     * 带看经纪人ID
     *
     */
    @ApiModelProperty(name = "waiterId", value = "带看经纪人ID")
    private Long waiterId;


    /**
     *
     * 客户ID
     *
     */
    @ApiModelProperty(name = "customerId", value = "客户ID")
    private Long customerId;

    /**
     *
     * 预约时间
     *
     */
    @ApiModelProperty(name = "appointmentTime", value = "预约时间")
    private Date appointmentTime;

    @ApiModelProperty(name = "products", value = "带看商品")
    private List<TakeLookProductDTO> products;


}
