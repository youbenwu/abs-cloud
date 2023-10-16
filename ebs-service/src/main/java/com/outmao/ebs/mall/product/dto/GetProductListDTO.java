package com.outmao.ebs.mall.product.dto;

import com.outmao.ebs.common.vo.Between;
import com.outmao.ebs.common.vo.Location;
import com.outmao.ebs.common.vo.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


@ApiModel(value = "GetProductListDTO", description = "获取商品列表参数对象")
@Data
public class GetProductListDTO extends PageDTO {

    @ApiModelProperty(name = "attrs", value = "反回哪些KEY参数数据")
    private String[] attrs;

    @ApiModelProperty(name = "categoryId", value = "商品分类ID")
    private Long categoryId;

    @ApiModelProperty(name = "spcId", value = "店铺中的商品分类ID")
    private Long spcId;

    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(name = "storeId", value = "门店ID，查找门店关联的商品")
    private Long storeId;

    @ApiModelProperty(name = "keyword", value = "关健字")
    private String keyword;


    @ApiModelProperty(name = "type", value = "商品类型")
    private Integer type;


    @ApiModelProperty(name = "onSell", value = "商品是否上架")
    private Boolean onSell;

    @ApiModelProperty(name = "status", value = "商品状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核失败)")
    private Integer status;


    @ApiModelProperty(name = "priceBetween", value = "价格区间")
    private Between<Double> priceBetween;


    @ApiModelProperty(name = "salesStatus", value = "销售状态（0待售/1在售/2售罄）")
    private Integer salesStatus;


    @ApiModelProperty(name = "marketTimeBetween", value = "开盘时间区间")
    private Between<Date> marketTimeBetween;


    @ApiModelProperty(name = "near", value = "经纬度 查找附近商品")
    private Location near;


}
