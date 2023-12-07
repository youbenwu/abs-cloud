package com.outmao.ebs.mall.product.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * 产品租赁信息
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductLease implements Serializable {



	@ApiModelProperty(name = "lease", value = "是否租赁")
	private boolean lease;

	@ApiModelProperty(name = "min", value = "起租租期")
	private Integer min;

	@ApiModelProperty(name = "field", value = "1--分钟 2--小时 3--天 4--月 5--年")
	private Integer field;

}
