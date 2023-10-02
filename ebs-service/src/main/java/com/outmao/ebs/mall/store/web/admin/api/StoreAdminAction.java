package com.outmao.ebs.mall.store.web.admin.api;


import com.outmao.ebs.mall.store.dto.*;
import com.outmao.ebs.mall.store.entity.StoreMember;
import com.outmao.ebs.mall.store.entity.StoreProduct;
import com.outmao.ebs.mall.store.vo.StoreMemberVO;
import com.outmao.ebs.mall.store.vo.StoreProductVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.mall.store.service.StoreService;
import com.outmao.ebs.mall.store.vo.StoreVO;
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

@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "门店管理",url = "/mall/store",name = "",children = {
                @AccessPermission(title = "保存门店信息",url = "/mall/store",name = "save"),
                @AccessPermission(title = "删除门店信息",url = "/mall/store",name = "delete"),
                @AccessPermission(title = "读取门店信息",url = "/mall/store",name = "read"),
                @AccessPermission(title = "设置门店状态",url = "/mall/store",name = "status"),

                @AccessPermission(title = "保存门店店员",url = "/mall/store/member",name = "save"),
                @AccessPermission(title = "删除门店店员",url = "/mall/store/member",name = "delete"),
                @AccessPermission(title = "读取门店店员",url = "/mall/store/member",name = "read"),

                @AccessPermission(title = "保存门店商品",url = "/mall/store/product",name = "save"),
                @AccessPermission(title = "删除门店商品",url = "/mall/store/product",name = "delete"),
                @AccessPermission(title = "读取门店商品",url = "/mall/store/product",name = "read"),


        }),
})


@Api(value = "account-mall-store", tags = "后台-电商-门店")
@RestController
@RequestMapping("/api/admin/mall/store")
public class StoreAdminAction {


    @Autowired
    private StoreService storeService;



    @PreAuthorize("hasPermission('/mall/store','save')")
    @ApiOperation(value = "保存门店信息", notes = "保存门店信息")
    @PostMapping("/save")
    public void saveStore(@RequestBody StoreDTO request) {
        storeService.saveStore(request);
    }

    @PreAuthorize("hasPermission('/mall/store','status')")
    @ApiOperation(value = "设置门店状态", notes = "设置门店状态")
    @PostMapping("/setStatus")
    public void setStoreStatus(SetStoreStatusDTO request) {
        storeService.setStoreStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/store','read')")
    @ApiOperation(value = "获取门店信息", notes = "获取门店信息")
    @PostMapping("/get")
    public StoreVO getStoreVOById(Long id) {
        return storeService.getStoreVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/store','read')")
    @ApiOperation(value = "获取门店信息列表", notes = "获取门店信息列表")
    @PostMapping("/page")
    public Page<StoreVO> getStoreVOPage(GetStoreListDTO request, Pageable pageable) {
        return storeService.getStoreVOPage(request,pageable);
    }


    //StoreMember
    @PreAuthorize("hasPermission('/mall/store/member','save')")
    @ApiOperation(value = "保存店员信息", notes = "保存店员信息")
    @PostMapping("/member/save")
    public void saveStoreMember(StoreMemberDTO request){
        storeService.saveStoreMember(request);
    }

    @PreAuthorize("hasPermission('/mall/store/member','delete')")
    @ApiOperation(value = "删除店员信息", notes = "删除店员信息")
    @PostMapping("/member/delete")
    public void deleteStoreMemberById(Long id){
        storeService.deleteStoreMemberById(id);
    }

    @PreAuthorize("hasPermission('/mall/store/member','read')")
    @ApiOperation(value = "获取店员信息列表", notes = "获取店员信息列表")
    @PostMapping("/member/page")
    public Page<StoreMemberVO> getStoreMemberVOPage(GetStoreMemberListDTO request, Pageable pageable){
        return storeService.getStoreMemberVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission('/mall/store/product','save')")
    @ApiOperation(value = "保存门店商品", notes = "保存门店商品")
    @PostMapping("/product/save")
    public StoreProduct saveStoreProduct(StoreProductDTO request) {
        return storeService.saveStoreProduct(request);
    }

    @PreAuthorize("hasPermission('/mall/store/product','delete')")
    @ApiOperation(value = "删除门店商品", notes = "删除门店商品")
    @PostMapping("/product/delete")
    public void deleteStoreProductById(Long id) {
        storeService.deleteStoreProductById(id);
    }

    @PreAuthorize("hasPermission('/mall/store/product','read')")
    @ApiOperation(value = "获取门店商品列表", notes = "获取门店商品列表")
    @PostMapping("/product/page")
    public Page<StoreProductVO> getStoreProductVOPage(GetStoreProductListDTO request, Pageable pageable) {
        return storeService.getStoreProductVOPage(request,pageable);
    }



}
