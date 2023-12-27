package com.outmao.ebs.message.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 外部消息模板
 *
 *
 */
@Data
@Entity
@Table(name = "m_OutTemplate")
public class OutTemplate implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 环境
     *
     */
    private String context;

    /**
     *
     * 类型
     * sms_ali
     * sms_juhe
     *
     *
     */
    private String type;

    /**
     *
     * 编码
     *
     */
    private String code;

    /**
     *
     * 名称
     *
     */
    private String name;

    /**
     *
     * 签名
     *
     */
    private String signName;

    /**
     *
     * 外部templateId
     *
     */
    private String templateId;

    /**
     *
     * 外部template
     *
     */
    private String template;


    private Date createTime;

    private Date updateTime;



}
