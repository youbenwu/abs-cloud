package com.outmao.ebs.data.dao;

import com.outmao.ebs.data.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaDao extends JpaRepository<Area,Long> {


    public Area findByParentIdAndName(Long parentId, String name);

    public Area findByCode(String code);

}
