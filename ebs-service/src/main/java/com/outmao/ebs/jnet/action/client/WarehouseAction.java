package com.outmao.ebs.jnet.action.client;


import com.outmao.ebs.jnet.dto.warehouse.MaterialBillParamsDTO;
import com.outmao.ebs.jnet.dto.warehouse.MaterialBillUpdateDTO;
import com.outmao.ebs.jnet.dto.warehouse.WarehouseParamsDTO;
import com.outmao.ebs.jnet.entity.warehouse.*;
import com.outmao.ebs.jnet.service.warehouse.WarehouseService;
import com.outmao.ebs.jnet.vo.material.ProductionTemplateVO;
import com.outmao.ebs.jnet.vo.warehouse.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "/warehouse", tags = "仓库模块接口")
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseAction {

    @Autowired
    WarehouseService warehouseService;


    @PreAuthorize("principal.id.equals(#params.userId)")
    @ApiOperation(value = "保存仓库信息", notes = "保存仓库信息")
    @PostMapping(value = "/save")
    public Warehouse saveWarehouse(@RequestBody WarehouseParamsDTO params){
        return warehouseService.saveWarehouse(params);
    }

    @ApiOperation(value = "设置告警库存", notes = "设置告警库存 id--仓库物料的ID")
    @PostMapping(value = "/material/setWarningQuantity")
    public WarehouseMaterial setWarehouseMaterialWarningQuantity(Long id, double warningQuantity) {
        return warehouseService.setWarehouseMaterialWarningQuantity(id,warningQuantity);
    }

    @ApiOperation(value = "设置仓库当前使用的生产模板", notes = "设置仓库当前使用的生产模板")
    @PostMapping(value = "/setProductionTemplate")
    public void setWarehouseProductionTemplate(Long warehouseId, Long templateId) {
        warehouseService.setWarehouseProductionTemplate(warehouseId,templateId);
    }

    @ApiOperation(value = "获取仓库当前使用的生产模板", notes = "获取仓库当前使用的生产模板")
    @PostMapping(value = "/getProductionTemplate")
    public ProductionTemplateVO getProductionTemplateVOByWarehouseId(Long warehouseId) {
        return warehouseService.getProductionTemplateVOByWarehouseId(warehouseId);
    }

    @ApiOperation(value = "获取用户的仓库列表", notes = "获取用户的仓库列表type--0 我的仓库列表 1--我参与的仓库列表")
    @PostMapping(value = "/list")
    public List<WarehouseVO> getWarehouseVOListByUserId(Long userId,Integer type){
        return warehouseService.getWarehouseVOListByUserId(userId,type);
    }

    @ApiOperation(value = "获取仓库详情", notes = "获取仓库详情")
    @PostMapping(value = "/get")
    public WarehouseVO getWarehouseVOById(Long id){
        return warehouseService.getWarehouseVOById(id);
    }

    @ApiOperation(value = "获取子仓库详情", notes = "获取子仓库详情")
    @PostMapping(value = "/sub/get")
    public SubWarehouseVO getSubWarehouseVOById(Long id){
        return warehouseService.getSubWarehouseVOById(id);
    }

    @ApiOperation(value = "获取子仓库详情", notes = "获取子仓库详情")
    @PostMapping(value = "/sub/getByUser")
    public SubWarehouseVO getSubWarehouseVOByUserId(Long warehouseId,Long userId){
        return warehouseService.getSubWarehouseVOByUserId(warehouseId,userId);
    }

    @ApiOperation(value = "获取主仓库物料列表", notes = "获取主仓库物料列表")
    @PostMapping(value = "/materials")
    public List<WarehouseMaterialVO> getWarehouseMaterialVOListByWarehouseId(Long warehouseId) {
        return warehouseService.getWarehouseMaterialVOListByWarehouseId(warehouseId);
    }

    @ApiOperation(value = "获取子仓库物料列表", notes = "获取子仓库物料列表")
    @PostMapping(value = "/sub/materials")
    public List<SubWarehouseMaterialVO> getSubWarehouseMaterialVOListBySubWarehouseId(Long subWarehouseId,Long warehouseId,Long userId) {
        if(warehouseId!=null&&userId!=null){
            return warehouseService.getSubWarehouseMaterialVOListByUserId(warehouseId,userId);
        }
        return warehouseService.getSubWarehouseMaterialVOListBySubWarehouseId(subWarehouseId);
    }


    //MaterialBill
    @PreAuthorize("principal.id.equals(#params.userId)")
    @ApiOperation(value = "发新物料单", notes = "发新物料单")
    @PostMapping(value = "/bill/save")
    public MaterialBill saveMaterialBill(@RequestBody MaterialBillParamsDTO params){
        return warehouseService.saveMaterialBill(params);
    }

    @PreAuthorize("principal.id.equals(#params.userId)")
    @ApiOperation(value = "修改物料单", notes = "修改物料单")
    @PostMapping(value = "/bill/update")
    public MaterialBill updateMaterialBill(@RequestBody MaterialBillUpdateDTO params){
        return warehouseService.updateMaterialBill(params);
    }

    @ApiOperation(value = "删除物料单", notes = "删除物料单")
    @PostMapping(value = "/bill/delete")
    public void deleteMaterialBillById(Long id){
        warehouseService.deleteMaterialBillById(id);
    }

    @ApiOperation(value = "取消修改物料单", notes = "取消修改物料单")
    @PostMapping(value = "/bill/cancel")
    public void cancelMaterialBillById(Long id) {
        warehouseService.cancelMaterialBillById(id);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "确认接收物料单", notes = "确认接收物料单")
    @PostMapping(value = "/bill/recv")
    public MaterialBill revcMaterialBill(Long billId, Long userId){
        return warehouseService.revcMaterialBill(billId,userId);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "物料单设置接收者", notes = "物料单设置接收者")
    @PostMapping(value = "/bill/setToUser")
    public MaterialBill setMaterialBillToUser(Long billId, Long userId){
        return warehouseService.setMaterialBillToUser(billId,userId);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取物料单详情", notes = "获取物料单详情")
    @PostMapping(value = "/bill/get")
    public MaterialBillVO getMaterialBillVOById(Long id){
        return warehouseService.getMaterialBillVOById(id);
    }


    @ApiOperation(value = "获取主仓库物料单记录", notes = "获取主仓库物料单记录type--物料单类型 0--成员转发单 1--采购单 2--外发单 3--回收单,不传为全部")
    @PostMapping(value = "/bill/page")
    public Page<MaterialBillVO> getMaterialBillVOPageByWarehouseId(
            Long warehouseId, Integer type, String keyword,
            @PageableDefault(page = 0, size = 10, sort = { "updateTime" }, direction = Sort.Direction.DESC)Pageable pageable){
        return warehouseService.getMaterialBillVOPageByWarehouseId(warehouseId,type,keyword,pageable);
    }

    //type=0 发出的 type=1 收到的
    @ApiOperation(value = "获取成员物料单记录", notes = "获取成员物料单记录type=0 发出的 type=1 收到的")
    @PostMapping(value = "/sub/bill/page")
    public Page<MaterialBillVO> getMaterialBillVOPageBySubWarehouseId(
            Long subWarehouseId,Integer type,String keyword,
            @PageableDefault(page = 0, size = 10, sort = { "updateTime" }, direction = Sort.Direction.DESC)Pageable pageable){
        return warehouseService.getMaterialBillVOPageBySubWarehouseId(subWarehouseId,type,keyword,pageable);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取物料单修改记录", notes = "获取物料单修改记录")
    @PostMapping(value = "/bill/record/list")
    public List<MaterialBillRecord> getMaterialBillRecordListByBillId(Long billId){
        return warehouseService.getMaterialBillRecordListByBillId(billId);
    }

    @ApiOperation(value = "获取仓库统计列表", notes = "获取仓库统计列表--主仓库统计传warehouseId 子仓库统计传subWarehouseId")
    @PostMapping(value = "/snapshoot/page")
    public Page<WarehouseSnapshoot> getWarehouseSnapshootPage(Long warehouseId, Long subWarehouseId, Pageable pageable) {
        return warehouseService.getWarehouseSnapshootPage(warehouseId,subWarehouseId,pageable);
    }

    @ApiOperation(value = "获取仓库统计信息", notes = "获取仓库统计信息")
    @PostMapping(value = "/statis")
    public WarehouseStatisVO getWarehouseStatisVOByWarehouseId(Long warehouseId) {
        return warehouseService.getWarehouseStatisVOByWarehouseId(warehouseId);
    }

    /**
     * @author yeyi
     * @date 2019/9/23 15:52
     **/
    @ApiOperation(value = "获取仓库主题列表", notes = "获取仓库主题列表")
    @PostMapping(value = "/theme/list")
    public Page<WarehouseThemeVO> themeList(Pageable pageable){
        Page<WarehouseThemeVO> result = warehouseService.getThemeList(pageable);
        return result;
    }
}
