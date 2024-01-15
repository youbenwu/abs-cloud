package com.outmao.ebs.org.dto;



import com.outmao.ebs.common.vo.Contact;
import lombok.Data;


@Data
public class OrgDTO {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 编码
     *
     */
    private String orgNo;

    /**
     *
     * 组织名称
     *
     */
    private String name;

    /**
     *
     * 组织简介
     *
     */
    private String intro;

    /**
     *
     * 联系信息
     *
     */
    private Contact contact;



}
