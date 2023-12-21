package com.outmao.ebs.data.dto;

import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.data.common.data.ItemMediaDTO;
import lombok.Data;

@Data
public class PhotoDTO extends ItemMediaDTO {

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     *
     * 自定义类别
     *
     */
    private String category;

    private BindingItem target;


}
