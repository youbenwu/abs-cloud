package com.outmao.ebs.org.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 组织机构多个父级
 *
 */
@Data
@Entity
@Table(name = "ebs_OrgParent", uniqueConstraints = { @UniqueConstraint(columnNames = { "orgId", "parentId" }) })
public class OrgParent implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,updatable = false)
    private Long orgId;

    @Column(nullable = false,updatable = false)
    private Long parentId;


}
