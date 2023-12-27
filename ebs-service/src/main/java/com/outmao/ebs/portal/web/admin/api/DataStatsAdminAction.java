package com.outmao.ebs.portal.web.admin.api;


import com.outmao.ebs.portal.dto.DataCityStatsDTO;
import com.outmao.ebs.portal.dto.DataStatsDTO;
import com.outmao.ebs.portal.dto.GetDataCityStatsListDTO;
import com.outmao.ebs.portal.dto.GetDataStatsListDTO;
import com.outmao.ebs.portal.entity.DataCityStats;
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

import java.util.List;


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

    @ApiOperation(value = "保存城市统计数据", notes = "保存城市统计数据")
    @PostMapping("/city/save")
    public DataCityStats saveDataCityStats(DataCityStatsDTO request){
        return dataStatsService.saveDataCityStats(request);
    }

    @ApiOperation(value = "删除城市统计数据", notes = "删除城市统计数据")
    @PostMapping("/city/delete")
    public void deleteDataCityStatsById(Long id){
        dataStatsService.deleteDataCityStatsById(id);
    }

    @ApiOperation(value = "获取城市统计数据列表", notes = "获取城市统计数据列表")
    @PostMapping("/city/list")
    public List<DataCityStats> getDataCityStatsList(GetDataCityStatsListDTO request){
        return dataStatsService.getDataCityStatsList(request);
    }

    @ApiOperation(value = "获取城市统计数据列表", notes = "获取城市统计数据列表")
    @PostMapping("/city/page")
    public Page<DataCityStats> getDataCityStatsPage(GetDataCityStatsListDTO request,Pageable pageable){
        return dataStatsService.getDataCityStatsPage(request,pageable);
    }




}
