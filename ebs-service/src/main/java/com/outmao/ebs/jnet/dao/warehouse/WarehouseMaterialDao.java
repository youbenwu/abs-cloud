package com.outmao.ebs.jnet.dao.warehouse;

import com.outmao.ebs.jnet.entity.material.MaterialEntity;
import com.outmao.ebs.jnet.entity.warehouse.Warehouse;
import com.outmao.ebs.jnet.entity.warehouse.WarehouseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WarehouseMaterialDao extends JpaRepository<WarehouseMaterial,Long> {

    public WarehouseMaterial findByWarehouseAndMaterial(Warehouse warehouse, MaterialEntity material);

}
