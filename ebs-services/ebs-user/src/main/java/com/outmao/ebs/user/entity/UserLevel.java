package com.outmao.ebs.user.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
*
* 用户等级划分
*
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ebs_UserLevel")
public class UserLevel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
    *
    * ID
    *
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 等级，从0开始
     *
     * */
    @Column(unique = true)
    private int level;

    /**
     *
     * 成长值范围
     *
     * */
    private int min;

    /**
     *
     * 成长值范围
     *
     * */
    private int max;

    /**
     *
     * 等级名称
     *
     * */
    private String name;

    /**
     *
     * 等级图标
     *
     * */
    private String icon;

    /**
     *
     * 备注
     *
     * */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;



}
