package com.outmao.ebs.jnet.dao.warehouse;

import com.outmao.ebs.jnet.entity.warehouse.MaterialBill;
import com.outmao.ebs.jnet.entity.warehouse.MaterialBillRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialBillRecordDao extends JpaRepository<MaterialBillRecord,Long> {

    public List<MaterialBillRecord> findAllByBillOrderByIdDesc(MaterialBill bill);

    public void deleteAllByBill(MaterialBill bill);

    public void deleteById(Long id);

}
