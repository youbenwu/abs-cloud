package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "HouseDTO", description = "保存房源参数")
@Data
public class HouseDTO {

    /**
     *
     * id
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 商品类型
     *
     */
    @ApiModelProperty(name = "type", value = "商品类型",required = true)
    private Integer type;


    /**
     * 商品店铺ID
     *
     */
    @ApiModelProperty(name = "shopId", value = "商品店铺ID",required = true)
    private Long shopId;

    /**
     * 关联门店ID
     *
     */
    @ApiModelProperty(name = "storeId", value = "关联门店ID")
    private Long storeId;

    /**
     *
     * 商品分类ID
     *
     */
    @ApiModelProperty(name = "categoryId", value = "商品分类ID",required = true)
    private Long categoryId;

    /**
     *
     * 置业顾问ID
     *
     */
    @ApiModelProperty(name = "counselorId", value = "置业顾问ID")
    private Long counselorId;


    /**
     *
     * 商品属性
     *
     */
    @ApiModelProperty(name = "attributes", value = "商品参数")
    private HouseAttributesDTO attributes;

    /**
     *
     * 商品SKUS属性
     *
     */
    @ApiModelProperty(name = "skus", value = "商品SKUS属性 户型就是SKU")
    private List<HouseSkuDTO> skus;


    /**
     *
     * 商品地址
     *
     */
    @ApiModelProperty(name = "address", value = "商品地址")
    private ProductAddressDTO address;

    /**
     *
     * 产品所在地址
     *
     */
    @ApiModelProperty(name = "salesAddress", value = "销售地址")
    private ProductAddressDTO salesAddress;


    /**
     *
     * 商品主图
     *
     */
    @ApiModelProperty(name = "image", value = "商品主图",required = true)
    private String image;

    /**
     *
     * 主图视频
     *
     */
    @ApiModelProperty(name = "movie", value = "主图视频")
    private String video;

    /**
     *
     * 商品轮播图片
     *
     */
    @ApiModelProperty(name = "images", value = "商品轮播图片")
    private List<ProductImageDTO> images;

    /**
     *
     * 商品详情图片
     *
     */
    @ApiModelProperty(name = "medias", value = "商品详情图片")
    private List<ProductMediaDTO> medias;


    /**
     *
     * 商品标题
     *
     */
    @ApiModelProperty(name = "title", value = "商品标题",required = true)
    private String title;

    /**
     *
     * 商品描述
     *
     */
    @ApiModelProperty(name = "description", value = "商品描述")
    private String description;

    /**
     *
     * 商品详情H5
     *
     */
    @ApiModelProperty(name = "details", value = "商品详情H5")
    private String details;

    /**
     *
     * 商品标签，逗号隔开
     *
     */
    @ApiModelProperty(name = "marks", value = "商品标签，逗号隔开")
    private String marks;


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


    /**
     *
     * 商品价格
     *
     */
    @ApiModelProperty(name = "price", value = "参考总价")
    private Double price;


    /**
     *
     * 单价（房屋销售里的每平方米单价）
     *
     */
    @ApiModelProperty(name = "unitPrice", value = "参考单价")
    private Double unitPrice;

    /**
     *
     * 开盘时间
     *
     */
    @ApiModelProperty(name = "marketTime", value = "开盘时间")
    private Date marketTime;

    /**
     *
     * 交付时间
     *
     */
    @ApiModelProperty(name = "deliveryTime", value = "交付时间")
    private Date deliveryTime;


    /**
     *
     * 商品状态(0未上架，1已上架)
     *
     */
    @ApiModelProperty(name = "status", value = "商品状态(0未上架，1已上架)")
    private int status;


    /**
     *
     * 销售状态（0待售/1在售/2售罄）
     *
     */
    @ApiModelProperty(name = "salesStatus", value = "销售状态（0待售/1在售/2售罄）")
    private int salesStatus;


}
