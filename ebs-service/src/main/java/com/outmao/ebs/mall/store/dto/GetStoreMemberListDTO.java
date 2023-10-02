package com.outmao.ebs.mall.store.dto;

import lombok.Data;

@Data
public class GetStoreMemberListDTO {


    /**
     *
     * 门店
     *
     */
    private Long storeId;

    /**
     *
     * 用户
     *
     */
    private Long userId;

    /**
     *
     * 职位
     *
     */
    private String job;

    /**
     *
     * keyword
     *
     */
    private String keyword;


}
