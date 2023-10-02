package com.outmao.ebs.mall.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 店员
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_StoreMember",uniqueConstraints = {
        @UniqueConstraint(columnNames = { "storeId", "userId" }) })
public class StoreMember  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 门店
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    /**
     *
     * 用户
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 职位
     *
     */
    private String job;

    /**
     *
     * 名称
     *
     */
    private String name;

    /**
     *
     * 头像
     *
     */
    private String avatar;

    /**
     *
     * 手机号
     *
     */
    private String phone;

    /**
     *
     * 电子邮箱
     *
     */
    private String email;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;



}
