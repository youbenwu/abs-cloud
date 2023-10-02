package com.outmao.ebs.org.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
*
 *
 * 首页 /index
 *
 * 商城管理
 *   商家管理
 * 门户
 *
 * 消息管理
 *   消息类型
 *   消息模板
 *   消息列表
 *   用户消息
 * 论坛管理
 *   分类管理
 *   板块管理
 *   主题管理
 *   帖子管理
 *   评论管理
 *   投诉管理
 *
 * 书城管理
 *   作者管理
 *   书籍管理
 *   目录管理
 *
 * 数据管理
 *   地区数据
 *   地铁数据
 *   资源数据
 *   二维码
 *   企业数据
 *   品牌数据
 *
 * 执法管理 /lawe
 *   案情管理 /lawe/case_list
 *   立案审核 /lawe/caseReview_list
 *   结案审核 /lawe/caseFinishReview_list
 *
 * 法律法规库存 /law
 *   法律法规 /law/law_list
 *   法律条款 /law/lawPro_list
 *
 *
 * 用户管理 /user
 *   用户列表 /user/user_list
 *   实名认证 /user/idAuth_list
 *   用户反馈 /user/feedback_list
 *   等级配置 /user/level_list
 *
 * 组织机构 /org
 *   组织信息 /org/org
 *   部门管理 /org/dep_list
 *   职位管理 /org/job_list
 *   成员管理 /org/member_list
 *   管理员管理 /org/admin_list
 *   角色管理 /org/role_list
 *
 * 系统管理 /sys
 *   系统信息 /sys/sys
 *   操作日志 /sys/log_list
 *
 * 开发管理 /dev
 *   组织列表 /dev/org_list
 *   系统列表 /dev/sys_list
 *   菜单列表 /dev/menu_list
 *   权限列表 /dev/per_list
 *
 *
* */

@Data
@Entity
@Table(name = "ebs_Menu")
public class Menu implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 上级
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId",updatable = false)
    private Menu parent;

    /**
     *
     * 多级分类中所处的级别，级别从0开始
     *
     */
    private int level=0;

    /**
     *
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf=true;

    /**
     *
     * 子权限
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Menu> children;

    /**
     * 排序 从0开始
     */
    private int sort;


    /**
     * 0--显示 1--隐藏
     */
    private int status;

    /**
     *
     * 菜单URL
     *
     */
    private String url;

    /**
     *
     * 菜单路径
     *
     */
    @Column(unique = true)
    private String path;

    /**
     * 显示图标
     */
    private String icon;

    /**
     * 显示名称
     */
    private String name;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
