package com.outmao.ebs.org.web.api;


import com.outmao.ebs.org.dto.GetOrgListDTO;
import com.outmao.ebs.org.dto.OrgDTO;
import com.outmao.ebs.org.dto.RegisterOrgDTO;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.org.vo.OrgVO;
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

import java.util.Collection;
import java.util.List;


@Api(value = "org", tags = "组织")
@RestController
@RequestMapping("/api/org")
public class OrgAction {

	@Autowired
    private OrgService orgService;


    /**
     *
     * 注册组织信息
     *
     * */
    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "注册组织信息", notes = "注册组织信息")
    @PostMapping("/register")
    public void registerOrg(RegisterOrgDTO request){
        request.setType(Org.TYPE_TENANT);
        orgService.registerOrg(request);
    }
    /**
     *
     * 修改组织信息
     *
     * */
    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "修改组织信息", notes = "修改组织信息")
    @PostMapping("/save")
    public void saveOrg(OrgDTO request){
        orgService.saveOrg(request);
    }

    /**
     *
     * 获取组织信息列表
     *
     * */
    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "获取组织信息列表", notes = "获取组织信息列表")
    @PostMapping("/page")
    public Page<OrgVO> getOrgVOPage(GetOrgListDTO request, Pageable pageable){
        return orgService.getOrgVOPage(request,pageable);
    }


    @ApiOperation(value = "获取组织信息", notes = "获取组织信息")
    @PostMapping("/get")
    public OrgVO getOrgVOById(Long id) {
        return orgService.getOrgVOById(id);
    }



    @ApiOperation(value = "获取组织信息列表", notes = "获取组织信息列表")
    @PostMapping("/listByIdIn")
    public List<OrgVO> getOrgVOListByIdIn(@RequestBody Collection<Long> idIn){
        return orgService.getOrgVOListByIdIn(idIn);
    }


}
