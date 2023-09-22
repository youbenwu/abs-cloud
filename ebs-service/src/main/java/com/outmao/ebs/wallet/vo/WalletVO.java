package com.outmao.ebs.wallet.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WalletVO  {

    /**
     * 钱包ID
     */
    private Long id;

    /**
     * 钱包用户
     */
    private Long userId;

    /**
     *
     * 钱包类型 0--个人钱包 1--商户钱包
     *
     */
    private int type;

    /**
     * 钱包状态0--正常 1--冻结
     */
    private int status;

    /**
     * 钱包状态备注
     */
    private String statusRemark;

    /**
     *
     * 银行帐户ID
     *
     */
    private Long bankAccountId;

    /**
     *
     * 实名
     *
     */
    private String realName;

    /**
     * 钱包帐号
     */
    private String account;


    /**
     * 钱包密码
     */
    private String password;


    /**
     * 钱包资产
     */
    private List<AssetVO> assets;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
