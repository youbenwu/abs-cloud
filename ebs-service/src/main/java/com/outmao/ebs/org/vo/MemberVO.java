package com.outmao.ebs.org.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MemberVO implements SimpleUserSetter {

    private Long id;

    private Long orgId;

    private Long userId;

    private SimpleUserVO user;

    private List<MemberRoleVO> roles;

    /**
     *
     * 状态
     *
     */
    private int status;

    /**
     *
     * 状态备注
     *
     */
    private String statusRemark;

    /**
     *
     * 成员类型
     *
     */
    private List<MemberTypeVO> types;

    /**
     *
     * vip等级
     *
     */
    private int vip;

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

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;



}
