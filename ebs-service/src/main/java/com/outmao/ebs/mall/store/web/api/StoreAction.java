package com.outmao.ebs.mall.store.web.api;



import com.outmao.ebs.mall.store.dto.GetStoreListDTO;
import com.outmao.ebs.mall.store.dto.GetStoreMemberListDTO;
import com.outmao.ebs.mall.store.dto.GetStoreProductListDTO;
import com.outmao.ebs.mall.store.service.StoreService;
import com.outmao.ebs.mall.store.vo.StoreMemberVO;
import com.outmao.ebs.mall.store.vo.StoreProductVO;
import com.outmao.ebs.mall.store.vo.StoreVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Api(value = "mall-store", tags = "电商-门店")
@RestController
@RequestMapping("/api/mall/store")
public class StoreAction {


    @Autowired
    private StoreService storeService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取门店信息", notes = "获取门店信息")
    @PostMapping("/get")
    public StoreVO getStoreVOById(Long id) {
        return storeService.getStoreVOById(id);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取门店信息列表", notes = "获取门店信息列表")
    @PostMapping("/page")
    public Page<StoreVO> getStoreVOPage(GetStoreListDTO request, Pageable pageable) {
        return storeService.getStoreVOPage(request,pageable);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取店员信息列表", notes = "获取店员信息列表")
    @PostMapping("/member/page")
    public Page<StoreMemberVO> getStoreMemberVOPage(GetStoreMemberListDTO request, Pageable pageable){
        return storeService.getStoreMemberVOPage(request,pageable);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取门店商品列表", notes = "获取门店商品列表")
    @PostMapping("/product/page")
    public Page<StoreProductVO> getStoreProductVOPage(GetStoreProductListDTO request, Pageable pageable) {
        return storeService.getStoreProductVOPage(request,pageable);
    }


}
