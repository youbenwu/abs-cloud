package com.outmao.ebs.jnet.dao.material;


import com.outmao.ebs.jnet.entity.material.MaterialPlan;
import com.outmao.ebs.jnet.entity.material.MaterialPlanMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MaterialPlanMaterialDao extends JpaRepository<MaterialPlanMaterial,Long> {

    @Modifying
    @Query("delete from MaterialPlanMaterial c where c.plan=?1")
    public void deleteAllByMaterialPlan(MaterialPlan plan);

}
