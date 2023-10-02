package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.vo.IItem;
import com.outmao.ebs.bbs.common.data.SubjectItemVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(value = "HouseVO", description = "房源信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class HouseVO extends SubjectItemVO implements IItem {

    public HouseVO(){

    }


    /**
     *
     * 商品ID
     *
     */
    @ApiModelProperty(name = "id", value = "商品ID")
    private Long id;


    @ApiModelProperty(name = "snapshotId", value = "如果是商品快照，会有快照ID")
    private Long snapshotId;


    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;

    /**
     * 商品店铺
     *
     */
    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;

    /**
     *
     * 商品分类
     *
     */
    @ApiModelProperty(name = "categoryId", value = "商品分类ID")
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
    private HouseAttributesVO attributes;


    /**
     *
     * 商品SKU
     *
     */
    @ApiModelProperty(name = "skus", value = "商品SKU列表")
    private List<HouseSkuVO> skus;


    /**
     *
     * 商品轮播图片列表
     *
     */
    @ApiModelProperty(name = "images", value = "商品轮播图片列表")
    private List<ProductImageVO> images;

    /**
     *
     * 商品图片列表
     *
     */
    @ApiModelProperty(name = "medias", value = "商品图片列表")
    private List<ProductMediaVO> medias;

    /**
     *
     * 产品所在地址
     *
     */
    @ApiModelProperty(name = "address", value = "产品所在地址")
    private ProductAddressVO address;

    /**
     *
     * 产品所在地址
     *
     */
    @ApiModelProperty(name = "salesAddress", value = "销售地址")
    private ProductAddressVO salesAddress;


    @ApiModelProperty(name = "addressId", value = "产品所在地址ID")
    private Long addressId;

    /**
     *
     * 商品类型
     *
     */
    @ApiModelProperty(name = "type", value = "商品类型 0普通商品 11新楼盘 12二手房 13出租房")
    private Integer type;


    /**
     *
     * 二维码地址
     *
     */
    @ApiModelProperty(name = "qrCode", value = "二维码地址")
    private String qrCode;

    /**
     *
     * 商品H5地址
     *
     */
    @ApiModelProperty(name = "url", value = "商品H5地址")
    private String url;

    /**
     *
     * 拼音
     *
     */
    @ApiModelProperty(name = "letter", value = "拼音")
    private String letter;


    /**
     *
     * 商品图片
     *
     */
    @ApiModelProperty(name = "image", value = "商品图片")
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
     * 商品标题
     *
     */
    @ApiModelProperty(name = "title", value = "商品标题")
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


    /**
     *
     * 商品价格
     *
     */
    @ApiModelProperty(name = "price", value = "商品价格")
    private Double price;

    /**
     *
     * 商品价格
     *
     */
    @ApiModelProperty(name = "maxPrice", value = "商品价格")
    private Double maxPrice;


    /**
     *
     * 单价（房屋销售里的每平方米单价）
     *
     */
    @ApiModelProperty(name = "unitPrice", value = "单价（房屋销售里的每平方米单价）")
    private Double unitPrice;

//    /**
//     *
//     * 押金
//     *
//     **/
//    @ApiModelProperty(name = "deposit", value = "押金")
//    private Double deposit;
//
//
//    /**
//     *
//     * 租金付款方式 如：0--月付 1--年付
//     *
//     **/
//    @ApiModelProperty(name = "rentPay", value = "租金付款方式 如：0--月付 1--年付")
//    private Integer rentPay;

    /**
     *
     * 商品库存
     *
     */
    @ApiModelProperty(name = "stock", value = "商品库存")
    private Long stock;


    /**
     *
     * 商品评分
     *
     */
    @ApiModelProperty(name = "grade", value = "商品评分 1～5")
    private Double grade;

    /**
     *
     * 商品状态(0未上架，1已上架)
     *
     */
    @ApiModelProperty(name = "status", value = "商品状态(0未上架，1已上架)")
    private int status;

    /**
     *
     * 审核状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除)
     *
     */
    @ApiModelProperty(name = "auditStatus", value = "审核状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除)")
    private int auditStatus;

    /**
     *
     * 销售状态（0待售/1在售/2售罄）
     *
     */
    @ApiModelProperty(name = "salesStatus", value = "销售状态（0待售/1在售/2售罄）")
    private int salesStatus;

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
