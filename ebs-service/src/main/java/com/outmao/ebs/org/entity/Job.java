package com.outmao.ebs.org.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 岗位 和组织关联
 *
 */
@Data
@Entity
@Table(name = "ebs_Job", uniqueConstraints = { @UniqueConstraint(columnNames = { "orgId", "name" }) })
public class Job implements Serializable {

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
     *
     * 组织
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId")
    private Org org;

    /**
     *
     * 岗位名称
     *
     */
    private String name;

    /**
     *
     * 岗位描述
     *
     */
    private String description;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


}
