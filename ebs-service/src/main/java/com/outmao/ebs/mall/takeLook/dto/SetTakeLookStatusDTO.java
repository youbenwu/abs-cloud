package com.outmao.ebs.mall.takeLook.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SetTakeLookStatusDTO {

    private Long id;

    /**
     *
     * 状态 0--未确认 1--待带看 2--带看中 3--带看完成 4--取消关闭
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--未确认 1--待带看 2--带看中 3--带看完成 4--取消关闭")
    private int status;


}
