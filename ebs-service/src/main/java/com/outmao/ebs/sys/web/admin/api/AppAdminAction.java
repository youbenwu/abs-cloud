package com.outmao.ebs.sys.web.admin.api;


import com.outmao.ebs.sys.dto.AppDTO;
import com.outmao.ebs.sys.entity.App;
import com.outmao.ebs.sys.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "admin-sys-app", tags = "后台-系统-应用")
@RestController
@RequestMapping("/api/admin/sys/app")
public class AppAdminAction {

	@Autowired
    private AppService appService;

    @PreAuthorize("hasPermission('/sys/app','save')")
    @ApiOperation(value = "保存应用", notes = "保存应用")
    @PostMapping("/save")
    public App saveApp(AppDTO request){
        return appService.saveApp(request);
    }

    @PreAuthorize("hasPermission('/sys/app','read')")
    @ApiOperation(value = "获取应用", notes = "获取应用")
    @PostMapping("/getByCode")
    public App getAppByCode(String code){
        return appService.getAppByCode(code);
    }


}
