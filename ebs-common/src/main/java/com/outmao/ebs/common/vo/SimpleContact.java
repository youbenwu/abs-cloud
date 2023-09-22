package com.outmao.ebs.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@Data
@Embeddable
@MappedSuperclass
public class SimpleContact {

    @ApiModelProperty(name = "name", value = "联系人姓名")
    private String name;
    @ApiModelProperty(name = "phone", value = "电话号码")
    private String phone;
    @ApiModelProperty(name = "address", value = "联系地址")
    private String address;

}
