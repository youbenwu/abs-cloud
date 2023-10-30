package com.outmao.ebs.mall.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 订单
 * 
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Order")
public class Order  implements Serializable{
	/**
	 *
	 *
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 自动编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 组织ID
	 */
	@Column(nullable = false,updatable = false)
	private Long orgId;

	/**
	 * 商家ID
	 */
	@Column(nullable = false,updatable = false)
	private Long merchantId;

	/**
	 * 商家店铺
	 */
//	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
//	@JoinColumn(name = "shopId")
//	private Shop shop;

	@Column(nullable = false,updatable = false)
	private Long shopId;

	/**
	 * 买家用户
	 */
//	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
//	@JoinColumn(name = "userId")
//	private User user;

	@Column(nullable = false,updatable = false)
	private Long userId;

	/**
	 * 卖家用户ID
	 */
	@Column(nullable = false,updatable = false)
	private Long sellerId;

	/**
	 *
	 * 门店ID
	 * 在门店下单
	 *
	 */
	@Column(updatable = false)
	private Long storeId;


	private Long hotelId;

	private String roomNo;


	/**
	 * 出货仓库ID
	 */
	private Long fromStoreId;


	//分销相关信息

	/**
	 * 经纪人ID 计算佣金用
	 */
	private Long brokerId;

	/**
	 * 合伙人ID 计算佣金用
	 */
	private Long partnerId;

	/**
	 * 合伙上级ID 计算佣金用
	 */
	private Long partnerParentId;

	/**
	 * 客户ID
	 */
	private Long customerId;

	/**
	 * 关联带看ID
	 */
	private Long lookId;


	/**
	 * 订单编号
	 */
	@Column(unique = true)
	private String orderNo;

	/**
	 *
	 * 订单状态
	 * 00 待付款：用户下单未付款状态
	 * 10 待发货：用户付款商家未发货状态
	 * 20 待签收：商家发货用户未签收状态
	 * 30 已完成：用户签收交易完成状态
	 * 40 已关闭：待付款超时、全款退款完成进入该状态
	 *
	 */
	private int status;

	/**
	 *
	 * 订单状态备注
	 *
	 */
	private String statusRemark;

	/**
	 *
	 * 订单退款状态
	 * 0 没退款
	 * 1 退款中：用户发起退款申请进入该状态
	 * 2 退款成功
	 * 3 退款失败
	 *
	 */
	private int refundStatus;

	/**
	 *
	 * 搜索关键字
	 *
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String keyword;


//	/**
//	 * 收货地址
//	 */
//	@OneToOne(cascade = CascadeType.PERSIST, optional = true, fetch = FetchType.LAZY)
//	@JoinColumn(name = "addressId")
//	private OrderAddress address;

	private Long addressId;

//	/**
//	 * 物流信息
//	 */
//	@OneToOne(cascade = CascadeType.PERSIST, optional = true, fetch = FetchType.LAZY)
//	@JoinColumn(name = "logistics")
//	private OrderLogistics logistics;


	private Long logisticsId;


	/**
	 * 商品类型
	 */
	private Integer type;

	/**
	 *
	 * 自定义业务参数
	 *
	 */
	private String business;


	//商品信息

	/**
	 * 订单商品信息
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@OrderBy(value = "id ASC")
	private List<OrderProduct> products;

	/**
	 *
	 * 商品数量
	 *
	 */
	private int quantity;

	/**
	 * 商品总价
	 */
	private double amount;

	/**
	 * 客户备注
	 */
	private String remark;

	//支付信息
	/**
	 *
	 * 运费 显示给用户看的运费
	 *
	 */
	private double freight;

	/**
	 * 订单金额,实际支付金额
	 */
	private double totalAmount;

	/**
	 * 订单支付渠道
	 */
	private String payChannel;

	/**
	 * 订单支付单号，第三方支付流水号
	 */
	private String tradeNo;


	//各种时间节点

	/**
	 * 订单创建时间
	 */
	private Date createTime;

	/**
	 *
	 * 更新时间
	 *
	 */
	private Date updateTime;

	/**
	 * 支付成功时间
	 */
	private Date successTime;

	/**
	 * 订单发货时间
	 */
	private Date deliveryTime;

	/**
	 * 订单完成时间
	 */
	private Date finishTime;

	/**
	 * 订单关闭时间
	 */
	private Date closeTime;


	/**
	 *
	 * 是否启用仓库库存
	 *
	 */
	private boolean useStoreStock;


	//是否外部商品
	public boolean isOut(){
		if(type!=null){
			if(type== ProductType.OUT_CTRIP.getType()){
				return true;
			}
		}
		return false;
	}

	
}
