package com.outmao.ebs.data.entity.enterprise;


import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class EnterpriseTaxInformation {


    /**
     *
     * 纳税人识别号
     * 由税务部门核发的《税务登记证》上的号
     *
     * */
    private String taxpayersRegistrationNumber;



}
