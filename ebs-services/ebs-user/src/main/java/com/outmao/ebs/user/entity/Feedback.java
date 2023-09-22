package com.outmao.ebs.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Feedback")
public class Feedback implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //0--未处理 1--处理中 2--已处理 3--忽略
    private int status;

    private String statusRemark;

    private Long userId;

    private String name;

    private String contact;

    private String content;

    private String images;

    private Date createTime;

    private Date updateTime;



}
