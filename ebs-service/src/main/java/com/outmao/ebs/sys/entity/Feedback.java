package com.outmao.ebs.sys.entity;

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

    //0--用户反馈 1--用户投诉
    private int type;

    //投诉项 逗号隔开
    private String items;

    private String content;

    //图片 逗号隔开
    private String images;

    private Date createTime;

    private Date updateTime;



}
