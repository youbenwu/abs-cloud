package com.outmao.ebs.data.entity.enterprise;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class EnterpriseContactInformation {

    /**
     *
     * 姓名
     * 企业的业务联系人姓名
     *
     * */
    private String name;

    /**
     *
     * 电话号码
     * 企业的业务联系人的电话号码
     *
     * */
    private String telephoneNumber;

    /**
     *
     * 传真号码
     * 企业的业务联系人的传真号码
     *
     * */
    private String faxNumber;


    /**
     *
     * 电子邮箱
     * 企业的业务联系人的电子邮箱
     *
     * */
    private String email;


    /**
     *
     * 地址
     * 企业的业务联系人的邮政地址
     *
     * */
    private String address;


    /**
     *
     * 邮政编码
     * 企业的业务联系人的邮政地址
     *
     * */
    private String postalCode;



}
