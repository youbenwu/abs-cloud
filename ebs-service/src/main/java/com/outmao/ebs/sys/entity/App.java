package com.outmao.ebs.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_App")
public class App  implements Serializable
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
     * 唯一编码 迁眼用户端小程序--qy-wechat
     */
    @Column(unique = true)
    private String code;


    /**
     * 0--开发 1--测试 2--提审 3--上线
     */
    private int status;


    /**
     * 应用名称
     */
    private String name;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
