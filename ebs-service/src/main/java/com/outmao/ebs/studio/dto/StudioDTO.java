package com.outmao.ebs.studio.dto;


import lombok.Data;



/**
 * 影视公司
 */
@Data
public class StudioDTO {


    private Long id;


    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 简介
     */
    private String intro;



}
