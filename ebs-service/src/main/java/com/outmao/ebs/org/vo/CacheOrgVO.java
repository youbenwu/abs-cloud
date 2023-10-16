package com.outmao.ebs.org.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class CacheOrgVO implements Serializable {

    /**
     * 自动编号
     */
    private Long id;

    /**
     * 多个父级组织ID
     */
    private Set<Long> parents;


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
