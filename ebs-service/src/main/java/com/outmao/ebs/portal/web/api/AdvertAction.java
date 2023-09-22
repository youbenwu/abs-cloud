package com.outmao.ebs.portal.web.api;


import com.outmao.ebs.portal.dto.GetAdvertListDTO;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.service.AdvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "portal-advert", tags = "门户-广告")
@RestController
@RequestMapping("/api/portal/advert")
public class AdvertAction {

	@Autowired
    private AdvertService advertService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取广告信息列表", notes = "获取广告信息列表")
    @PostMapping("/page")
    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable) {
        return advertService.getAdvertPage(request,pageable);
    }



}
