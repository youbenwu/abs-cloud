package com.outmao.ebs.data.web.api;


import com.outmao.ebs.data.dto.BrandDTO;
import com.outmao.ebs.data.dto.GetBrandListDTO;
import com.outmao.ebs.data.entity.Brand;
import com.outmao.ebs.data.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "data-brand", tags = "数据-品牌")
@RestController
@RequestMapping("/api/data/brand")
public class BrandAction {

    @Autowired
    private BrandService brandService;

    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存品牌信息", notes = "保存品牌信息")
    @PostMapping("/save")
    public Brand saveBrand(BrandDTO request){
        return brandService.saveBrand(request);
    }

    @ApiOperation(value = "删除品牌信息", notes = "删除品牌信息")
    @PostMapping("/delete")
    public void deleteBrandById(Long id) {
        brandService.deleteBrandById(id);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取品牌信息", notes = "获取品牌信息")
    @PostMapping("/get")
    public Brand getBrandById(Long id){
        return brandService.getBrandById(id);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取品牌信息列表", notes = "获取品牌信息列表")
    @PostMapping("/page")
    public Page<Brand> getBrandPage(GetBrandListDTO request, Pageable pageable){
        return brandService.getBrandPage(request,pageable);
    }


}
