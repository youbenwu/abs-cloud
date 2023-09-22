package com.outmao.ebs.bbs.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "SubjectBrowseVO", description = "浏览记录")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SubjectBrowseVO<T> {


    private Long id;

    private Long userId;

    private Long subjectId;

    /**
     *
     */
    private T item;

    /**
     * subject.item.id
     */
    private Long itemId;

    /**
     * subject.item.type
     */
    private String itemType;

    /**
     * 创建时间
     */
    private Date createTime;

}
