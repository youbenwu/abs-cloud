package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.DataCityStatsDao;
import com.outmao.ebs.portal.dao.DataStatsDao;
import com.outmao.ebs.portal.domain.DataStatsDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.DataCityStats;
import com.outmao.ebs.portal.entity.DataStats;
import com.outmao.ebs.portal.entity.QDataCityStats;
import com.outmao.ebs.portal.vo.DataProvinceStatsVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class DataStatsDomainImpl extends BaseDomain implements DataStatsDomain {


    @Autowired
    private DataStatsDao dataStatsDao;

    @Autowired
    private DataCityStatsDao dataCityStatsDao;


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
        if(StringUtils.isEmpty(request.getChannel())){
            return dataStatsDao.findAll();
        }
        return dataStatsDao.findAllByChannel(request.getChannel());
    }


    @Override
    public Page<DataStats> getDataStatsPage(GetDataStatsListDTO request, Pageable pageable) {
        if(StringUtils.isEmpty(request.getChannel())){
            return dataStatsDao.findAll(pageable);
        }
        return dataStatsDao.findAllByChannel(request.getChannel(),pageable);
    }


    @Transactional
    @Override
    public DataCityStats saveDataCityStats(DataCityStatsDTO request) {
        DataCityStats stats=request.getId()==null?null:dataCityStatsDao.getOne(request.getId());
        if(stats==null){
            stats=new DataCityStats();
            stats.setCreateTime(new Date());
        }
        stats.setUpdateTime(new Date());
        BeanUtils.copyProperties(request,stats);
        dataCityStatsDao.save(stats);
        return stats;
    }

    @Transactional
    @Override
    public void deleteDataCityStatsById(Long id) {
        dataCityStatsDao.deleteById(id);
    }

    @Override
    public List<DataCityStats> getDataCityStatsList(GetDataCityStatsListDTO request) {
        QDataCityStats e=QDataCityStats.dataCityStats;

        Predicate p=null;
        if(request.getType()!=null){
           p=e.type.eq(request.getType());
        }

        return QF.select(e).from(e).where(p).orderBy(e.count.desc()).fetch();
    }

    @Override
    public Page<DataCityStats> getDataCityStatsPage(GetDataCityStatsListDTO request, Pageable pageable) {

        if(request.getType()!=null){
            return dataCityStatsDao.findAllByType(request.getType(),pageable);
        }

        return dataCityStatsDao.findAll(pageable);
    }


    @Override
    public List<DataProvinceStatsVO> getDataProvinceStatsVOList(GetDataProvinceStatsListDTO request) {
        QDataCityStats e=QDataCityStats.dataCityStats;

        Predicate p=null;

        if(request.getType()!=null){
            p=e.type.eq(request.getType());
        }

        List<Tuple> tuples=QF.select(e.amount.sum(),e.count.sum(),e.province).from(e).where(p).groupBy(e.province).orderBy(e.count.desc()).fetch();

        List<DataProvinceStatsVO> list=new ArrayList<>(tuples.size());

        for(Tuple t:tuples){
            DataProvinceStatsVO vo=new DataProvinceStatsVO();
            vo.setProvince(t.get(e.province));
            vo.setCount(t.get(e.count.sum()));
            vo.setAmount(t.get(e.amount.sum()));
            list.add(vo);
        }

        return list;
    }


}
