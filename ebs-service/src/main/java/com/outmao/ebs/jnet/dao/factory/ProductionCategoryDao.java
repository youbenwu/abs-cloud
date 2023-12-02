package com.outmao.ebs.jnet.dao.factory;

import com.outmao.ebs.jnet.entity.factory.Industry;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionCategoryDao extends JpaRepository<ProductionCategory,Long> {

    public List<ProductionCategory> findAllByIndustryAndLevelOrderBySortDesc(Industry industry, int level);

    /**
     * @author yeyi
     * @date 2019/9/16 12:18
     **/
    @Query(nativeQuery = true,
            value = "SELECT * FROM z_production_category pc WHERE pc.id IN(:ids) ORDER BY pc.create_time DESC ")
    public List<ProductionCategory> findByIdIn(@Param("ids") List<Long> ids);
}
