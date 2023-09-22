package com.outmao.ebs.portal.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
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


@AccessPermissionGroup(title="门户",url="/portal",name="",children = {
        @AccessPermissionParent(title = "专题管理",url = "/portal/topic",name = "",children = {
                @AccessPermission(title = "保存专题",url = "/portal/topic",name = "save"),
                @AccessPermission(title = "删除专题",url = "/portal/topic",name = "delete"),
                @AccessPermission(title = "读取专题",url = "/portal/topic",name = "read"),
        }),
})


@Api(value = "admin-portal-topic", tags = "后台-门户-专题")
@RestController
@RequestMapping("/api/admin/portal/topic")
public class TopicAdminAction {

	@Autowired
    private TopicService topicService;


    @PreAuthorize("hasPermission('/portal/topic','save')")
    @ApiOperation(value = "保存专题", notes = "保存专题")
    @PostMapping("/save")
    public void saveTopic(TopicDTO request){
        topicService.saveTopic(request);
    }

    @PreAuthorize("hasPermission('/portal/topic','delete')")
    @ApiOperation(value = "删除专题", notes = "删除专题")
    @PostMapping("/delete")
    public void deleteTopicById(Long id){
        topicService.deleteTopicById(id);
    }

    @PreAuthorize("hasPermission('/portal/topic','read')")
    @ApiOperation(value = "获取专题列表", notes = "获取专题列表")
    @PostMapping("/page")
    public Page<TopicVO> getTopicVOPage(GetTopicListDTO request, Pageable pageable){
        return topicService.getTopicVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission('/portal/topic','save')")
    @ApiOperation(value = "保存专题文章列表", notes = "保存专题文章列表")
    @PostMapping("/article/saveList")
    public void saveTopicArticleList(TopicArticleListDTO request){
        topicService.saveTopicArticleList(request);
    }

    @PreAuthorize("hasPermission('/portal/topic','save')")
    @ApiOperation(value = "删除专题文章列表", notes = "删除专题文章列表")
    @PostMapping("/article/deleteList")
    public void deleteTopicArticleList(DeleteTopicArticleListDTO request){
        topicService.deleteTopicArticleList(request);
    }

    @PreAuthorize("hasPermission('/portal/topic','read')")
    @ApiOperation(value = "获取专题文章列表", notes = "获取专题文章列表")
    @PostMapping("/article/list")
    public List<TopicArticleVO>  getTopicArticleVOList(GetTopicArticleListDTO request){
        return  topicService.getTopicArticleVOList(request);
    }


}
