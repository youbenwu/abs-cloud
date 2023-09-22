package com.outmao.ebs.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "HuaUserVO", description = "成都花卉小程序用户信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class HuaUserVO /*implements UserStatsOrderSetter, UserStatsInquirySetter */{

    @ApiModelProperty(name = "id", value = "用户ID")
    private Long   id;

    @ApiModelProperty(name = "sex", value = "姓别0--未知 1--男 2--女")
    private Integer sex;

    @ApiModelProperty(name = "username", value = "帐号")
    private String username;

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;

    @ApiModelProperty(name = "avatar", value = "用户头像")
    private String avatar;

    @ApiModelProperty(name = "registerTime", value = "注册时间")
    private Date registerTime;

//    @ApiModelProperty(name = "statsInquiry", value = "询盘数")
//    private StatsInquiryVO statsInquiry;
//
//    @ApiModelProperty(name = "statsOrder", value = "订单统计")
//    private StatsOrderVO statsOrder;
//
//    @JsonIgnore
//    @Override
//    public Long getUserId() {
//        return id;
//    }


}
