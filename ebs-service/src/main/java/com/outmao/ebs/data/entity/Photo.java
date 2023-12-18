package com.outmao.ebs.data.entity;


import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.data.common.data.ItemMedia;
import lombok.Data;

import javax.persistence.*;

/**
 *
 * 相册
 *
 */
@Data
@Entity
@Table(name = "ebs_Photo")
public class Photo extends ItemMedia  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 所属用户ID
     */
    private Long userId;


    /**
     * 绑定目标
     */
    @Embedded
    private BindingItem target;




}
