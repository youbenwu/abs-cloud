package com.outmao.ebs.mall.order.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_OrderProduct",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "orderId", "productId", "skuId" }) })
public class OrderProduct implements Serializable {

	/**
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
	 * 订单编号
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	private Order order;

	/**
	 * 商品快照ID
	 */
	private Long snapshotId;


	/**
	 * 商品
	 */
    @Column(nullable = false)
	private Long productId;

	/**
	 *
	 * 商品类型
	 *
	 */
	private Integer productType;

	/**
	 * 商品名称
	 */
	private String productTitle;

	/**
	 * 商品图片
	 */
	private String productImage;

	/**
	 * 商品SKU ID
	 */
	@Column(nullable = false)
	private Long skuId;

	/**
	 * 商品SKU
	 */
	private String skuName;

	/**
	 * 商品单价
	 */
	private double price;

	/**
	 *
	 * 商品数量
	 *
	 */
	private int quantity;

	/**
	 * 金额小计
	 */
	private double amount;

	/**
	 *
	 * 重量小计KG
	 *
	 */
	private Double weight;

	/**
	 *
	 * 体积小计M3
	 *
	 */
	private Double volume;

	/**
	 *
	 * 客户商品备注信息
	 *
	 *
	 */
	private String remark;


	/**
	 *
	 * 租赁信息
	 *
	 *
	 */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="field", column=@Column(name="lease_field")),
			@AttributeOverride(name="value", column=@Column(name="lease_value"))
	})
	private OrderProductLease lease;


	/**
	 *
	 * 订单分销中的佣金
	 *
	 */
	private double commissionAmount;


	/**
	 *
	 * 是否无需发货
	 *
	 */
	private boolean noDelivery;

	/**
	 *
	 * 是否允许商家标记签收
	 *
	 */
	private boolean sellerFinish;


}
