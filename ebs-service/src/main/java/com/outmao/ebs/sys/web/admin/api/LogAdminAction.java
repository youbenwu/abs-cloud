package com.outmao.ebs.sys.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.sys.entity.Log;
import com.outmao.ebs.sys.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AccessPermissionGroup(title="系统管理",url="/sys",name="",children = {
        @AccessPermissionParent(title = "日志管理",url = "/sys/log",name = "",children = {
                @AccessPermission(title = "读取日志",url = "/sys/log",name = "read"),
        }),
})

@Api(value = "admin-sys-log", tags = "后台-系统-日志")
@RestController
@RequestMapping("/api/admin/sys/log")
public class LogAdminAction {

	@Autowired
    private LogService logService;

    @PreAuthorize("hasPermission('/sys/log','read')")
    @ApiOperation(value = "获取日志列表", notes = "获取日志列表")
    @PostMapping("/page")
    public Page<Log> getLogPage(Pageable pageable){
        return logService.getLogPage(pageable);
    }




}
