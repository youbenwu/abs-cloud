package com.outmao.ebs.thirdpartys.rongcloud.dto;

import io.rong.models.user.UserModel;
import lombok.Data;

@Data
public class RcRegisterUserDTO extends UserModel {

    private String groupId;

    private String groupName;

}
