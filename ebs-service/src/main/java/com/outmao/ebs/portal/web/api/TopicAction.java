package com.outmao.ebs.portal.web.api;


import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.service.TopicService;
import com.outmao.ebs.portal.vo.TopicArticleVO;
import com.outmao.ebs.portal.vo.TopicVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;





@Api(value = "portal-topic", tags = "门户-专题")
@RestController
@RequestMapping("/api/portal/topic")
public class TopicAction {

	@Autowired
    private TopicService topicService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取专题列表", notes = "获取专题列表")
    @PostMapping("/page")
    public Page<TopicVO> getTopicVOPage(GetTopicListDTO request, Pageable pageable){
        return topicService.getTopicVOPage(request,pageable);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取专题文章列表", notes = "获取专题文章列表")
    @PostMapping("/article/list")
    public List<TopicArticleVO>  getTopicArticleVOList(GetTopicArticleListDTO request){
        return  topicService.getTopicArticleVOList(request);
    }


}
