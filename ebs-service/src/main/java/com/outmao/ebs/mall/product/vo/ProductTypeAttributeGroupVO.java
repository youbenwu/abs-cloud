package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;


@ApiModel(value = "ProductTypeAttributeGroupVO", description = "商品类型参数分组")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductTypeAttributeGroupVO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "typeId", value = "商品类型ID")
    private Long typeId;
    /**
     *
     * 分组KEY
     *
     */
    @ApiModelProperty(name = "key", value = "分组KEY")
    private String key;

    /**
     *
     * 分组名称
     *
     */
    @ApiModelProperty(name = "name", value = "分组名称")
    private String name;

    /**
     *
     * 参数列表
     *
     *
     */
    @ApiModelProperty(name = "attributes", value = "参数列表")
    private List<ProductTypeAttributeVO> attributes;


}
