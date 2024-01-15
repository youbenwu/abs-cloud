package com.outmao.ebs.studio.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.org.dto.GetOrgListDTO;
import com.outmao.ebs.org.dto.OrgDTO;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.studio.dto.GetStudioListDTO;
import com.outmao.ebs.studio.dto.StudioDTO;
import com.outmao.ebs.studio.entity.Studio;
import com.outmao.ebs.studio.service.StudioService;
import com.outmao.ebs.studio.vo.StudioVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AccessPermissionGroup(title="影视模块",url="/studio",name="",children = {

        @AccessPermissionParent(title = "影视公司管理",url = "/studio/studio",name = "",children = {
                @AccessPermission(title = "保存影视公司",url = "/studio/studio",name = "save"),
                @AccessPermission(title = "删除影视公司",url = "/studio/studio",name = "delete"),
                @AccessPermission(title = "读取影视公司",url = "/studio/studio",name = "read"),
        }),

})


@Api(value = "admin-studio", tags = "后台-影视模块")
@RestController
@RequestMapping("/api/admin/studio")
public class StudioAdminAction {

	@Autowired
    private StudioService studioService;


    @PreAuthorize("hasPermission('/studio/studio','save')")
    @ApiOperation(value = "保存影视公司", notes = "保存影视公司")
    @PostMapping("/save")
    public void saveStudio(StudioDTO request){
        studioService.saveStudio(request);
    }

    @PreAuthorize("hasPermission('/studio/studio','delete')")
    @ApiOperation(value = "删除影视公司", notes = "删除影视公司")
    @PostMapping("/delete")
    public void deleteStudioById(Long id){
        studioService.deleteStudioById(id);
    }

    @PreAuthorize("hasPermission('/studio/studio','read')")
    @ApiOperation(value = "获取影视公司", notes = "获取影视公司")
    @PostMapping("/get")
    public StudioVO getStudioVOById(Long id){
        return studioService.getStudioVOById(id);
    }

    @PreAuthorize("hasPermission('/studio/studio','read')")
    @ApiOperation(value = "获取影视公司列表", notes = "获取影视公司列表")
    @PostMapping("/page")
    public Page<StudioVO> getStudioVOPage(GetStudioListDTO request, Pageable pageable){
        return studioService.getStudioVOPage(request,pageable);
    }


}
