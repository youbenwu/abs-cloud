package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.DataStatsDao;
import com.outmao.ebs.portal.domain.DataStatsDomain;
import com.outmao.ebs.portal.dto.DataStatsDTO;
import com.outmao.ebs.portal.dto.GetDataStatsListDTO;
import com.outmao.ebs.portal.entity.DataStats;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


@Component
public class DataStatsDomainImpl extends BaseDomain implements DataStatsDomain {


    @Autowired
    private DataStatsDao dataStatsDao;


    @Transactional
    @Override
    public DataStats saveDataStats(DataStatsDTO request) {
        DataStats stats=request.getId()==null?null:dataStatsDao.getOne(request.getId());
        if(stats==null&&request.getType()!=null){
            stats=dataStatsDao.findByType(request.getType());
        }
        if(stats==null){
            stats=new DataStats();
            stats.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,stats,"id");
        stats.setUpdateTime(new Date());
        dataStatsDao.save(stats);
        return stats;
    }

    @Override
    public long getCount() {
        return dataStatsDao.count();
    }

    @Override
    public DataStats getDataStatsByType(int type) {
        return dataStatsDao.findByType(type);
    }

    @Override
    public List<DataStats> getDataStatsList(GetDataStatsListDTO request) {
        return dataStatsDao.findAllByGroup(request.getGroup());
    }


    @Override
    public Page<DataStats> getDataStatsPage(GetDataStatsListDTO request, Pageable pageable) {
        if(StringUtils.isEmpty(request.getGroup())){
            return dataStatsDao.findAllByGroup(pageable);
        }
        return dataStatsDao.findAllByGroup(request.getGroup(),pageable);
    }

}
