package com.outmao.ebs.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO {

    /**
     *
     * 自动ID
     *
     */
    private Long id;

    /**
     *
     * 上级ID
     *
     */
    private Long parentId;

    /**
     *
     * 0--国家 1--省级 2--市级 3--区级 4--街道
     *
     */
    private int type;

    /**
     *
     * 是否国外
     *
     */
    private boolean foreign;

    /**
     *
     * 地区名称
     *
     */
    private String name;

    /**
     *
     * 地区编号
     *
     */
    private String areaCode;

    /**
     *
     * 邮编
     *
     */
    private String zipCode;

    /**
     *
     * 国家统计局里的编号
     *
     */
    private String code;

}
