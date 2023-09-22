package com.outmao.ebs.data.web.api;


import com.outmao.ebs.data.dto.EnterpriseDTO;
import com.outmao.ebs.data.dto.GetEnterpriseListDTO;
import com.outmao.ebs.data.entity.enterprise.Enterprise;
import com.outmao.ebs.data.service.EnterpriseService;
import com.outmao.ebs.data.vo.EnterpriseVO;
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
import java.util.List;


@Api(value = "data-enterprise", tags = "数据-企业")
@RestController
@RequestMapping("/api/data/enterprise")
public class EnterpriseAction {

    @Autowired
    EnterpriseService enterpriseService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存企业信息", notes = "保存企业信息")
    @PostMapping("/save")
    public Enterprise saveEnterprise(@RequestBody EnterpriseDTO request){
        return enterpriseService.saveEnterprise(request);
    }

    @ApiOperation(value = "获取企业信息", notes = "获取企业信息")
    @PostMapping("/get")
    public EnterpriseVO getEnterpriseVOById(Long id){
        return enterpriseService.getEnterpriseVOById(id);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取用户企业信息", notes = "获取用户企业信息")
    @PostMapping("/list")
    public List<EnterpriseVO> getEnterpriseVOListByUserId(Long userId){
        return enterpriseService.getEnterpriseVOListByUserId(userId);
    }


    @ApiOperation(value = "获取用户企业信息列表", notes = "获取用户企业信息列表")
    @PostMapping("/page")
    public Page<EnterpriseVO> getEnterpriseVOPage(GetEnterpriseListDTO request, Pageable pageable){
        return enterpriseService.getEnterpriseVOPage(request,pageable);
    }






}
