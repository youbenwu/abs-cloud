package com.outmao.ebs.mall.manufacturer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Counselor")
public class Counselor  implements Serializable {
    /**
     *
     *
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
     * 用户
     */
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     * 所属开发商
     */
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    /**
     *
     * 搜索关键字
     *
     */
    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;

    /**
     *
     * 名称
     *
     */
    private String name;

    /**
     *
     * 手机号
     *
     */
    private String phone;


    /**
     * 创建时间
     */
    private Date createTime;



}
