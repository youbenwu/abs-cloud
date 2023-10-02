package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.vo.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@ApiModel(value = "ProductAddressVO", description = "商品地址")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductAddressVO extends Address {

    /**
     *
     * ID
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "productId", value = "商品ID")
    private Long productId;

    /**
     *
     * 创建时间
     *
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     *
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


}
