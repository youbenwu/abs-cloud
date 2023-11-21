package com.outmao.ebs.mall.order.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.mall.shop.common.data.SimpleShopSetter;
import com.outmao.ebs.mall.shop.vo.SimpleShopVO;
import com.outmao.ebs.user.common.data.ContactUserSetter;
import com.outmao.ebs.user.vo.ContactUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "OrderVO", description = "订单信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class OrderVO implements SimpleShopSetter , ContactUserSetter {


    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;


    @ApiModelProperty(name = "shopId", value = "商家店铺")
    private Long shopId;

    @ApiModelProperty(name = "shop", value = "商家店铺")
    private SimpleShopVO shop;

    @ApiModelProperty(name = "userId", value = "买家用户")
    private Long userId;

    @ApiModelProperty(name = "user", value = "买家用户")
    private ContactUserVO user;

    @ApiModelProperty(name = "merchantId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "storeId", value = "门店ID")
    private Long storeId;

    @ApiModelProperty(name = "sellerId", value = "卖方商家用户ID")
    private Long sellerId;

    @ApiModelProperty(name = "brokerId", value = "经纪人ID 计算佣金用")
    private Long brokerId;

    @ApiModelProperty(name = "partnerId", value = "合伙人ID 计算佣金用")
    private Long partnerId;

    @ApiModelProperty(name = "customerId", value = "客户ID")
    private Long customerId;

    @ApiModelProperty(name = "lookId", value = "关联带看ID")
    private Long lookId;

    @ApiModelProperty(name = "hotelId", value = "酒店ID")
    private Long hotelId;

    @ApiModelProperty(name = "roomNo", value = "房间号")
    private String roomNo;

    @ApiModelProperty(name = "orderNo", value = "订单编号")
    private String orderNo;

    /**
     *
     * 订单状态
     * 00 待付款：用户下单未付款状态
     * 10 待发货：用户付款商家未发货状态
     * 20 待签收：商家发货用户未签收状态
     * 30 已完成：用户签收交易完成状态
     * 40 已关闭：待付款超时、退款完成进入该状态
     *
     */
    @ApiModelProperty(name = "status", value = "订单状态\n" +
            "      00 待付款：用户下单未付款状态\n" +
            "      10 待发货：用户付款商家未发货状态\n" +
            "      20 待签收：商家发货用户未签收状态\n" +
            "      30 已完成：用户签收交易完成状态\n" +
            "      40 已关闭：待付款超时、全额退款完成进入该状态")
    private int status;

    /**
     *
     * 订单状态备注
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "订单状态备注")
    private String statusRemark;


    //各种时间节点

    /**
     * 订单创建时间
     */
    @ApiModelProperty(name = "createTime", value = "订单创建时间")
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

    /**
     * 支付成功时间
     */
    @ApiModelProperty(name = "successTime", value = "支付成功时间")
    private Date successTime;

    /**
     * 订单发货时间
     */
    @ApiModelProperty(name = "deliveryTime", value = "订单发货时间")
    private Date deliveryTime;

    /**
     * 订单完成时间
     */
    @ApiModelProperty(name = "finishTime", value = "订单完成时间")
    private Date finishTime;

    /**
     * 订单关闭时间
     */
    @ApiModelProperty(name = "closeTime", value = "订单关闭时间")
    private Date closeTime;


    /**
     *
     * 预计发货时间
     *
     */
    @ApiModelProperty(name = "expectDeliveryTime", value = "预计发货时间")
    private Date expectDeliveryTime;


    /**
     * 收货地址
     */
    private Long addressId;
    @ApiModelProperty(name = "address", value = "收货地址")
    private OrderAddressVO address;

    /**
     * 物流信息
     */
    private Long logisticsId;
    @ApiModelProperty(name = "logistics", value = "物流信息")
    private OrderLogisticsVO logistics;


    /**
     * 商品类型
     */
    @ApiModelProperty(name = "type", value = "商品类型  " +
            "0--普通商品 " +
            "11--新楼盘 " +
            "12--二手房 " +
            "13--出租房 " +
            "1--虚拟商品 " +
            "20--广告频道 " +
            "30--酒店洗衣服务 " +
            "31--酒店送餐服务 " +
            "100--外部携程旅游商品")
    private Integer type;


    //商品信息

    /**
     * 订单商品信息
     */
    @ApiModelProperty(name = "products", value = "订单商品信息")
    private List<OrderProductVO> products;

    /**
     *
     * 商品总数量
     *
     */
    @ApiModelProperty(name = "quantity", value = "商品总数量")
    private int quantity;

    /**
     * 商品总价
     */
    @ApiModelProperty(name = "amount", value = "商品总价")
    private double amount;

    /**
     * 客户备注
     */
    @ApiModelProperty(name = "remark", value = "客户备注")
    private String remark;

    /**
     *
     * 描述
     *
     */
    private String description;

    /**
     *
     * 订单内容JSON
     *
     */
    private String data;

    //支付信息

    /**
     *
     * 运费 显示给用户看的运费
     *
     */
    @ApiModelProperty(name = "freight", value = "运费 显示给用户看的运费")
    private double freight;

    /**
     * 订单金额,实际支付金额
     */
    @ApiModelProperty(name = "totalAmount", value = "订单金额,实际支付金额")
    private double totalAmount;

    /**
     * 订单支付渠道
     */
    @ApiModelProperty(name = "payChannel", value = "订单支付渠道")
    private String payChannel;

    /**
     * 订单支付单号，第三方支付流水号
     */
    @ApiModelProperty(name = "tradeNo", value = "订单支付单号，第三方支付流水号")
    private String tradeNo;


    @ApiModelProperty(name = "contracts", value = "交易合同")
    private List<OrderContractVO> contracts;



}
