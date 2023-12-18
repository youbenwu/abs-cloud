package com.outmao.ebs.data.vo;

import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.data.common.data.ItemMediaVO;
import lombok.Data;

@Data
public class PhotoVO extends ItemMediaVO {

    /**
     * 所属用户ID
     */
    private Long userId;

    private BindingItem target;

}
