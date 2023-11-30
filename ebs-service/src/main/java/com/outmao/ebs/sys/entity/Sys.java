package com.outmao.ebs.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Sys")
public class Sys implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 系统类型，和Org中type对应
     */
    @Column(unique = true,updatable = false)
    private int type;

    @Column(unique = true,updatable = false)
    private String sysNo;

    /**
     * 系统名称
     */
    private String name;

    /**
     *
     * 系统描述
     *
     */
    private String description;

    /**
     * 首页路径
     */
    private String homePath;

    /**
     * 系统LOGO
     */
    private String logo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
