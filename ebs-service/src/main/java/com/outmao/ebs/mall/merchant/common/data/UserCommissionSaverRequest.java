package com.outmao.ebs.mall.merchant.common.data;


public interface UserCommissionSaverRequest {

    /**
     * 商家
     */
    public Long getMerchantId();

    /**
     * 用户
     */
    public Long getUserId();

    /**
     *
     * 0--经纪人佣金 1--合伙人佣金
     *
     */
    public int getCommissionType();




}
