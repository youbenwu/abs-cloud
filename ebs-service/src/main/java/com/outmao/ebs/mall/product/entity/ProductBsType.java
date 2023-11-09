package com.outmao.ebs.mall.product.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


/**
 * 
 * 商品业务类型
 * 只能是 enum ProductType 中的值
 * 
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_ProductBsType")
public class ProductBsType  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 *
	 * 唯一不变标识
	 *
	 */
	@Id
	@Column(name = "_type")
	private Integer type;

	/**
	 *
	 * 类型描述
	 *
	 */
	@Column(name = "_describe")
	private String describe;



}
