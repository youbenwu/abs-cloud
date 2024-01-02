package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "ProductDistributionVO", description = "商品分销信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDistributionVO  {


    /**
     *
     * 商品ID
     *
     */
    @ApiModelProperty(name = "id", value = "商品ID")
    private Long id;


    /**
     *
     * 是否启用分销
     *
     * */
    @ApiModelProperty(name = "distribution", value = "是否启用分销")
    private boolean distribution;


    /**
     *
     * 佣金类型 0固定/1按比例
     *
     */
    @ApiModelProperty(name = "commissionType", value = "佣金类型 0固定/1按比例")
    private Integer commissionType;

    /**
     *
     * 佣金 按比例(0~1)
     *
     */
    @ApiModelProperty(name = "commissionRate", value = "佣金 按比例(0~1)")
    private Double commissionRate;

    /**
     *
     * 佣金 固定金额
     *
     */
    @ApiModelProperty(name = "commissionAmount", value = "佣金 固定金额")
    private Double commissionAmount;


}
