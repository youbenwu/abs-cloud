package com.outmao.ebs.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Embeddable
public class BindingItem implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "绑定对像ID")
	@Column(name="item_id")
	private Long id;

	@ApiModelProperty(name = "type", value = "绑定对像类型 绑定商品传：Product")
	@Column(name="item_type")
	private String type;

	@ApiModelProperty(name = "title", value = "绑定对像标题")
	@Column(name="item_title")
	private String title;


	public BindingItem(Long id,String type){
		this.id=id;
		this.type=type;
	}

}
