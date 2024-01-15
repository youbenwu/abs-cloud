package com.outmao.ebs.studio.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户付费的影视
 */
@Data
@Entity
@Table(name = "ebs_UserMovie",
        uniqueConstraints = {@UniqueConstraint(columnNames = { "userId", "movieId" })})
public class UserMovie implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long orgId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long movieId;

    private Date createTime;

}