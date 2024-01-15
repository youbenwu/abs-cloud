package com.outmao.ebs.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "ebs_Department", uniqueConstraints = { @UniqueConstraint(columnNames = { "orgId", "name" }) })
public class Department implements Serializable {

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
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId")
    private Org org;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Department parent;
    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Department> children;
    private int level;
    private boolean leaf=true;
    private int sort;

    private String name; //部门名称
    private String intro;//部门简介

    private Date createTime;
    private Date updateTime;

}
