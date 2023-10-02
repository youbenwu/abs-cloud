package com.outmao.ebs.mall.store.dto;

import lombok.Data;

@Data
public class StoreMemberDTO {

    public StoreMemberDTO(Long storeId,Long userId){
        this.storeId=storeId;
        this.userId=userId;
    }


    /**
     *
     * ID
     *
     */
    private Long id;

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
     * 名称
     *
     */
    private String name;

    /**
     *
     * 头像
     *
     */
    private String avatar;

    /**
     *
     * 手机号
     *
     */
    private String phone;

    /**
     *
     * 电子邮箱
     *
     */
    private String email;

}
