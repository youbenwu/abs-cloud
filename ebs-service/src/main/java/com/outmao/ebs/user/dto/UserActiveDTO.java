package com.outmao.ebs.user.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * 用户活跃记录
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActiveDTO {


    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 用户活动类型
     *
     */
    @ApiModelProperty(name = "type", value = "//100--迁眼酒店设备每小时调一次\n" +
            "    //101--迁眼酒店设备开机\n" +
            "    //102--迁眼酒店设备关机")
    private int type;

    /**
     *
     * 用户活动类型
     *
     */
    private String message;




}
