package com.outmao.ebs.org.entity.enterprise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 *  企业
 *  Enterprise
 *  定义:企业核心元数据 参见GB/T 24663-2009
 *  类型:复合型
 *
 */
@Data
@Entity
@Table(name = "ebs_Enterprise")
public class Enterprise extends EnterpriseBasicInformation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 用户
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 状态
     *
     */
    private int status;

    /**
     *
     * 状态
     *
     */
    private String statusRemark;


    /**
     *
     * 更新时间
     *
     */
    private Date createTime;

    /**
     *
     * 创建时间
     *
     */
    private Date updateTime;


}
