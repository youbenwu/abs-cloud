package com.outmao.ebs.mall.shop.dto;


import lombok.Data;

@Data
public class ShopDTO {

    /**
     *
     * 店铺ID
     *
     */
    private Long id;

    /**
     *
     * 商家ID
     *
     */
    private Long merchantId;

    /**
     *
     * 是否启用仓库库存
     *
     */
    private boolean useStoreStock;

    /**
     *
     * 店铺标题
     *
     */
    private String title;

    /**
     *
     * 店铺副标题
     *
     */
    private String subtitle;

    /**
     *
     * 店铺简介
     *
     */
    private String intro;

    /**
     *
     * 店铺图标
     *
     */
    private String logo;

    /**
     *
     * 店铺封面
     *
     */
    private String image;


}
