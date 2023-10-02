package com.outmao.ebs.mall.store.vo;

import lombok.Data;
import java.util.Date;

@Data
public class StoreMemberVO {

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
