package com.outmao.ebs.bbs.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 浏览记录
 */
@Data
@Entity
@Table(name = "bbs_SubjectBrowse", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "userId", "subjectId" }) })
public class SubjectBrowse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long subjectId;


    /**
     * subject.item.id
     */
    private Long itemId;

    /**
     * subject.item.type
     */
    private String itemType;

    /**
     * 创建时间
     */
    private Date createTime;


}
