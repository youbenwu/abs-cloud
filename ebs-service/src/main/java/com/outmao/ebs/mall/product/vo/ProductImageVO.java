package com.outmao.ebs.mall.product.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 * 商品轮播图片
 *
 */
@ApiModel(value = "ProductImageVO", description = "商品轮播图片")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductImageVO {

	@ApiModelProperty(name = "id", value = "ID")
	private Long id;

	@ApiModelProperty(name = "mediaId", value = "文件ID")
	private Long mediaId;

	@ApiModelProperty(name = "url", value = "图片地址")
	private String url;

	@ApiModelProperty(name = "type", value = "0图片，1视频")
	private int type;

    @JsonIgnore
	private String skuKey;



}
