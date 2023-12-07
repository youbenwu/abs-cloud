package com.outmao.ebs.portal.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.bbs.common.data.SubjectItemVO;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "ArticleVO", description = "文章信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ArticleVO extends SubjectItemVO implements SimpleUserSetter {


    /**
     *
     * ID
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;

    /**
     *
     * 组织ID
     *
     */
    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    /**
     *
     * 用户
     *
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    private SimpleUserVO user;

    /**
     *
     * 分类
     *
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID")
    private Long categoryId;


    @ApiModelProperty(name = "categoryName", value = "分类名称")
    private String categoryTitle;


    /**
     *
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败")
    private Integer status;


    /**
     *
     * 类型 0--普通文章 20--协义
     *
     */
    @ApiModelProperty(name = "type", value = "类型 0--普通文章 10--公告 20--关于")
    private int type;

    @ApiModelProperty(name = "code", value = "类型 about--关于我们 agree--用户协议 privacy--隐私政策")
    private String code;

    /**
     *
     * 文章作者
     *
     */
    @ApiModelProperty(name = "author", value = "文章作者")
    private String author;


    /**
     *
     * 文章相册
     *
     */
    @ApiModelProperty(name = "medias", value = "文章相册")
    private List<ArticleMediaVO> medias;


    /**
     *
     * 文章标题
     *
     */
    @ApiModelProperty(name = "title", value = "文章标题")
    private String title;


    /**
     *
     * 文章内容详情
     *
     */
    @ApiModelProperty(name = "content", value = "文章内容详情")
    private String content;

    /**
     *
     * H5地址
     *
     */
    private String url;

    /**
     *
     * 创建时间
     *
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     *
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;





}
