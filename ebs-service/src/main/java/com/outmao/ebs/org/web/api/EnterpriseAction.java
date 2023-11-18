package com.outmao.ebs.org.web.api;


import com.outmao.ebs.org.dto.EnterpriseDTO;
import com.outmao.ebs.org.entity.enterprise.Enterprise;
import com.outmao.ebs.org.service.EnterpriseService;
import com.outmao.ebs.org.vo.EnterpriseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Api(value = "org-enterprise", tags = "组织-企业")
@RestController
@RequestMapping("/api/org/enterprise")
public class EnterpriseAction {

    @Autowired
    private EnterpriseService enterpriseService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存企业信息", notes = "保存企业信息")
    @PostMapping("/save")
    public Enterprise saveEnterprise(@RequestBody EnterpriseDTO request){
        return enterpriseService.saveEnterprise(request);
    }

    @PostAuthorize("principal.id.equals(returnObject.userId)")
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



}
