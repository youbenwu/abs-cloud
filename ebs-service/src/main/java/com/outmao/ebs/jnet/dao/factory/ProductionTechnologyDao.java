package com.outmao.ebs.jnet.dao.factory;

import com.outmao.ebs.jnet.entity.factory.Industry;
import com.outmao.ebs.jnet.entity.factory.ProductionTechnology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionTechnologyDao extends JpaRepository<ProductionTechnology,Long> {

    public List<ProductionTechnology> findAllByIndustryAndLevel(Industry industry, int level);
    public List<ProductionTechnology> findAllByLevel(int level);

}
