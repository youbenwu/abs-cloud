package com.outmao.ebs.wallet.dto;


import com.outmao.ebs.wallet.entity.Fee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyDTO {

    /*
     * 币种ID
     */
    private String id;

    /*
     * 币种名称，如‘人民币’
     */
    private String name;

    /*
     * 币种单位，如‘元’
     */
    private String unit;

    /*
     * 单位数量，货币值/oneUnit=单位值
     */
    private long oneUnit;

    /*
     *
     * 转换成基准货币的单位价值
     *
     */
    private long par;

    /*
     *
     * 是否可提现
     *
     */
    private boolean cash;

    /*
     *
     * 提现费率
     *
     */
    private Fee cashFee;

}
