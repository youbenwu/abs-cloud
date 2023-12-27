package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.DataCityStats;
import com.outmao.ebs.portal.entity.DataStats;
import com.outmao.ebs.portal.vo.DataProvinceStatsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DataStatsDomain {


    public DataStats saveDataStats(DataStatsDTO request);

    public long getCount();


    public DataStats getDataStatsByType(int type);


    public List<DataStats> getDataStatsList(GetDataStatsListDTO request);


    public Page<DataStats> getDataStatsPage(GetDataStatsListDTO request, Pageable pageable);


    public DataCityStats saveDataCityStats(DataCityStatsDTO request);

    public void deleteDataCityStatsById(Long id);

    public List<DataCityStats> getDataCityStatsList(GetDataCityStatsListDTO request);

    public Page<DataCityStats> getDataCityStatsPage(GetDataCityStatsListDTO request,Pageable pageable);

    public List<DataProvinceStatsVO> getDataProvinceStatsVOList(GetDataProvinceStatsListDTO request);

}
