package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.DataStatsDTO;
import com.outmao.ebs.portal.dto.GetDataStatsListDTO;
import com.outmao.ebs.portal.entity.DataStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DataStatsService {


    public DataStats saveDataStats(DataStatsDTO request);


    public DataStats getDataStatsByType(int type);


    public List<DataStats> getDataStatsList(GetDataStatsListDTO request);


    public Page<DataStats> getDataStatsPage(GetDataStatsListDTO request, Pageable pageable);




}
