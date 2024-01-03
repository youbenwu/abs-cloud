package com.outmao.ebs.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户认证信息
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "ebs_UserCertification")
public class UserCertification  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;

    //认证状态
    private int status;

    //状态备注
    private String statusRemark;

    //姓名
    private String name;

    //0--身份证
    private int certificateType;

    //证件号码
    private int certificateNumber;

    //证件正面照
    private String certificateFront;

    //证件反面照
    private String certificateBack;

    //时间
    private Date createTime;
    //时间
    private Date updateTime;

}
