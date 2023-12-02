package com.outmao.ebs.jnet.dao.material;


import com.outmao.ebs.jnet.entity.material.ProductionTemplate;
import com.outmao.ebs.jnet.entity.material.ProductionTemplateMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionTemplateMaterialDao extends JpaRepository<ProductionTemplateMaterial,Long> {

    public List<ProductionTemplateMaterial> findAllByTemplate(ProductionTemplate template);

    public void deleteAllByTemplate(ProductionTemplate template);

}
