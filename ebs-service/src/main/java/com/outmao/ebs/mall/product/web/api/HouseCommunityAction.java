package com.outmao.ebs.mall.product.web.api;



import com.outmao.ebs.mall.product.dto.GetHouseCommunityListDTO;
import com.outmao.ebs.mall.product.dto.HouseCommunityDTO;
import com.outmao.ebs.mall.product.entity.HouseCommunity;
import com.outmao.ebs.mall.product.service.HouseCommunityService;
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




@Api(value = "mall-community", tags = "电商-小区")
@RestController
@RequestMapping("/api/mall/community")
public class HouseCommunityAction {


    @Autowired
    private HouseCommunityService houseCommunityService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取小区信息", notes = "获取小区信息")
    @PostMapping("/get")
    public HouseCommunity getHouseCommunityById(Long id) {
        return houseCommunityService.getHouseCommunityById(id);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取小区信息列表", notes = "获取小区信息列表")
    @PostMapping("/page")
    public Page<HouseCommunity> getHouseCommunityPage(GetHouseCommunityListDTO request, Pageable pageable) {
        return houseCommunityService.getHouseCommunityPage(request,pageable);
    }


}
