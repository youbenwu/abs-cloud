package com.outmao.ebs.portal.dto;


import lombok.Data;

@Data
public class TopicDTO {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 组织ID
     *
     */
    private Long orgId;

    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 标题
     *
     */
    private String title;

    /**
     *
     * 详情
     *
     */
    private String content;

}
