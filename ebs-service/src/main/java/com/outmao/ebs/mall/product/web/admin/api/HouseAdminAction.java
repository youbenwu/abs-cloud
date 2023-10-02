package com.outmao.ebs.mall.product.web.admin.api;


import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.service.HouseService;
import com.outmao.ebs.mall.product.vo.HouseVO;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.List;



@Api(value = "account-mall-house", tags = "后台-电商-房源")
@RestController
@RequestMapping("/api/admin/mall/house")
public class HouseAdminAction {

	@Autowired
    private HouseService houseService;


    @PreAuthorize("hasPermission('/mall/product','save')")
    @ApiOperation(value = "保存房源信息", notes = "保存房源信息")
    @PostMapping("/save")
    public void saveHouse(@RequestBody HouseDTO request){
        houseService.saveHouse(request);
    }

    @PostAuthorize("hasPermission('/mall/product','read')")
    @ApiOperation(value = "获取房源信息", notes = "获取房源信息")
    @PostMapping("/get")
    public HouseVO getHouseVOById(Long id){
        return houseService.getHouseVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/product','read')")
    @ApiOperation(value = "获取房源信息列表", notes = "获取房源信息列表")
    @PostMapping("/page")
    public Page<HouseVO> getHouseVOPage(@RequestBody GetHouseListDTO request, Pageable pageable){

        return  houseService.getHouseVOPage(request,pageable);
    }

    @ApiOperation(value = "获取房源快照列表", notes = "获取房源快照列表ids--快照ID列表")
    @PostMapping("/snapshot/list")
    public List<HouseVO> getHouseSnapshotVOListByIdIn(@RequestBody Collection<Long> ids) {
        return houseService.getHouseSnapshotVOListByIdIn(ids);
    }

    //获取首页房源推荐
    @ApiOperation(value = "获取首页房源推荐列表", notes = "获取首页房源推荐列表")
    @PostMapping("/recommend/page")
    public Page<RecommendVO<HouseVO>> getHouseRecommendVOPage(GetRecommendListDTO request,Pageable pageable){
        return houseService.getHouseRecommendVOPage(request,pageable);
    }


}
