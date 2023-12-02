package com.outmao.ebs.jnet.domain.warehouse;

import com.outmao.ebs.jnet.dto.warehouse.MaterialBillParamsDTO;
import com.outmao.ebs.jnet.dto.warehouse.MaterialBillUpdateDTO;
import com.outmao.ebs.jnet.dto.warehouse.WarehouseParamsDTO;
import com.outmao.ebs.jnet.entity.warehouse.*;
import com.outmao.ebs.jnet.vo.warehouse.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WarehouseDomain {

    //Warehouse

    public Warehouse saveWarehouse(WarehouseParamsDTO params);


    public WarehouseMaterial setWarehouseMaterialWarningQuantity(Long id, double warningQuantity);


    public void setWarehouseProductionTemplate(Long warehouseId, Long templateId);

    public Long getWarehouseProductionTemplate(Long warehouseId);


    //type--0我的主仓库 1--我参与的主仓库
    public List<Warehouse> getWarehouseListByUserId(Long userId, Integer type);


    //type--0我的主仓库 1--我参与的主仓库
    public List<WarehouseVO> getWarehouseVOListByUserId(Long userId, Integer type);

    public WarehouseVO getWarehouseVOById(Long id);


    public List<WarehouseMaterialVO> getWarehouseMaterialVOListByWarehouseId(Long warehouseId);


    //SubWarehouse
    public SubWarehouseVO getSubWarehouseVOById(Long id);
    public SubWarehouseVO getSubWarehouseVOByUserId(Long warehouseId, Long userId);

    public List<SubWarehouseMaterialVO> getSubWarehouseMaterialVOListBySubWarehouseId(Long subWarehouseId);

    public List<SubWarehouseMaterialVO> getSubWarehouseMaterialVOListByUserId(Long warehouseId, Long userId);


    //MaterialBill

    public MaterialBill saveMaterialBill(MaterialBillParamsDTO params);

    public MaterialBill updateMaterialBill(MaterialBillUpdateDTO params);

    public List<MaterialBillRecord> getMaterialBillRecordListByBillId(Long billId);

    public void deleteMaterialBillById(Long id);

    public void cancelMaterialBillById(Long id);

    public MaterialBill setMaterialBillToUser(Long billId, Long userId);

    public MaterialBill revcMaterialBill(Long billId, Long userId);

    public List<MaterialBillMaterialVO> getMaterialBillMaterialVOListByBillId(Long billId);

    public MaterialBillVO getMaterialBillVOById(Long id);

    public Page<MaterialBillVO> getMaterialBillVOPageByWarehouseId(Long warehouseId, Integer type, String keyword, Pageable pageable);

    //type=0 发出的 type=1 收到的
    public Page<MaterialBillVO> getMaterialBillVOPageBySubWarehouseId(Long subWarehouseId, Integer type, String keyword, Pageable pageable);


    public Page<WarehouseSnapshoot> getWarehouseSnapshootPage(Long warehouseId, Long subWarehouseId, Pageable pageable);

    public WarehouseStatisVO getWarehouseStatisVOByWarehouseId(Long warehouseId);


    public Page<WarehouseThemeVO> getThemeList(Pageable pageable);

}
