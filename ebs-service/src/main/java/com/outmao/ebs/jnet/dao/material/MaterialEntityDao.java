package com.outmao.ebs.jnet.dao.material;

import com.outmao.ebs.jnet.entity.material.Material;
import com.outmao.ebs.jnet.entity.material.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialEntityDao extends JpaRepository<MaterialEntity,Long> {


    public List<MaterialEntity> findAllByMaterial(Material material);

    public List<MaterialEntity> findAllByMaterialAndDeleted(Material material, boolean deleted);



}
