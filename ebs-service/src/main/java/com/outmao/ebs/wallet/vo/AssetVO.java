package com.outmao.ebs.wallet.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AssetVO {

    /*
     * 自动ID
     */
    private Long id;

    /*
     * 钱包用户
     */
    private Long userId;

    /*
     * 资金钱包
     */
    private Long walletId;

    /*
     * 资产币种
     */
    private String currencyId;

    /*
     * 资产总额
     */
    private long amount;

    /*
     * 资产佘额
     */
    private long balance;

    /*
     * 冻结金额
     */
    private long frozen;

    /*
     * 预付款金额
     */
    private long advance;

    /*
     * 创建时间
     */
    private Date createTime;

    /*
     * 更新时间
     */
    private Date updateTime;


}
