package com.outmao.ebs.sys.web.api;


import com.outmao.ebs.sys.entity.Sys;
import com.outmao.ebs.sys.service.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "sys", tags = "系统")
@RestController
@RequestMapping("/api/sys")
public class SysAction {

	@Autowired
    private SysService sysService;

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取系统信息", notes = "获取系统信息")
    @PostMapping("/get")
    public Sys getSysById(Long id) {
        return sysService.getSysById(id);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取系统信息", notes = "获取系统信息")
    @PostMapping("/getBySysNo")
    public Sys getSysBySysNo(String sysNo){
        return sysService.getSysBySysNo(sysNo);
    }


}
