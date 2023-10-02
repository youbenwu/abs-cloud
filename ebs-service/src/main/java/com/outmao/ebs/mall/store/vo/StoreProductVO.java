package com.outmao.ebs.mall.store.vo;

import com.outmao.ebs.mall.product.common.data.ProductSetter;
import com.outmao.ebs.mall.product.vo.ProductVO;
import lombok.Data;

import java.util.Date;

@Data
public class StoreProductVO implements ProductSetter {

    private Long id;

    private Long storeId;

    private Long productId;

    private ProductVO product;

    private Long categoryId;

    /**
     * 创建时间
     */
    private Date createTime;

}
