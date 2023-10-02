package com.outmao.ebs.mall.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 商品轮播图片
 *
 */
@ApiModel(value = "ProductImageVO", description = "商品轮播图片")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductImageDTO {

	@ApiModelProperty(name = "id", value = "ID")
	private Long id;

	@ApiModelProperty(name = "mediaId", value = "文件ID")
	private Long mediaId;

	@ApiModelProperty(name = "url", value = "图片地址")
	private String url;

	@ApiModelProperty(name = "type", value = "0图片，1视频")
	private int type;

	private String skuKey;

}
