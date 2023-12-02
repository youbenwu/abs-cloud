package com.outmao.ebs.jnet.dao.warehouse;

import com.outmao.ebs.jnet.entity.material.MaterialEntity;
import com.outmao.ebs.jnet.entity.warehouse.SubWarehouse;
import com.outmao.ebs.jnet.entity.warehouse.SubWarehouseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubWarehouseMaterialDao extends JpaRepository<SubWarehouseMaterial,Long> {

    public SubWarehouseMaterial findBySubWarehouseAndMaterial(SubWarehouse subWarehouse, MaterialEntity materialEntity);

}
