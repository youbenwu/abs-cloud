package com.outmao.ebs.sys.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ebs_SysMenu", uniqueConstraints = {@UniqueConstraint(columnNames = { "sysId", "menuId" }) })
public class SysMenu  implements Serializable {

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

    @Column(updatable = false,nullable = false)
    private Long sysId;

    @Column(updatable = false,nullable = false)
    private Long menuId;

    /**
     * 创建时间
     */
    private Date createTime;


}
