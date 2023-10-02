package com.outmao.ebs.mall.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "GetHouseListDTO", description = "获取房子列表参数对象")
@Data
public class GetHouseListDTO  {


    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(name = "storeId", value = "门店ID，查找门店关联的商品")
    private Long storeId;

    @ApiModelProperty(name = "keyword", value = "关健字")
    private String keyword;

    @ApiModelProperty(name = "categoryId", value = "商品分类ID")
    private Integer categoryId;

    @ApiModelProperty(name = "type", value = "商品类型 0普通商品 11新楼盘 12二手房 13出租房")
    private Integer type;


    @ApiModelProperty(name = "minPrice", value = "价格区间")
    private Double minPrice;
    @ApiModelProperty(name = "minPrice", value = "价格区间")
    private Double maxPrice;


    @ApiModelProperty(name = "houseRoomNum", value = "户型房间数量")
    private Integer houseRoomNum;

    @ApiModelProperty(name = "maxHouseRoomNum", value = "户型房间数量区间")
    private Integer maxHouseRoomNum;


    @ApiModelProperty(name = "minMarketTime", value = "开盘时间区间")
    private Date minMarketTime;

    @ApiModelProperty(name = "minMarketTime", value = "开盘时间区间")
    private Date maxMarketTime;

    @ApiModelProperty(name = "city", value = "城市")
    private String city;

    @ApiModelProperty(name = "district", value = "区")
    private String district;

    @ApiModelProperty(name = "subway", value = "地铁")
    private String subway;

    @ApiModelProperty(name = "latitude", value = "纬度")
    private Double latitude;// 纬度

    @ApiModelProperty(name = "longitude", value = "经度")
    private Double longitude;

}
