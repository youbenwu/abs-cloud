package com.outmao.ebs.portal.web.api;



import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.entity.Recommend;
import com.outmao.ebs.portal.service.RecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@Api(value = "portal-recommend", tags = "门户-推荐")
@RestController
@RequestMapping("/api/portal/recommend")
public class RecommendAction {

	@Autowired
    private RecommendService recommendService;

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取推荐列表", notes = "获取推荐列表")
    @PostMapping("/page")
    public Page<Recommend> getRecommendPage(GetRecommendListDTO request, Pageable pageable) {
        return recommendService.getRecommendPage(request,pageable);
    }


}
