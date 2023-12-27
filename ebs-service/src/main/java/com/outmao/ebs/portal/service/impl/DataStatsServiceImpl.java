package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.portal.common.constant.DataStatsType;
import com.outmao.ebs.portal.domain.DataStatsDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.DataCityStats;
import com.outmao.ebs.portal.entity.DataStats;
import com.outmao.ebs.portal.service.DataStatsService;
import com.outmao.ebs.portal.vo.DataProvinceStatsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataStatsServiceImpl extends BaseService implements DataStatsService, CommandLineRunner {


    @Autowired
    private DataStatsDomain dataStatsDomain;


    @Override
    public void run(String... args) throws Exception {


        if(dataStatsDomain.getCount()>0)
            return;

        DataStatsType[] types=DataStatsType.values();

        for(DataStatsType type : types){
            DataStatsDTO dataStatsDTO=new DataStatsDTO();
            dataStatsDTO.setTitle(type.getDesc());
            dataStatsDTO.setChannel(type.getChannel());
            dataStatsDTO.setType(type.getType());
            dataStatsDTO.setSuffix(type.getSuffix());
            dataStatsDTO.setValue("0");
            saveDataStats(dataStatsDTO);
        }

    }




    @Override
    public DataStats saveDataStats(DataStatsDTO request) {
        return dataStatsDomain.saveDataStats(request);
    }

    @Override
    public DataStats getDataStatsByType(int type) {
        return dataStatsDomain.getDataStatsByType(type);
    }

    @Override
    public List<DataStats> getDataStatsList(GetDataStatsListDTO request) {
        return dataStatsDomain.getDataStatsList(request);
    }

    @Override
    public Page<DataStats> getDataStatsPage(GetDataStatsListDTO request, Pageable pageable) {
        return dataStatsDomain.getDataStatsPage(request,pageable);
    }


    @Override
    public DataCityStats saveDataCityStats(DataCityStatsDTO request) {
        return dataStatsDomain.saveDataCityStats(request);
    }

    @Override
    public void deleteDataCityStatsById(Long id) {
        dataStatsDomain.deleteDataCityStatsById(id);
    }

    @Override
    public List<DataCityStats> getDataCityStatsList(GetDataCityStatsListDTO request) {
        return dataStatsDomain.getDataCityStatsList(request);
    }

    @Override
    public Page<DataCityStats> getDataCityStatsPage(GetDataCityStatsListDTO request, Pageable pageable) {
        return dataStatsDomain.getDataCityStatsPage(request,pageable);
    }

    @Override
    public List<DataProvinceStatsVO> getDataProvinceStatsVOList(GetDataProvinceStatsListDTO request) {
        return dataStatsDomain.getDataProvinceStatsVOList(request);
    }
}
