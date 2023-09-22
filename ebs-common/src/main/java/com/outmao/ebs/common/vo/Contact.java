package com.outmao.ebs.common.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@Data
@Embeddable
@MappedSuperclass
public class Contact {

    @ApiModelProperty(name = "name", value = "联系人姓名")
    private String name;
    @ApiModelProperty(name = "phone", value = "电话号码")
    private String  phone;
    @ApiModelProperty(name = "fax", value = "传真")
    private String  fax;
    @ApiModelProperty(name = "email", value = "邮箱")
    private String  email;
    @ApiModelProperty(name = "address", value = "联系地址")
    @Embedded
    private Address address;

}
