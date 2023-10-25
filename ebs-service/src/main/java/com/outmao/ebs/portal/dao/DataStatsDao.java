package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.DataStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataStatsDao extends JpaRepository<DataStats,Long> {

    public DataStats findByType(int type);

    public List<DataStats> findAllByChannel(String channel);

    public Page<DataStats> findAllByChannel(String channel, Pageable pageable);



}
