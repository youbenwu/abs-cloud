package com.outmao.ebs.mall.product.dto;

import com.outmao.ebs.common.vo.Location;
import com.outmao.ebs.common.vo.TimeSpan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ProductDTO {

    /**
     *
     * id
     *
     */
    @ApiModelProperty(name = "id", value = "id")
    private Long id;

    /**
     *
     * 商品店铺ID
     *
     */
    @ApiModelProperty(name = "shopId", value = "商品店铺ID",required = true)
    private Long shopId;

    /**
     *
     * 关联门店ID
     *
     */
    @ApiModelProperty(name = "storeId", value = "关联门店ID")
    private Long storeId;

    @ApiModelProperty(name = "hotelId", value = "酒店ID，酒店中商品")
    private Long hotelId;

    /**
     *
     * 商品分类ID
     *
     */
    @ApiModelProperty(name = "categoryId", value = "商品分类ID",required = true)
    private Long categoryId;

    /**
     *
     * 店铺中的商品分类ID
     *
     */
    @ApiModelProperty(name = "spcId", value = "店铺中的商品分类ID")
    private Long spcId;

    /**
     *
     * 商品品牌
     *
     */
    @ApiModelProperty(name = "brandId", value = "商品品牌ID")
    private Long brandId;

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
    @ApiModelProperty(name = "attributes", value = "商品属性")
    private List<ProductAttributeGroupDTO> attributes;

    /**
     *
     * 商品规格属性
     *
     */
    @ApiModelProperty(name = "properties", value = "商品规格属性")
    private List<ProductPropertyDTO> properties;

    /**
     *
     * 商品SKUS属性
     *
     */
    @ApiModelProperty(name = "skus", value = "商品SKUS属性")
    private List<ProductSkuDTO> skus;


    /**
     *
     * 产品所在地址
     *
     */
    @ApiModelProperty(name = "address", value = "产品所在地址")
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
     * 位置经纬度
     *
     */
    @ApiModelProperty(name = "location", value = "位置经纬度")
    private Location location;


    /**
     *
     * 商品类型
     *
     */
    @ApiModelProperty(name = "type", value = "商品类型 0--普通商品 10--广告位")
    private int type;


    /**
     *
     * 商品条码
     *
     */
    @ApiModelProperty(name = "barcode", value = "商品条码")
    private String barcode;

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
    @ApiModelProperty(name = "video", value = "主图视频")
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
     * 商品卖点-副标题
     *
     */
    @ApiModelProperty(name = "subtitle", value = "商品卖点-副标题")
    private String subtitle;

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


//    /**
//     *
//     * 商品价格
//     *
//     */
    //@ApiModelProperty(name = "price", value = "参考总价")
    //private Double price;


//    /**
//     *
//     * 单价（房屋销售里的每平方米单价）
//     *
//     */
    //@ApiModelProperty(name = "unitPrice", value = "参考单价")
    //private Double unitPrice;


    /**
     *
     * 市场价
     *
     */
    @ApiModelProperty(name = "marketPrice", value = "市场价")
    private Double marketPrice;

    /**
     *
     * 成本价
     *
     */
    @ApiModelProperty(name = "costPrice", value = "成本价")
    private Double costPrice;

    /**
     *
     * 告警库存
     *
     */
    @ApiModelProperty(name = "alarmStock", value = "告警库存")
    private Long alarmStock;

    /**
     *
     * 净重 KG
     *
     */
    @ApiModelProperty(name = "weight", value = "净重 KG")
    private Double weight;

    /**
     *
     * 毛重 KG
     *
     */
    @ApiModelProperty(name = "roughWeight", value = "毛重 KG")
    private Double roughWeight;

    /**
     *
     * 体积
     *
     */
    @ApiModelProperty(name = "volume", value = "体积")
    private Double volume;


    /**
     *
     * 支持商品订制
     *
     */
    @ApiModelProperty(name = "custom", value = "支持商品订制")
    private Boolean custom;


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
    @ApiModelProperty(name = "onSell", value = "商品是否上架")
    private boolean onSell;

    /**
     *
     * 销售状态（0待售/1在售/2售罄）
     *
     */
    @ApiModelProperty(name = "salesStatus", value = "销售状态（0待售/1在售/2售罄）")
    private int salesStatus;

    /**
     *
     * 是否启用仓库库存
     *
     */
    @ApiModelProperty(name = "useStoreStock", value = "是否启用仓库库存")
    private boolean useStoreStock;

    /**
     *
     * 预计发货时长
     * 支付成功后的预计发货时长
     */
    @ApiModelProperty(name = "expectDeliveryTimeSpan", value = "支付成功后的预计发货时长")
    private TimeSpan expectDeliveryTimeSpan;



}
