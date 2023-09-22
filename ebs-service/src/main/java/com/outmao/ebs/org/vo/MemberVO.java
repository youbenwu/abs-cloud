package com.outmao.ebs.org.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.user.vo.SimpleUserVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MemberVO {

    private Long id;

    private Long orgId;

    private Long userId;

    private SimpleUserVO user;

    private List<MemberRoleVO> roles;

    private int status;

    private String statusRemark;
    /**
     *
     * 名称
     *
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     *
     * 头像
     *
     */
    private String avatar;

    /**
     *
     * 电子邮箱
     *
     */
    private String email;

    private Date createTime;

    private Date updateTime;




}
