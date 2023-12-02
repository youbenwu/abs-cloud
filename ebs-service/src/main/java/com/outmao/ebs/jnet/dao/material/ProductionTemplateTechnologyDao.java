package com.outmao.ebs.jnet.dao.material;

import com.outmao.ebs.jnet.entity.material.ProductionTemplateTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductionTemplateTechnologyDao extends JpaRepository<ProductionTemplateTechnology, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM z_production_template_technology ptt WHERE ptt.template_id IN (:templateIds) AND ptt.is_deleted = 0 ORDER BY ptt.weight DESC, ptt.id DESC ")
    List<ProductionTemplateTechnology> findByTemplateIds(@Param("templateIds") List<Long> templateIds);

    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE ptt FROM z_production_template_technology ptt WHERE ptt.template_id IN(:templateIds) AND ptt.is_deleted = 0")
    int deleteByTemplateIds(@Param("templateIds") List<Long> templateIds);



}
