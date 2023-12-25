package com.outmao.ebs.sys.web.api;


import com.outmao.ebs.sys.entity.App;
import com.outmao.ebs.sys.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "sys-app", tags = "系统-应用")
@RestController
@RequestMapping("/api/sys/app")
public class AppAction {

	@Autowired
    private AppService appService;


    @PreAuthorize("permitAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "应该编码 迁眼用户端小程序传：qy-wechat", required = true, dataType = "String"),
    })
    @ApiOperation(value = "获取应用", notes = "获取应用")
    @PostMapping("/getByCode")
    public App getAppByCode(String code){
        return appService.getAppByCode(code);
    }


}
