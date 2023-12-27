package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.DataCityStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataCityStatsDao extends JpaRepository<DataCityStats,Long> {

    public List<DataCityStats> findByTypeOrderByCountDesc(Integer type);

    public Page<DataCityStats> findAllByType(Integer type, Pageable pageable);

}
