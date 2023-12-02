package com.outmao.ebs.jnet.dao.warehouse;

import com.outmao.ebs.jnet.entity.warehouse.MaterialBill;
import com.outmao.ebs.jnet.entity.warehouse.MaterialBillMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialBillMaterialDao extends JpaRepository<MaterialBillMaterial,Long> {

    public List<MaterialBillMaterial> findAllByBill(MaterialBill bill);

    public void deleteAllByBill(MaterialBill bill);

}
