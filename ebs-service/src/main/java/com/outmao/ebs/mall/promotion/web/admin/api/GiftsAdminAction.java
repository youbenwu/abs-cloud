package com.outmao.ebs.mall.promotion.web.admin.api;


import com.outmao.ebs.mall.promotion.dto.GetGiftsListDTO;
import com.outmao.ebs.mall.promotion.dto.GiftsDTO;
import com.outmao.ebs.mall.promotion.dto.SetGiftsStatusDTO;
import com.outmao.ebs.mall.promotion.entity.Gifts;
import com.outmao.ebs.mall.promotion.service.GiftsService;
import com.outmao.ebs.mall.promotion.vo.GiftsVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
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



@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "赠品促销管理",url = "/mall/promotion/gifts",name = "",children = {
                @AccessPermission(title = "保存赠品促销信息",url = "/mall/promotion/gifts",name = "save"),
                @AccessPermission(title = "删除赠品促销信息",url = "/mall/promotion/gifts",name = "delete"),
                @AccessPermission(title = "读取赠品促销信息",url = "/mall/promotion/gifts",name = "read"),
                @AccessPermission(title = "设置赠品促销状态",url = "/mall/promotion/gifts",name = "status"),
        }),
})


@Api(value = "account-mall-promotion-gifts", tags = "后台-电商-促销-赠品促销")
@RestController
@RequestMapping("/api/admin/mall/promotion/gifts")
public class GiftsAdminAction {

	@Autowired
    private GiftsService giftsService;


    @PreAuthorize("hasPermission('/mall/promotion/gifts','save')")
    @ApiOperation(value = "保存赠品促销信息", notes = "保存赠品促销信息")
    @PostMapping("/save")
    public Gifts saveGifts(@RequestBody GiftsDTO request) {
        return giftsService.saveGifts(request);
    }

    @PreAuthorize("hasPermission('/mall/promotion/gifts','status')")
    @ApiOperation(value = "设置赠品促销状态", notes = "设置赠品促销状态")
    @PostMapping("/setStatus")
    public Gifts setGiftsStatus(SetGiftsStatusDTO request) {
        return giftsService.setGiftsStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/promotion/gifts','read')")
    @ApiOperation(value = "读取赠品促销信息", notes = "读取赠品促销信息")
    @PostMapping("/get")
    public GiftsVO getGiftsVOById(Long id) {
        return giftsService.getGiftsVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/promotion/gifts','read')")
    @ApiOperation(value = "读取赠品促销信息列表", notes = "读取赠品促销信息列表")
    @PostMapping("/page")
    public Page<GiftsVO> getGiftsVOPage(GetGiftsListDTO request, Pageable pageable) {
        return giftsService.getGiftsVOPage(request,pageable);
    }



}
