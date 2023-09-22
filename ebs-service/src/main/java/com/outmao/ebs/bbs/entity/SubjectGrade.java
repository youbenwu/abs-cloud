package com.outmao.ebs.bbs.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户评分记录
 *
 * */
@Data
@Entity
@Table(name = "bbs_SubjectGrade", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"type", "userId", "subjectId" }) })
public class SubjectGrade implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    /**
     *
     * 根据业务分多个评分项
     *
     * 0--总评分
     * 1--评分项1
     * 2--评分项2
     *
     *
     */
    private int type;

    /**
     * 分数 1～5
     */
    private double grade;

    /**
     * 时间
     */
    private Date createTime;


}
