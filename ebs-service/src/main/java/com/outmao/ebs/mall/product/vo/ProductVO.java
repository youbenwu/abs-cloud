package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.bbs.common.data.SubjectItemVO;
import com.outmao.ebs.common.vo.IItem;
import com.outmao.ebs.common.vo.Location;
import com.outmao.ebs.common.vo.TimeSpan;
import com.outmao.ebs.mall.product.entity.ProductLease;
import com.outmao.ebs.mall.promotion.vo.ProductPromotionVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "ProductVO", description = "商品信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductVO extends SubjectItemVO implements IItem {


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
     * 酒店ID，酒店中商品
     *
     */
    @ApiModelProperty(name = "hotelId", value = "酒店ID，酒店中商品")
    private Long hotelId;

    /**
     *
     * 商品分类
     *
     */
    @ApiModelProperty(name = "categoryId", value = "商品分类ID")
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
    @ApiModelProperty(name = "attributes", value = "商品参数列表")
    private List<ProductAttributeGroupVO> attributes;

    /**
     *
     * 商品规格属性
     *
     */
    @ApiModelProperty(name = "properties", value = "商品规格属性列表")
    private List<ProductPropertyVO> properties;

    /**
     *
     * 商品SKU
     *
     */
    @ApiModelProperty(name = "skus", value = "商品SKU列表")
    private List<ProductSkuVO> skus;


    /**
     *
     * 商品轮播图片列表
     *
     */
    @ApiModelProperty(name = "images", value = "商品轮播图片列表")
    private List<ProductImageVO> images;

    /**
     *
     * 商品详情图片列表
     *
     */
    @ApiModelProperty(name = "medias", value = "商品详情图片列表")
    private List<ProductMediaVO> medias;

    /**
     *
     * 产品所在地址
     *
     */
    @ApiModelProperty(name = "address", value = "产品所在地址")
    private ProductAddressVO address;

    @ApiModelProperty(name = "addressId", value = "产品所在地址ID")
    private Long addressId;

    /**
     *
     * 产品所在地址
     *
     */
    @ApiModelProperty(name = "salesAddress", value = "销售地址")
    private ProductAddressVO salesAddress;

    @ApiModelProperty(name = "salesAddressId", value = "销售地址ID")
    private Long salesAddressId;

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
    @ApiModelProperty(name = "type", value = "商品类型")
    private Integer type;

    /**
     *
     * 租赁信息
     *
     */
    @ApiModelProperty(name = "lease", value = "租赁信息")
    private ProductLease lease;


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
     * 商品条码
     *
     */
    @ApiModelProperty(name = "barcode", value = "商品条码")
    private String barcode;


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
     * 商品评分
     *
     */
    @ApiModelProperty(name = "grade", value = "商品评分 1～5")
    private Double grade;


    /**
     *
     * 支持商品订制
     *
     */
    @ApiModelProperty(name = "custom", value = "支持商品订制")
    private Boolean custom;


    /**
     *
     * 商品是否上架
     *
     */
    @ApiModelProperty(name = "onSell", value = "商品是否上架")
    private boolean onSell;

    /**
     *
     * 审核状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核失败)
     *
     */
    @ApiModelProperty(name = "status", value = "审核状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核失败)")
    private int status;

    /**
     *
     * 状态备注
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "状态备注")
    private String statusRemark;

    /**
     *
     * 销售状态（0待售/1在售/2售罄）
     *
     */
    @ApiModelProperty(name = "salesStatus", value = "销售状态（0待售/1在售/2售罄）")
    private int salesStatus;

    /**
     *
     * 销售量
     *
     */
    @ApiModelProperty(name = "sales", value = "销售量")
    private int sales;

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


    @ApiModelProperty(name = "promotion", value = "商品促销信息")
    private ProductPromotionVO promotion;



}
