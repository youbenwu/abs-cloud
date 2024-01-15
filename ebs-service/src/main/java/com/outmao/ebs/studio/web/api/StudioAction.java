package com.outmao.ebs.studio.web.api;



import com.outmao.ebs.studio.dto.StudioDTO;
import com.outmao.ebs.studio.service.StudioService;
import com.outmao.ebs.studio.vo.StudioVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Api(value = "studio", tags = "影视模块")
@RestController
@RequestMapping("/api/studio")
public class StudioAction {

	@Autowired
    private StudioService studioService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存影视公司", notes = "保存影视公司")
    @PostMapping("/save")
    public void saveStudio(StudioDTO request){
        studioService.saveStudio(request);
    }


    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取影视公司", notes = "获取影视公司")
    @PostMapping("/get")
    public StudioVO getStudioVOByUserId(Long userId){
        return studioService.getStudioVOByUserId(userId);
    }


}
