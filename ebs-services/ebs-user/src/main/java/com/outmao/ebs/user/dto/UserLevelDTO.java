package com.outmao.ebs.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLevelDTO {

    /*
     *
     * ID
     *
     * */
    private Long id;

    /*
     *
     * 等级，从0开始
     *
     * */
    private int level;

    /*
     *
     * 成长值范围
     *
     * */
    private int min;

    /*
     *
     * 成长值范围
     *
     * */
    private int max;

    /*
     *
     * 等级名称
     *
     * */
    private String name;

    /*
     *
     * 等级图标
     *
     * */
    private String icon;

    /*
     *
     * 备注
     *
     * */
    private String remark;


}
