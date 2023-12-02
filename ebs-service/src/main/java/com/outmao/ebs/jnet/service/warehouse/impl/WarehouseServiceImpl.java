package com.outmao.ebs.jnet.service.warehouse.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.jnet.domain.material.MaterialDomain;
import com.outmao.ebs.jnet.domain.warehouse.WarehouseDomain;
import com.outmao.ebs.jnet.dto.warehouse.MaterialBillParamsDTO;
import com.outmao.ebs.jnet.dto.warehouse.MaterialBillUpdateDTO;
import com.outmao.ebs.jnet.dto.warehouse.WarehouseParamsDTO;
import com.outmao.ebs.jnet.entity.warehouse.*;
import com.outmao.ebs.jnet.service.warehouse.WarehouseService;
import com.outmao.ebs.jnet.vo.material.ProductionTemplateVO;
import com.outmao.ebs.jnet.vo.warehouse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class WarehouseServiceImpl extends BaseService implements WarehouseService {


    @Autowired
    WarehouseDomain warehouseDomain;

    @Autowired
    MaterialDomain materialDomain;


    @Override
    public Warehouse saveWarehouse(WarehouseParamsDTO params) {
        return warehouseDomain.saveWarehouse(params);
    }

    @Override
    public List<Warehouse> getWarehouseListByUserId(Long userId, Integer type) {
        return warehouseDomain.getWarehouseListByUserId(userId,type);
    }

    @Override
    public WarehouseVO getWarehouseVOById(Long id) {
        return warehouseDomain.getWarehouseVOById(id);
    }

    @Override
    public MaterialBill saveMaterialBill(MaterialBillParamsDTO params) {
        return warehouseDomain.saveMaterialBill(params);
    }


    @Override
    public MaterialBillVO getMaterialBillVOById(Long id) {
        return warehouseDomain.getMaterialBillVOById(id);
    }

    @Override
    public Page<MaterialBillVO> getMaterialBillVOPageByWarehouseId(Long warehouseId, Integer type, String keyword, Pageable pageable) {
        return warehouseDomain.getMaterialBillVOPageByWarehouseId(warehouseId,type,keyword,pageable);
    }

    @Override
    public MaterialBill updateMaterialBill(MaterialBillUpdateDTO params) {
        return warehouseDomain.updateMaterialBill(params);
    }

    @Override
    public void deleteMaterialBillById(Long id) {
        warehouseDomain.deleteMaterialBillById(id);
    }

    @Override
    public MaterialBill setMaterialBillToUser(Long billId, Long userId) {
        return warehouseDomain.setMaterialBillToUser(billId,userId);
    }

    @Override
    public List<MaterialBillRecord> getMaterialBillRecordListByBillId(Long billId) {
        return warehouseDomain.getMaterialBillRecordListByBillId(billId);
    }

    @Override
    public Page<WarehouseThemeVO> getThemeList(Pageable pageable) {
        return warehouseDomain.getThemeList(pageable);
    }


    @Override
    public MaterialBill revcMaterialBill(Long billId, Long userId) {
        return warehouseDomain.revcMaterialBill(billId,userId);
    }

    @Override
    public SubWarehouseVO getSubWarehouseVOById(Long id) {
        return warehouseDomain.getSubWarehouseVOById(id);
    }

    @Override
    public SubWarehouseVO getSubWarehouseVOByUserId(Long warehouseId, Long userId){

        return warehouseDomain.getSubWarehouseVOByUserId(warehouseId,userId);
    }

    @Override
    public Page<MaterialBillVO> getMaterialBillVOPageBySubWarehouseId(Long subWarehouseId, Integer type, String keyword, Pageable pageable){
        return warehouseDomain.getMaterialBillVOPageBySubWarehouseId(subWarehouseId,type,keyword,pageable);
    }

    @Override
    public Page<WarehouseSnapshoot> getWarehouseSnapshootPage(Long warehouseId, Long subWarehouseId, Pageable pageable) {
        return warehouseDomain.getWarehouseSnapshootPage(warehouseId,subWarehouseId,pageable);
    }

    @Override
    public List<WarehouseVO> getWarehouseVOListByUserId(Long userId, Integer type) {
        return warehouseDomain.getWarehouseVOListByUserId(userId,type);
    }

    @Override
    public WarehouseMaterial setWarehouseMaterialWarningQuantity(Long id, double warningQuantity) {
        return warehouseDomain.setWarehouseMaterialWarningQuantity(id,warningQuantity);
    }

    @Override
    public WarehouseStatisVO getWarehouseStatisVOByWarehouseId(Long warehouseId) {
        return warehouseDomain.getWarehouseStatisVOByWarehouseId(warehouseId);
    }

    @Override
    public void cancelMaterialBillById(Long id) {
        warehouseDomain.cancelMaterialBillById(id);
    }

    @Override
    public List<WarehouseMaterialVO> getWarehouseMaterialVOListByWarehouseId(Long warehouseId) {
        return warehouseDomain.getWarehouseMaterialVOListByWarehouseId(warehouseId);
    }

    @Override
    public List<SubWarehouseMaterialVO> getSubWarehouseMaterialVOListBySubWarehouseId(Long subWarehouseId) {
        return warehouseDomain.getSubWarehouseMaterialVOListBySubWarehouseId(subWarehouseId);
    }

    @Override
    public List<SubWarehouseMaterialVO> getSubWarehouseMaterialVOListByUserId(Long warehouseId, Long userId) {
        return warehouseDomain.getSubWarehouseMaterialVOListByUserId(warehouseId,userId);
    }


    @Override
    public void setWarehouseProductionTemplate(Long warehouseId, Long templateId) {
       warehouseDomain.setWarehouseProductionTemplate(warehouseId,templateId);
    }

    @Override
    public ProductionTemplateVO getProductionTemplateVOByWarehouseId(Long warehouseId) {
        Long id=warehouseDomain.getWarehouseProductionTemplate(warehouseId);
        if(id!=null){
           return materialDomain.getProductionTemplateVOById(id);
        }
        return null;
    }
}
