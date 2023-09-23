package com.outmao.ebs.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;



@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "ebs_Online")
public class Online implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     * 标识
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 用户ID
     *
     */
    private Long userId;


    /**
     *
     * 更新时间
     *
     */
    private long time;


    /**
     *
     * 有新消息
     *
     */
    private boolean message;



}
