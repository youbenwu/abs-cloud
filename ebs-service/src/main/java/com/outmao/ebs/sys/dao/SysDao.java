package com.outmao.ebs.sys.dao;

import com.outmao.ebs.sys.entity.Sys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysDao extends JpaRepository<Sys,Long> {

    public List<Sys> findAllByIdIn(Collection<Long> idIn);

    public Sys findByType(int type);

    public Sys findBySysNo(String sysNo);

}
