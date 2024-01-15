package com.outmao.ebs.org.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 组织机构类型
 *
 */
@Data
@Entity
@Table(name = "ebs_OrgType", uniqueConstraints = { @UniqueConstraint(columnNames = { "orgId", "type" }) })
public class OrgType implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 组织类型
     * 0--平台
     * 1--租户
     * 2--部门
     * 3--商家
     * 4--门店
     * 5--店铺
     * 6--酒店
     * 7--影视公司
     *
     */
    public static final int TYPE_SYSTEM=0;
    public static final int TYPE_TENANT=1;
    public static final int TYPE_DEPART=2;
    public static final int TYPE_MERCHANT=3;
    public static final int TYPE_STORE=4;
    public static final int TYPE_SHOP=5;
    public static final int TYPE_HOTEL=6;
    public static final int TYPE_STUDIO=7;

    public static int getType(String type){
        if(type.equals("Merchant")){
            return OrgType.TYPE_MERCHANT;
        }else if(type.equals("Store")){
            return OrgType.TYPE_STORE;
        }else if(type.equals("Shop")){
            return OrgType.TYPE_SHOP;
        }else if(type.equals("Hotel")){
            return OrgType.TYPE_HOTEL;
        }else if(type.equals("Studio")){
            return OrgType.TYPE_STUDIO;
        }
        return TYPE_TENANT;
    }

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 组织ID
     *
     */
    @Column(nullable = false,updatable = false)
    private Long orgId;

    /**
     *
     * 组织类型对应ID
     *
     */
    @Column(unique = true,updatable = false)
    private Long targetId;

    /**
     *
     * 组织类型
     *
     */
    @Column(updatable = false)
    private int type;

    /**
     *
     * 类型名称
     *
     */
    @Column(nullable = false,updatable = false)
    private String name;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;


}
