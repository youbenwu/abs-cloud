package com.outmao.ebs.portal.web.admin.api;


import com.outmao.ebs.portal.dto.DataStatsDTO;
import com.outmao.ebs.portal.dto.GetDataStatsListDTO;
import com.outmao.ebs.portal.entity.DataStats;
import com.outmao.ebs.portal.service.DataStatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "admin-portal-data-stats", tags = "后台-门户-数据大屏统计数据")
@RestController
@RequestMapping("/api/admin/portal/data/stats")
public class DataStatsAdminAction {


	@Autowired
    private DataStatsService dataStatsService;


    @ApiOperation(value = "保存统计数据", notes = "保存统计数据")
    @PostMapping("/save")
    public DataStats saveDataStats(DataStatsDTO request) {
        return dataStatsService.saveDataStats(request);
    }


    @ApiOperation(value = "获取统计数据", notes = "获取统计数据")
    @PostMapping("/page")
    public Page<DataStats> getDataStatsPage(GetDataStatsListDTO request, Pageable pageable) {
        return dataStatsService.getDataStatsPage(request,pageable);
    }




}
