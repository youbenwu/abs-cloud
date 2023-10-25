package com.outmao.ebs.portal.web.api;


import com.outmao.ebs.portal.dto.GetDataStatsListDTO;
import com.outmao.ebs.portal.entity.DataStats;
import com.outmao.ebs.portal.service.DataStatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Api(value = "portal-data-stats", tags = "门户-数据大屏统计数据")
@RestController
@RequestMapping("/api/portal/data/stats")
public class DattaStatsAction {


	@Autowired
    private DataStatsService dataStatsService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取统计数据", notes = "获取统计数据")
    @PostMapping("/getByType")
    public DataStats getDataStatsByType(int type) {
        return dataStatsService.getDataStatsByType(type);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取统计数据", notes = "获取统计数据")
    @PostMapping("/list")
    public List<DataStats> getDataStatsList(GetDataStatsListDTO request) {
        return dataStatsService.getDataStatsList(request);
    }



}
