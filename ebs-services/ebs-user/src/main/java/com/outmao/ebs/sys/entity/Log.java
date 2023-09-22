package com.outmao.ebs.sys.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "ebs_Log")
public class Log implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     * 用户标识
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 组织ID
     *
     */
    private Long orgId;

    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * IP
     *
     */
    private String ip;

    /**
     *
     * 操作日志标题
     *
     */
    private String title;

    /**
     *
     * 操作日志详情
     *
     */
    @Lob
    private String message;


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;



}
