package com.outmao.ebs.mall.manufacturer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 制造商
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Manufacturer")
public class Manufacturer  implements Serializable {
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
     *
     * 搜索关键字
     *
     */
    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;


    /**
     * 开发商名称
     */
    @Column(unique = true)
    private String name;


    /**
     * 订单创建时间
     */
    private Date createTime;


}
