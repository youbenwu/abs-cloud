package com.outmao.ebs.studio.vo;

import com.outmao.ebs.data.common.data.SimpleCategorySetter;
import com.outmao.ebs.data.vo.SimpleCategoryVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 影视对象
 */
@Data
public class MovieVO implements SimpleCategorySetter {


    /**
     * 自动ID
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 所属组织ID
     *
     */
    @ApiModelProperty(name = "orgId", value = "所属组织ID")
    private Long orgId;

    /**
     * 发布用户ID
     */
    @ApiModelProperty(name = "userId", value = "发布用户ID")
    private Long userId;

    /**
     * 主题ID
     */
    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;

    /**
     * 商品ID
     */
    @ApiModelProperty(name = "productId", value = "绑定的商品ID")
    private Long productId;

    /**
     * 类型ID
     */
    @ApiModelProperty(name = "categoryId", value = "类型ID")
    private Long categoryId;
    private SimpleCategoryVO category;

    /**
     * 付费类型
     * 0--免费 1--会员 2--付费
     */
    @ApiModelProperty(name = "feeType", value = "付费类型 0--免费 1--会员 2--付费")
    private int feeType;

    /**
     *
     * 价格
     *
     */
    @ApiModelProperty(name = "price", value = "价格")
    private double price;

    /**
     * 影视名称
     */
    @ApiModelProperty(name = "name", value = "影视名称")
    private String name;

    /**
     * 影视简介
     */
    @ApiModelProperty(name = "intro", value = "影视简介")
    private String intro;

    /**
     * 影视封面
     */
    @ApiModelProperty(name = "cover", value = "影视封面")
    private String cover;

    /**
     * 上映时间
     */
    @ApiModelProperty(name = "releaseTime", value = "上映时间")
    private Date releaseTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


    private List<MovieEpisodeVO> episodes;




}
