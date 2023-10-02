package com.outmao.ebs.mall.promotion.vo;

import com.outmao.ebs.mall.product.common.data.MiniProductSetter;
import com.outmao.ebs.mall.product.vo.MiniProductVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class GiftsVO implements MiniProductSetter {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 所属店铺ID
     *
     */
    private Long shopId;


    /**
     *
     * 状态 0--正常 1--已下架
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--正常 1--已下架")
    private int status;


    /**
     *
     * 主商品ID
     *
     */
    private Long productId;

    private MiniProductVO product;

    private List<GiftsProductSkuVO> skus;

    /**
     *
     * 标题
     *
     */
    private String title;

    /**
     *
     * 描述
     *
     */
    private String description;

    /**
     *
     * 是否永远有效
     *
     */
    private boolean forEver;


    /**
     *
     * 活动开始时间
     *
     */
    private Date startTime;


    /**
     *
     * 活动开始时间
     *
     */
    private Date endTime;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
