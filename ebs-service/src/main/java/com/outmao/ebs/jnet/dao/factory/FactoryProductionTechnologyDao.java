package com.outmao.ebs.jnet.dao.factory;

import com.outmao.ebs.jnet.entity.factory.Factory;
import com.outmao.ebs.jnet.entity.factory.FactoryProductionTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactoryProductionTechnologyDao extends JpaRepository<FactoryProductionTechnology,Long> {


    public List<FactoryProductionTechnology> findAllByFactory(Factory factory);
    @Modifying
    @Query("delete from FactoryProductionTechnology c where c.factory=?1")
    public void deleteAllByFactory(Factory factory);
}
