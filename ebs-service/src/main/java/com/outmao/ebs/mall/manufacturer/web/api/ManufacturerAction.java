package com.outmao.ebs.mall.manufacturer.web.api;


import com.outmao.ebs.mall.manufacturer.dto.GetCounselorDTO;
import com.outmao.ebs.mall.manufacturer.service.ManufacturerService;
import com.outmao.ebs.mall.manufacturer.vo.CounselorVO;
import com.outmao.ebs.mall.manufacturer.vo.ManufacturerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "mall-manufacturer", tags = "电商-开发商")
@RestController
@RequestMapping("/api/mall/manufacturer")
public class ManufacturerAction {

    @Autowired
    private ManufacturerService manufacturerService;



    @ApiOperation(value = "获取开发商信息", notes = "获取开发商信息")
    @PostMapping("/page")
    public Page<ManufacturerVO> getManufacturerVOPage(String keyword, Pageable pageable){
        return manufacturerService.getManufacturerVOPage(keyword,pageable);
    }


    @ApiOperation(value = "获取置业顾问信息", notes = "获取置业顾问信息")
    @PostMapping("/counselor/get")
    public CounselorVO getCounselorVOById(Long id){
        return manufacturerService.getCounselorVOById(id);
    }


    @ApiOperation(value = "获取置业顾问信息", notes = "获取置业顾问信息")
    @PostMapping("/counselor/page")
    public Page<CounselorVO> getCounselorVOPage(GetCounselorDTO request, Pageable pageable){
        return  manufacturerService.getCounselorVOPage(request,pageable);
    }



}
