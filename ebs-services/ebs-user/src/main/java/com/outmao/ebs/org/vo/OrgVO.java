package com.outmao.ebs.org.vo;

import com.outmao.ebs.org.entity.OrgContact;
import lombok.Data;

import java.util.Date;

@Data
public class OrgVO {

    /**
     * 自动编号
     */
    private Long id;

    /**
     *
     * 组织类型对应ID
     * 租户ID
     * 部门ID
     * 商家ID
     * 门店ID
     */
    private Long targetId;

    /**
     *
     * 用户
     *
     */
    private Long userId;

    /**
     *
     * 上级组织
     *
     */
    private Long parentId;

    /**
     *
     * 多级分类中所处的级别，级别从0开始
     *
     */
    private int level;

    /**
     *
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf=true;


    /**
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除
     *
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
     * 组织类型 0--平台 1--租户
     *
     */
    private int type;

    /**
     *
     * 组织编码
     *
     */
    private String orgNo;

    /**
     *
     * 组织名称
     *
     */
    private String name;

    /**
     *
     * 组织简介
     *
     */
    private String intro;

    /**
     *
     * 地区
     *
     */
    private String area;

    /**
     *
     * 联系信息
     *
     */
    private OrgContact contact;

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
