package com.outmao.ebs.portal.dto;

import lombok.Data;

import java.util.List;

@Data
public class TopicArticleListDTO {

    private Long topicId;

    private List<Long> articles;


}
