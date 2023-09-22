package com.outmao.ebs.data.dao;

import com.outmao.ebs.data.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayDao extends JpaRepository<Subway,Long> {


    public Subway findByParentIdAndName(Long parentId, String name);



}
