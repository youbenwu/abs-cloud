package com.outmao.ebs.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户活跃记录
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "ebs_UserActive")
public class UserActive  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 标识
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 用户活动类型
     *
     */
    private int type;

    /**
     *
     * 用户活动类型
     *
     */
    private String message;

    /**
     *
     * 时间
     *
     */
    private Date createTime;


}
