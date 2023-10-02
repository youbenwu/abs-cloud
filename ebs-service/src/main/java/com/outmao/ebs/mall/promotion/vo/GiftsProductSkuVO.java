package com.outmao.ebs.mall.promotion.vo;

import com.outmao.ebs.mall.product.common.data.MiniProductSkuSetter;
import com.outmao.ebs.mall.product.vo.MiniProductSkuVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GiftsProductSkuVO implements MiniProductSkuSetter {

    /**
     *
     * ID
     *
     */

    private Long id;

    /**
     *
     * 活动ID
     *
     */
    private Long giftsId;

    /**
     *
     * 商品ID
     *
     */
    @ApiModelProperty(name = "productId", value = "商品ID")
    private Long productId;


    /**
     *
     * 商品SKU ID
     *
     */
    private Long skuId;

    private MiniProductSkuVO sku;

    /**
     *
     * 赠品数量
     *
     */
    @ApiModelProperty(name = "quantity", value = "赠品数量")
    private int quantity;


    /**
     * 创建时间
     */
    private Date createTime;


}
