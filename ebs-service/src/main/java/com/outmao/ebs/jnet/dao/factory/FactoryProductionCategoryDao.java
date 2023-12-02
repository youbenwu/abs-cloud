package com.outmao.ebs.jnet.dao.factory;

import com.outmao.ebs.jnet.entity.factory.Factory;
import com.outmao.ebs.jnet.entity.factory.FactoryProductionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactoryProductionCategoryDao extends JpaRepository<FactoryProductionCategory,Long> {

    public List<FactoryProductionCategory> findAllByFactory(Factory factory);

    @Modifying
    @Query("delete from FactoryProductionCategory c where c.factory=?1")
    public void deleteAllByFactory(Factory factory);


}
