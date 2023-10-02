package com.outmao.ebs.mall.takeLook.web.admin.api;


import com.outmao.ebs.mall.takeLook.dto.TakeLookDTO;
import com.outmao.ebs.mall.takeLook.service.TakeLookService;
import com.outmao.ebs.mall.takeLook.vo.TakeLookVO;
import com.outmao.ebs.mall.takeLook.dto.SetTakeLookStatusDTO;
import com.outmao.ebs.mall.takeLook.dto.GetTakeLookListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "account-mall-takeLook", tags = "后台-电商-带看")
@RestController
@RequestMapping("/api/admin/mall/takeLook")
public class TakeLookAdminAction {

	@Autowired
    private TakeLookService takeLookService;


    @ApiOperation(value = "保存带看信息", notes = "保存带看信息")
    @PostMapping("/save")
    public void saveTakeLook(@RequestBody TakeLookDTO request){
         takeLookService.saveTakeLook(request);
    }

    @ApiOperation(value = "删除带看信息", notes = "删除带看信息")
    @PostMapping("/delete")
    public void deleteTakeLookById(Long id){
        takeLookService.deleteTakeLookById(id);
    }

    @ApiOperation(value = "设置带看信息状态", notes = "设置带看信息状态")
    @PostMapping("/setStatus")
    public void setTakeLookStatus(SetTakeLookStatusDTO request){
        takeLookService.setTakeLookStatus(request);
    }

    @ApiOperation(value = "获取带看信息", notes = "获取带看信息")
    @PostMapping("/get")
    public TakeLookVO getTakeLookVOById(Long id){
        return takeLookService.getTakeLookVOById(id);
    }

    @ApiOperation(value = "获取带看信息列表", notes = "获取带看信息列表")
    @PostMapping("/page")
    public Page<TakeLookVO> getTakeLookVOPage(GetTakeLookListDTO request, Pageable pageable){
        return takeLookService.getTakeLookVOPage(request,pageable);
    }


}
