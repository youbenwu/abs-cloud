package com.outmao.ebs.mall.product.dto;


import com.outmao.ebs.mall.product.entity.ProductLease;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@ApiModel(value = "ProductTypeDTO", description = "保存商品类型参数")
@Data
public class ProductTypeDTO {

    /**
     * 唯一不变标识
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;


    /**
     *
     * 商品类型
     *
     */
    @ApiModelProperty(name = "type", value = "类型")
    private int type;

    /**
     *
     * 类型名称
     *
     */
    @ApiModelProperty(name = "name", value = "类型名称")
    private String name;

    /**
     *
     * 类型描述
     *
     */
    @ApiModelProperty(name = "description", value = "类型描述")
    private String description;


    @ApiModelProperty(name = "attributes", value = "参数列表")
    private List<ProductTypeAttributeGroupDTO> attributes;


    @ApiModelProperty(name = "attributes", value = "属性列表")
    private List<ProductTypePropertyDTO> propertys;

    /**
     *
     * 是否无需发货
     *
     */
    private boolean noDelivery;


    /**
     *
     * 租赁信息
     *
     */
    private ProductLease lease;

    /**
     *
     * 是否允许商家标记签收
     *
     */
    private boolean sellerFinish;

}
