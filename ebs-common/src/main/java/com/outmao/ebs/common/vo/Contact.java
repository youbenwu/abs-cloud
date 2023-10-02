package com.outmao.ebs.common.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

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


    @Override
    public String toString(){
        StringBuffer s=new StringBuffer();
        if(!StringUtils.isEmpty(name)){
            s.append(" "+name);
        }
        if(!StringUtils.isEmpty(phone)){
            s.append(" "+phone);
        }
        if(!StringUtils.isEmpty(fax)){
            s.append(" "+fax);
        }
        if(!StringUtils.isEmpty(email)){
            s.append(" "+email);
        }
        s.append(" "+address.toString());
        return s.toString().trim();
    }


}
