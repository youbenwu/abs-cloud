package com.outmao.ebs.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class SortEntity implements Serializable {

	@ApiModelProperty(name = "id", value = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty(name = "sort", value = "排序")
	private int sort;
	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Date createTime;
	@ApiModelProperty(name = "updateTime", value = "更新时间")
	private Date updateTime;

}
