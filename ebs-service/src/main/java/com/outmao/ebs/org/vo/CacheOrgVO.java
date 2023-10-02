package com.outmao.ebs.org.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class CacheOrgVO implements Serializable {

    /**
     * 自动编号
     */
    private Long id;


    /**
     *
     * 上级组织
     *
     */
    private Long parentId;


    /**
     *
     * 上级组织ID
     *
     */
    private CacheOrgVO parent;


    /**
     *
     * 组织名称
     *
     */
    private String name;




}
