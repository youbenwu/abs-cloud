package com.outmao.ebs.jnet.domain.warehouse.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.Arith;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.entity.QUserDetails;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.dao.material.MaterialEntityDao;
import com.outmao.ebs.jnet.dao.material.MaterialSupplierDao;
import com.outmao.ebs.jnet.dao.warehouse.*;
import com.outmao.ebs.jnet.domain.material.MaterialDomain;
import com.outmao.ebs.jnet.domain.warehouse.WarehouseDomain;
import com.outmao.ebs.jnet.dto.warehouse.*;
import com.outmao.ebs.jnet.entity.material.MaterialEntity;
import com.outmao.ebs.jnet.entity.warehouse.*;
import com.outmao.ebs.jnet.vo.material.MaterialEntityVO;
import com.outmao.ebs.jnet.vo.warehouse.*;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.user.vo.ContactUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Transactional
@Component
public class WarehouseDomainImpl extends BaseDomain implements WarehouseDomain {


    @Autowired
    WarehouseDao warehouseDao;

    @Autowired
    WarehouseMaterialDao warehouseMaterialDao;

    @Autowired
    SubWarehouseDao subWarehouseDao;

    @Autowired
    SubWarehouseMaterialDao subWarehouseMaterialDao;

    @Autowired
    MaterialBillDao materialBillDao;

    @Autowired
    MaterialBillMaterialDao materialBillMaterialDao;

    @Autowired
    MaterialBillRecordDao materialBillRecordDao;

    @Autowired
    MaterialEntityDao materialEntityDao;

    @Autowired
    SubWarehouseSessionDao subWarehouseSessionDao;

    @Autowired
    WarehouseSnapshootDao warehouseSnapshootDao;

    @Autowired
    MaterialSupplierDao materialSupplierDao;


    @Autowired
    UserDao userDao;

    @Autowired
    QrCodeService qrcodeService;

    @Autowired
    UserService userService;




    @Autowired
    MaterialDomain materialDomain;


    @Value("${config.base-url}")
    private String baseUrl;


    //从物料单进料
    private WarehouseMaterial warehouseMaterialAdd(Warehouse warehouse, MaterialBillMaterial m, boolean isInc, boolean updateTotal){

        WarehouseMaterial material=warehouseMaterialDao.findByWarehouseAndMaterial(warehouse,m.getMaterial());
        if(material==null){
            material=new WarehouseMaterial();
            material.setWarehouse(warehouse);
            material.setMaterial(m.getMaterial());
            material.setUnit(m.getUnit());
            material.setCreateTime(new Date());
        }
        if(!material.getUnit().equals(m.getUnit())){
            throw new BusinessException("单位必须和第一次入库时的单位一致");
        }

        double quantity=isInc?m.getQuantityInc():m.getQuantity();

        if(quantity==0)
            return material;

        material.setQuantity(Arith.add(material.getQuantity(),quantity));
        //material.setAmount(material.getAmount()+m.getAmount());
        if(updateTotal){
            material.setTotalQuantity(Arith.add(material.getTotalQuantity(),quantity));
            //material.setTotalAmount(material.getTotalAmount()+m.getAmount());
        }

        material.setUpdateTime(new Date());
        material=warehouseMaterialDao.save(material);

        return material;
    }


    //从物料单减料
    private WarehouseMaterial warehouseMaterialDec(Warehouse warehouse, MaterialBillMaterial m, boolean isInc, boolean updateTotal){

        WarehouseMaterial material=warehouseMaterialDao.findByWarehouseAndMaterial(warehouse,m.getMaterial());

        if(material==null){
            throw new BusinessException("物料不存在");
        }

        if(!material.getUnit().equals(m.getUnit())){
            throw new BusinessException("单位必须和第一次入库时的单位一致");
        }

        double quantity=isInc?m.getQuantityInc():m.getQuantity();

        if(quantity==0)
            return material;

        if(quantity>material.getQuantity()){
            throw new BusinessException("库存不足");
        }

        material.setQuantity(Arith.sub(material.getQuantity(),quantity));
        //material.setAmount(material.getAmount()-m.getAmount());
        if(updateTotal){
            material.setTotalQuantity(Arith.sub(material.getTotalQuantity(),quantity));
            //material.setTotalAmount(material.getTotalAmount()-m.getAmount());
        }
        material=warehouseMaterialDao.save(material);

        return material;
    }


    //子仓库从物料单进料
    private SubWarehouseMaterial subWarehouseMaterialAdd(SubWarehouse subWarehouse, MaterialBillMaterial m, boolean isInc){

        SubWarehouseMaterial material=subWarehouseMaterialDao.findBySubWarehouseAndMaterial(subWarehouse,m.getMaterial());
        if(material==null){
            material=new SubWarehouseMaterial();
            material.setSubWarehouse(subWarehouse);
            material.setMaterial(m.getMaterial());
            material.setUnit(m.getUnit());
            material.setCreateTime(new Date());
        }

        if(!material.getUnit().equals(m.getUnit())){
            throw new BusinessException("单位必须和第一次入库时的单位一致");
        }

        double quantity=isInc?m.getQuantityInc():m.getQuantity();

        if(quantity==0)
            return material;

        material.setQuantity(Arith.add(material.getQuantity(),quantity));
        //material.setAmount(material.getAmount()+m.getAmount());

        material.setUpdateTime(new Date());
        material=subWarehouseMaterialDao.save(material);

        return material;
    }

    //子仓库从物料单减料
    private SubWarehouseMaterial subWarehouseMaterialDec(SubWarehouse subWarehouse, MaterialBillMaterial m, boolean isInc){

        SubWarehouseMaterial material=subWarehouseMaterialDao.findBySubWarehouseAndMaterial(subWarehouse,m.getMaterial());
        if(material==null){
            throw new BusinessException("物料不存在");
        }

        if(!material.getUnit().equals(m.getUnit())){
            throw new BusinessException("单位必须和第一次入库时的单位一致");
        }


        double quantity=isInc?m.getQuantityInc():m.getQuantity();

        if(quantity==0)
            return material;

        if(quantity>material.getQuantity()){
            throw new BusinessException("库存不足");
        }

        material.setQuantity(Arith.sub(material.getQuantity(),quantity));
        //material.setAmount(material.getAmount()-m.getAmount());

        material.setUpdateTime(new Date());
        material=subWarehouseMaterialDao.save(material);

        return material;
    }

    /*

    //从物料单进料
    private void warehouseMaterialAddAllByMaterialBill(MaterialBill bill){


        boolean updateTotal=(bill.getType()==1);

        Warehouse warehouse=bill.getWarehouse();

        //处理仓库物料
        double quantity=0;
        List<MaterialBillMaterial> billMaterials=materialBillMaterialDao.findAllByBill(bill);
        for (MaterialBillMaterial m:billMaterials){
            warehouseMaterialAdd(warehouse,m,true,updateTotal);
            quantity+=m.getQuantityInc();
        }

        if(quantity==0)
            return;

        //warehouse.setAmount(warehouse.getAmount()+bill.getAmount());
        warehouse.setQuantity(warehouse.getQuantity()+quantity);

        if(updateTotal) {
            //warehouse.setTotalAmount(warehouse.getTotalAmount() + bill.getAmount());
            warehouse.setTotalQuantity(warehouse.getTotalQuantity() + quantity);
        }

        warehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*warehouse.getQuantity()/warehouse.getTotalQuantity()));

        warehouseDao.save(warehouse);

        saveWarehouseSnapshoot(warehouse);

    }

    //从物料单减料
    private void warehouseMaterialDecAllByMaterialBill(MaterialBill bill){

        boolean updateTotal=(bill.getType()==1);

        Warehouse warehouse=bill.getWarehouse();

        //处理仓库物料
        double quantity=0;
        List<MaterialBillMaterial> billMaterials=materialBillMaterialDao.findAllByBill(bill);
        for (MaterialBillMaterial m:billMaterials){
            warehouseMaterialDec(warehouse,m,true,updateTotal);
            quantity+=m.getQuantityInc();
        }

        if(quantity==0)
            return;

        //warehouse.setAmount(warehouse.getAmount()-bill.getAmount());
        warehouse.setQuantity(warehouse.getQuantity()-quantity);

        if(updateTotal) {
            //warehouse.setTotalAmount(warehouse.getTotalAmount() - bill.getAmount());
            warehouse.setTotalQuantity(warehouse.getTotalQuantity() - quantity);
        }

        warehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*warehouse.getQuantity()/warehouse.getTotalQuantity()));

        warehouseDao.save(warehouse);

        saveWarehouseSnapshoot(warehouse);
    }



    //子仓库从物料单进料
    private void subWarehouseMaterialAddAllByMaterialBill(SubWarehouse subWarehouse,MaterialBill bill){

        Warehouse warehouse=bill.getWarehouse();

        //处理仓库物料
        double quantity=0;
        List<MaterialBillMaterial> billMaterials=materialBillMaterialDao.findAllByBill(bill);
        for (MaterialBillMaterial m:billMaterials){
            subWarehouseMaterialAdd(subWarehouse,m,true);
            quantity+=m.getQuantityInc();
        }

        if(quantity==0)
            return;

        //subWarehouse.setAmount(subWarehouse.getAmount()+bill.getAmount());
        subWarehouse.setQuantity(subWarehouse.getQuantity()+quantity);

        subWarehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*subWarehouse.getQuantity()/warehouse.getTotalQuantity()));

        subWarehouseDao.save(subWarehouse);

        saveSubWarehouseSnapshoot(subWarehouse);
    }


    //子仓库从物料单减料
    private void subWarehouseMaterialDecAllByMaterialBill(SubWarehouse subWarehouse,MaterialBill bill){

        Warehouse warehouse=bill.getWarehouse();

        //处理仓库物料
        double quantity=0;
        List<MaterialBillMaterial> billMaterials=materialBillMaterialDao.findAllByBill(bill);
        for (MaterialBillMaterial m:billMaterials){
            subWarehouseMaterialDec(subWarehouse,m,true);
            quantity+=m.getQuantityInc();
        }

        if(quantity==0)
            return;

        //subWarehouse.setAmount(subWarehouse.getAmount()-bill.getAmount());
        subWarehouse.setQuantity(subWarehouse.getQuantity()-quantity);

        subWarehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*subWarehouse.getQuantity()/warehouse.getTotalQuantity()));

        subWarehouseDao.save(subWarehouse);

        saveSubWarehouseSnapshoot(subWarehouse);
    }

     */

    private void updateWarehouseLock(MaterialBill bill){

        Warehouse warehouse=bill.getWarehouse();
        SubWarehouse fromSubWarehouse=subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getUser());
        SubWarehouse toSubWarehouse=bill.getToUser()==null?null:subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getToUser());

        boolean updateTotal=(bill.getType()==1);

        //处理仓库物料
        double quantity=0;
        double fromQuantity=0;
        double toQuantity=0;

        List<MaterialBillMaterial> billMaterials=materialBillMaterialDao.findAllByBill(bill);
        for (MaterialBillMaterial m:billMaterials){
            if(m.getQuantityInc()>0){
                //From出料
                if(bill.getType()==0){
                    //转发单
                    //从A减料
                    subWarehouseMaterialDec(fromSubWarehouse,m,true);
                    fromQuantity=Arith.sub(fromQuantity,m.getQuantityInc());
                    //fromQuantity-=m.getQuantityInc();
                }else if(bill.getType()==1){
                    //采购单
                    // 不用出库
                }else if(bill.getType()==2){
                    //外发单
                    //从主仓库减料
                    warehouseMaterialDec(warehouse,m,true,updateTotal);
                    quantity= Arith.sub(quantity,m.getQuantityInc());
                    //quantity-=m.getQuantityInc();
                }else if(bill.getType()==3){
                    //回收单
                    //从A减料
                    subWarehouseMaterialDec(fromSubWarehouse,m,true);
                    fromQuantity=Arith.sub(fromQuantity,m.getQuantityInc());
                    //fromQuantity-=m.getQuantityInc();
                }

            }else if(m.getQuantityInc()<0){

                //To出料
                if(bill.getType()==0){
                    //转发单
                    //从C减料
                    subWarehouseMaterialAdd(toSubWarehouse,m,true);
                    toQuantity=Arith.add(toQuantity,m.getQuantityInc());
                    //toQuantity+=m.getQuantityInc();
                }else if(bill.getType()==1){
                    //采购单
                    //从C减料
                    warehouseMaterialAdd(warehouse,m,true,updateTotal);
                    quantity=Arith.add(quantity,m.getQuantityInc());
                    //quantity+=m.getQuantityInc();
                }else if(bill.getType()==2){
                    //外发单
                    //从C减料
                    subWarehouseMaterialAdd(toSubWarehouse,m,true);
                    toQuantity=Arith.add(toQuantity,m.getQuantityInc());
                    //toQuantity+=m.getQuantityInc();
                }else if(bill.getType()==3){
                    //回收单
                    //从C减料
                    warehouseMaterialAdd(warehouse,m,true,updateTotal);
                    quantity=Arith.add(quantity,m.getQuantityInc());
                    //quantity+=m.getQuantityInc();
                }

            }
        }


        if(quantity!=0){
            warehouse.setQuantity(Arith.add(warehouse.getQuantity(),quantity));
            if(updateTotal) {
                warehouse.setTotalQuantity(Arith.add(warehouse.getTotalQuantity(),quantity));
            }
            warehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*warehouse.getQuantity()/warehouse.getTotalQuantity()));
            warehouseDao.save(warehouse);
            saveWarehouseSnapshoot(warehouse);
        }

        if(fromQuantity!=0){
            fromSubWarehouse.setQuantity(Arith.add(fromSubWarehouse.getQuantity(),fromQuantity));
            fromSubWarehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*fromSubWarehouse.getQuantity()/warehouse.getTotalQuantity()));
            subWarehouseDao.save(fromSubWarehouse);
            saveSubWarehouseSnapshoot(fromSubWarehouse);
        }

        if(toQuantity!=0){
            toSubWarehouse.setQuantity(Arith.add(toSubWarehouse.getQuantity(),toQuantity));
            toSubWarehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*toSubWarehouse.getQuantity()/warehouse.getTotalQuantity()));
            subWarehouseDao.save(toSubWarehouse);
            saveSubWarehouseSnapshoot(toSubWarehouse);
        }

    }

    private void updateWarehouseLockBack(MaterialBill bill){

        Warehouse warehouse=bill.getWarehouse();
        SubWarehouse fromSubWarehouse=subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getUser());
        SubWarehouse toSubWarehouse=bill.getToUser()==null?null:subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getToUser());

        boolean updateTotal=(bill.getType()==1);

        //处理仓库物料
        double quantity=0;
        double fromQuantity=0;
        double toQuantity=0;

        List<MaterialBillMaterial> billMaterials=materialBillMaterialDao.findAllByBill(bill);
        for (MaterialBillMaterial m:billMaterials){
            if(m.getQuantityInc()>0){
                //From出料 反方向
                if(bill.getType()==0){
                    //转发单
                    //从A减料 反方向
                    subWarehouseMaterialAdd(fromSubWarehouse,m,true);
                    fromQuantity=Arith.add(fromQuantity,m.getQuantityInc());
                    //fromQuantity+=m.getQuantityInc();
                }else if(bill.getType()==1){
                    //采购单
                    // 不用出库 反方向
                }else if(bill.getType()==2){
                    //外发单
                    //从主仓库减料 反方向
                    warehouseMaterialAdd(warehouse,m,true,updateTotal);
                    quantity=Arith.add(quantity,m.getQuantityInc());
                    //quantity+=m.getQuantityInc();
                }else if(bill.getType()==3){
                    //回收单
                    //从A减料 反方向
                    subWarehouseMaterialAdd(fromSubWarehouse,m,true);
                    fromQuantity=Arith.add(fromQuantity,m.getQuantityInc());
                    //fromQuantity+=m.getQuantityInc();
                }

            }else if(m.getQuantityInc()<0){

                //To出料 反方向
                if(bill.getType()==0){
                    //转发单
                    //从C减料 反方向
                    subWarehouseMaterialDec(toSubWarehouse,m,true);
                    toQuantity=Arith.sub(toQuantity,m.getQuantityInc());
                    //toQuantity-=m.getQuantityInc();
                }else if(bill.getType()==1){
                    //采购单
                    //从C减料 反方向
                    warehouseMaterialDec(warehouse,m,true,updateTotal);
                    quantity=Arith.sub(quantity,m.getQuantityInc());
                    //quantity-=m.getQuantityInc();
                }else if(bill.getType()==2){
                    //外发单
                    //从C减料 反方向
                    subWarehouseMaterialDec(toSubWarehouse,m,true);
                    toQuantity=Arith.sub(toQuantity,m.getQuantityInc());
                    //toQuantity-=m.getQuantityInc();
                }else if(bill.getType()==3){
                    //回收单
                    //从C减料 反方向
                    warehouseMaterialDec(warehouse,m,true,updateTotal);
                    quantity=Arith.sub(quantity,m.getQuantityInc());
                    //quantity-=m.getQuantityInc();
                }

            }
        }


        if(quantity!=0){
            warehouse.setQuantity(Arith.add(warehouse.getQuantity(),quantity));
            if(updateTotal) {
                warehouse.setTotalQuantity(Arith.add(warehouse.getTotalQuantity() , quantity));
            }
            warehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*warehouse.getQuantity()/warehouse.getTotalQuantity()));
            warehouseDao.save(warehouse);
            saveWarehouseSnapshoot(warehouse);
        }

        if(fromQuantity!=0){
            fromSubWarehouse.setQuantity(Arith.add(fromSubWarehouse.getQuantity(),fromQuantity));
            fromSubWarehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*fromSubWarehouse.getQuantity()/warehouse.getTotalQuantity()));
            subWarehouseDao.save(fromSubWarehouse);
            saveSubWarehouseSnapshoot(fromSubWarehouse);
        }

        if(toQuantity!=0){
            toSubWarehouse.setQuantity(Arith.add(toSubWarehouse.getQuantity(),toQuantity));
            toSubWarehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*toSubWarehouse.getQuantity()/warehouse.getTotalQuantity()));
            subWarehouseDao.save(toSubWarehouse);
            saveSubWarehouseSnapshoot(toSubWarehouse);
        }

    }


    private void updateWarehouseLockDone(MaterialBill bill){

        Warehouse warehouse=bill.getWarehouse();
        SubWarehouse fromSubWarehouse=subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getUser());
        SubWarehouse toSubWarehouse=bill.getToUser()==null?null:subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getToUser());

        boolean updateTotal=(bill.getType()==1);

        //处理仓库物料
        double quantity=0;
        double fromQuantity=0;
        double toQuantity=0;

        List<MaterialBillMaterial> billMaterials=materialBillMaterialDao.findAllByBill(bill);
        for (MaterialBillMaterial m:billMaterials){
            if(m.getQuantityInc()>0){
                //To入料
                if(bill.getType()==0){
                    //转发单
                    //To入料
                    subWarehouseMaterialAdd(toSubWarehouse,m,true);
                    toQuantity=Arith.add(toQuantity,m.getQuantityInc());
                    //toQuantity+=m.getQuantityInc();
                }else if(bill.getType()==1){
                    //采购单
                    //To入料
                    warehouseMaterialAdd(warehouse,m,true,updateTotal);
                    quantity=Arith.add(quantity,m.getQuantityInc());
                    //quantity+=m.getQuantityInc();
                }else if(bill.getType()==2){
                    //外发单
                    //To入料
                    subWarehouseMaterialAdd(toSubWarehouse,m,true);
                    toQuantity=Arith.add(toQuantity,m.getQuantityInc());
                    //toQuantity+=m.getQuantityInc();
                }else if(bill.getType()==3){
                    //回收单
                    //To入料
                    warehouseMaterialAdd(warehouse,m,true,updateTotal);
                    quantity=Arith.add(quantity,m.getQuantityInc());
                    //quantity+=m.getQuantityInc();
                }

            }else if(m.getQuantityInc()<0){

                //From入料
                if(bill.getType()==0){
                    //转发单
                    //From入料
                    subWarehouseMaterialDec(fromSubWarehouse,m,true);
                    fromQuantity=Arith.sub(fromQuantity,m.getQuantityInc());
                    //fromQuantity-=m.getQuantityInc();
                }else if(bill.getType()==1){
                    //采购单
                    //From入料
                }else if(bill.getType()==2){
                    //外发单
                    //From入料
                    warehouseMaterialDec(warehouse,m,true,updateTotal);
                    quantity=Arith.sub(quantity,m.getQuantityInc());
                    //quantity-=m.getQuantityInc();
                }else if(bill.getType()==3){
                    //回收单
                    //From入料
                    subWarehouseMaterialDec(fromSubWarehouse,m,true);
                    fromQuantity=Arith.sub(fromQuantity,m.getQuantityInc());
                    //fromQuantity-=m.getQuantityInc();
                }

            }
        }


        if(quantity!=0){
            warehouse.setQuantity(Arith.add(warehouse.getQuantity(),quantity));
            if(updateTotal) {
                warehouse.setTotalQuantity(Arith.add(warehouse.getTotalQuantity() , quantity));
            }
            warehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*warehouse.getQuantity()/warehouse.getTotalQuantity()));
            warehouseDao.save(warehouse);
            saveWarehouseSnapshoot(warehouse);
        }

        if(fromQuantity!=0){
            fromSubWarehouse.setQuantity(Arith.add(fromSubWarehouse.getQuantity(),fromQuantity));
            fromSubWarehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*fromSubWarehouse.getQuantity()/warehouse.getTotalQuantity()));
            subWarehouseDao.save(fromSubWarehouse);
            saveSubWarehouseSnapshoot(fromSubWarehouse);
        }

        if(toQuantity!=0){
            toSubWarehouse.setQuantity(Arith.add(toSubWarehouse.getQuantity(),toQuantity));
            toSubWarehouse.setQuantityPer(warehouse.getTotalQuantity()==0?0:(100*toSubWarehouse.getQuantity()/warehouse.getTotalQuantity()));
            subWarehouseDao.save(toSubWarehouse);
            saveSubWarehouseSnapshoot(toSubWarehouse);
        }

    }


    /*

    //A--出方 C--入方 B--物料单
    //从A方出库
    private void updateWarehouse_A2B(MaterialBill bill){
        if(bill.getType()==0){
            //转发单
            //从A减料
            SubWarehouse A = subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getUser());
            subWarehouseMaterialDecAllByMaterialBill(A, bill);
        }else if(bill.getType()==1){
            //采购单
            // 不用出库
        }else if(bill.getType()==2){
            //外发单
            //从主仓库减料
            warehouseMaterialDecAllByMaterialBill(bill);
        }else if(bill.getType()==3){
            //回收单
            //从A减料
            SubWarehouse A = subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getUser());
            subWarehouseMaterialDecAllByMaterialBill(A, bill);
        }
    }

    //A--出方 C--入方 B--物料单
    //从A方出库 反方向
    private void updateWarehouse_A2B_back(MaterialBill bill){
        boolean check=true;
        if(bill.getType()==0){
            //转发单
            //A+料
            SubWarehouse A = subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getUser());
            subWarehouseMaterialAddAllByMaterialBill(A, bill);
        }else if(bill.getType()==1){
            //采购单
            // 不用出库
        }else if(bill.getType()==2){
            //外发单
            //主仓库+料
            warehouseMaterialAddAllByMaterialBill(bill);
        }else if(bill.getType()==3){
            //回收单
            //A+料
            SubWarehouse A = subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getUser());
            subWarehouseMaterialAddAllByMaterialBill(A, bill);
        }
    }



    //A--出方 C--入方 B--物料单
    //C方入库
    private void updateWarehouse_B2C(MaterialBill bill){
        if(bill.getType()==0){
            //转发单
            //C加料
            SubWarehouse C = subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getToUser());
            subWarehouseMaterialAddAllByMaterialBill(C, bill);
        }else if(bill.getType()==1){
            //采购单
            //主仓库加料
            warehouseMaterialAddAllByMaterialBill(bill);
        }else if(bill.getType()==2){
            //外发单
            //C加料
            SubWarehouse C = subWarehouseDao.findByWarehouseAndUser(bill.getWarehouse(), bill.getToUser());
            subWarehouseMaterialAddAllByMaterialBill(C, bill);
        }else if(bill.getType()==3){
            //回收单
            //主仓库加料
            warehouseMaterialAddAllByMaterialBill(bill);
        }
    }

     */

    //发新物料单更新库存
    private void updateWarehouseByNewMaterialBill(MaterialBill bill){
        updateWarehouseLock(bill);
        if(bill.getStatus()==2){
            //已经确认 直接入库
            updateWarehouseLockDone(bill);
        }
    }

    //确认接收物料单更新库存
    private void updateWarehouseByRecvMaterialBill(MaterialBill bill){
        //直接入库
        updateWarehouseLockDone(bill);
    }


    //修改物料单后更新库存
    private void updateWarehouseByUpdateMaterialBill(MaterialBill bill){
        updateWarehouseLock(bill);
        if(bill.getStatus()==2){
            //已经确认 直接入库
            updateWarehouseLockDone(bill);
        }
    }

    //删除物料单更新库存
    private void updateWarehouseByCancelMaterialBill(MaterialBill bill){
        //回复库存
        updateWarehouseLockBack(bill);

    }



    @Override
    public Warehouse saveWarehouse(WarehouseParamsDTO params) {

        Warehouse warehouse=params.getId()==null?null:warehouseDao.getOne(params.getId());

        if(warehouse==null){
            warehouse=new Warehouse();
            warehouse.setUser(userDao.getOne(params.getUserId()));
            warehouse.setManager(userDao.getOne(params.getManagerId()==null?params.getUserId():params.getManagerId()));
            warehouse.setCreateTime(new Date());
            warehouse.setUpdateTime(warehouse.getCreateTime());
        }else{
            warehouse.setUpdateTime(new Date());
        }

        BeanUtils.copyProperties(params,warehouse);

        warehouse=warehouseDao.save(warehouse);

        if(warehouse.getWarehouseNo()==null){
            warehouse.setWarehouseNo("ML"+warehouse.getId());
            warehouseDao.save(warehouse);
        }

        //创建子仓库
        saveSubWarehouse(warehouse,warehouse.getManager());

        return warehouse;
    }


    @Override
    public WarehouseMaterial setWarehouseMaterialWarningQuantity(Long id, double warningQuantity) {
        WarehouseMaterial material=warehouseMaterialDao.getOne(id);
        material.setWarningQuantity(warningQuantity);
        material=warehouseMaterialDao.save(material);
        return material;
    }

    @Override
    public List<Warehouse> getWarehouseListByUserId(Long userId, Integer type) {
        if(type!=null&&type==1){
            return subWarehouseDao.findWarehouseAllByUser(userDao.getOne(userId));
        }
        return warehouseDao.findAllByUser(userDao.getOne(userId));
    }

    @Override
    public List<WarehouseVO> getWarehouseVOListByUserId(Long userId, Integer type) {
        if(type!=null&&type==1){
            QSubWarehouse es=QSubWarehouse.subWarehouse;
            List<Long> ids=QF.select(es.warehouse.id).from(es).where(es.user.id.eq(userId)).fetch();
            QWarehouse e=QWarehouse.warehouse;
            return toList(QF.select(WarehouseVO.select(e)).from(e).where(e.id.in(ids).and(e.status.eq(0))).orderBy(e.updateTime.desc()),WarehouseVO.class,e);
        }
        QWarehouse e=QWarehouse.warehouse;
        return toList(QF.select(WarehouseVO.select(e)).from(e).where(e.user.id.eq(userId)).orderBy(e.updateTime.desc()),WarehouseVO.class,e);
    }

    @Override
    public WarehouseVO getWarehouseVOById(Long id) {

        QWarehouse e=QWarehouse.warehouse;

        Tuple tuple=QF.select(WarehouseVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();

        WarehouseVO vo=new WarehouseVO().fromTuple(tuple,e);

        vo.setMaterials(getWarehouseMaterialVOListByWarehouseId(id));

        vo.setSubs(getSubWarehouseVOListByWarehouseVO(vo));

        return vo;
    }


    @Override
    public List<WarehouseMaterialVO> getWarehouseMaterialVOListByWarehouseId(Long warehouseId) {
        QWarehouseMaterial e=QWarehouseMaterial.warehouseMaterial;
        List<WarehouseMaterialVO> list= toList(QF.select(WarehouseMaterialVO.select(e)).from(e).where(e.warehouse.id.eq(warehouseId))
                , WarehouseMaterialVO.class,e);


        Set<Long> materialIds=new HashSet<>();
        for (WarehouseMaterialVO vo : list){
            materialIds.add(vo.getMaterialId());
        }

        if(materialIds.size()>0){
           List<MaterialEntityVO> materialEntityVOS=materialDomain.getMaterialEntityVOListByIdIn(materialIds);
           Map<Long, MaterialEntityVO> map=new HashMap<>();
           for (MaterialEntityVO entityVO: materialEntityVOS){
               map.put(entityVO.getId(),entityVO);
           }
            for (WarehouseMaterialVO vo : list){
                vo.setMaterial(map.get(vo.getMaterialId()));
            }
        }

        return list;
    }


    @Override
    public MaterialBill saveMaterialBill(MaterialBillParamsDTO params) {
        //处理物料单会更新仓库、锁住仓库
        synchronized (this) {
            MaterialBill bill = new MaterialBill();
            bill.setWarehouse(warehouseDao.getOne(params.getWarehouseId()));
            bill.setUser(userDao.getOne(params.getUserId()));
            bill.setToUser(params.getToUserId() == null ? null : userDao.getOne(params.getToUserId()));
            bill.setCreateTime(new Date());
            bill.setUpdateTime(bill.getCreateTime());

            BeanUtils.copyProperties(params, bill);

            //物料单类型 0--成员转发单 1--采购单 2--外发单 3--回收单
            //物料单状态 0--新创建未选择接收者 1--已发出、等待确认 2--对方已经确认 3--已修改、已确认的可修改 4--已取消
            if (bill.getType() == 0) {
            } else if (bill.getType() == 1) {
                if(!bill.getUser().equals(bill.getWarehouse().getManager())){
                    throw new BusinessException("你不是管理员不能发采购单");
                }
                //采购单
                bill.setToUser(bill.getWarehouse().getManager());
            } else if (bill.getType() == 2) {
                if(!bill.getUser().equals(bill.getWarehouse().getManager())){
                    throw new BusinessException("你不是管理员不能外发单");
                }
            } else if (bill.getType() == 3) {
                //回收单
                bill.setToUser(bill.getWarehouse().getManager());
            }

            if (bill.getToUser() != null) {
                bill.setStatus(1);
            }

            //计算金额
            double amount = 0;
            double quantity=0;
            for (MaterialBillMaterialParamsDTO m : params.getMaterials()) {
                if(m.getQuantity()!=0){
                    throw new BusinessException("只需传quantityInc");
                }
                if(m.getQuantityInc()<0){
                    throw new BusinessException("quantityInc不能小于0");
                }
                amount += m.getAmount();
                quantity+=m.getQuantityInc();
            }
            bill.setAmount(amount);
            bill.setQuantity(quantity);


            bill = materialBillDao.save(bill);

            //生成QR码
            if (bill.getQrcode() == null) {
                //pages/materials/bill/detail/main?id=(物料单id)
                String q=baseUrl+"/m/materialsbill?id="+bill.getId().toString();
                bill.setQrcode(qrcodeService.generateQrCode(new GenerateQrCodeDTO(q, 500, 500)));
                materialBillDao.save(bill);
            }

            //更新物料价格
            updateMaterialEntityPrice(params);

            //物料列表
            List<MaterialBillMaterial> materials = new ArrayList<>();
            for (MaterialBillMaterialParamsDTO m : params.getMaterials()) {

                if(m.getQuantityInc()==0)
                    continue;
                MaterialBillMaterial material = new MaterialBillMaterial();
                material.setBill(bill);
                material.setMaterial(materialEntityDao.getOne(m.getMaterialId()));
                BeanUtils.copyProperties(m, material);
                materials.add(material);

            }
            materialBillMaterialDao.saveAll(materials);


            //更新仓库
            updateWarehouseByNewMaterialBill(bill);

            //保存记录
            saveMaterialBillRecordByNew(bill,materials);


            if(bill.getUser().equals(bill.getToUser())) {
                //如果是自己，直接确认
                revcMaterialBill(bill.getId(),bill.getToUser().getId());
            }


            //开启会话
            startSessionByMaterialBill(bill);

            //设置仓库更新时间
            Warehouse warehouse=bill.getWarehouse();

            //采购单更新采购成本
            if(bill.getType()==1){
                if(params.getTotalAmountInc()!=0){
                    warehouse.setTotalAmount(Arith.add(warehouse.getTotalAmount(),params.getTotalAmountInc()));
                }
            }

            warehouse.setQuantityUpdateTime(new Date());
            warehouseDao.save(warehouse);

            return bill;
        }
    }

    //更新物料价格
    private void updateMaterialEntityPrice(MaterialBillParamsDTO params){
        List<Long> updateMaterials=new ArrayList<>();
        for (MaterialBillMaterialParamsDTO m : params.getMaterials()) {
            if(m.getPrice()!=null&&m.getPrice()>0){
                //有修改单价
                MaterialEntity entity=materialEntityDao.getOne(m.getMaterialId());
                if(entity.getPrice()!=m.getPrice()) {
                    entity.setPrice(m.getPrice());
                    materialEntityDao.save(entity);
                    if(!updateMaterials.contains(entity.getMaterial().getId())) {
                        updateMaterials.add(entity.getMaterial().getId());
                    }
                }
            }
        }
        if(updateMaterials.size()>0){
            materialDomain.updateMaterialEntitys(updateMaterials);
        }
    }

    @Override
    public MaterialBill revcMaterialBill(Long billId, Long userId) {
        synchronized (this) {

            MaterialBill bill = materialBillDao.getOne(billId);

            User toUser=userDao.getOne(userId);

            if(bill.getStatus()==2){
                //已确认的单不能接收
                throw new BusinessException("你不能接收该物料单");
            }

            if(bill.getToUser()!=null&&!bill.getToUser().equals(toUser)){
                //如果已经设置了接收者，接收者必须一致
                throw new BusinessException("你不能接收该物料单");
            }

            if(bill.getToUser()==null){
                //设置接收者
               bill= setMaterialBillToUser(billId,userId);
            }

            //更新库存
            updateWarehouseByRecvMaterialBill(bill);


            //修改的物料单要把变化量加到数量上
            List<MaterialBillMaterial> materials=materialBillMaterialDao.findAllByBill(bill);
            for(MaterialBillMaterial m:materials){
                if(m.getQuantityInc()!=0){
                    m.setQuantity(Arith.add(m.getQuantity(),m.getQuantityInc()));
                    m.setQuantityInc(0);
                    materialBillMaterialDao.save(m);
                }
            }

            //设置状态
            bill.setStatus(2);
            bill.setUpdateTime(new Date());
            bill=materialBillDao.save(bill);

            //结束会话
            closeSessionByMaterialBill(bill);

            //设置仓库更新时间
            Warehouse warehouse=bill.getWarehouse();
            warehouse.setQuantityUpdateTime(new Date());
            warehouseDao.save(warehouse);

            return bill;
        }
    }

    //开启会话
    private void startSessionByMaterialBill(MaterialBill bill){
        if(bill.getToUser()==null)//没接收者不处理
            return;

        Warehouse warehouse=bill.getWarehouse();
        User toUser=bill.getToUser();

        //成员子仓库
        SubWarehouse subWarehouse=saveSubWarehouse(warehouse,toUser);


        if(bill.getType()==1)//采购单不处理
            return;
        if(bill.getStatus()==2)
            return;//已确认的不处理

        if(bill.getUser().equals(bill.getToUser())){
            if(subWarehouse.getMemberType()!=1||bill.getType()==0)
                throw new BusinessException("发送者和接收者不能是同一人");
        }

        //创建成员之间会话
        startSubWarehouseSessionByMaterialBill(bill);

    }

    private SubWarehouse saveSubWarehouse(Warehouse warehouse, User toUser){
        //成员子仓库
        SubWarehouse subWarehouse=subWarehouseDao.findByWarehouseAndUser(warehouse,toUser);

        if(subWarehouse==null){
            //创建子仓库
            subWarehouse=new SubWarehouse();
            subWarehouse.setWarehouse(warehouse);
            subWarehouse.setUser(toUser);
            subWarehouse.setCreateTime(new Date());
            subWarehouse.setUpdateTime(new Date());
            //成员类型 0--普通成员 1--管理员
            subWarehouse.setMemberType(toUser.equals(warehouse.getManager())?1:0);


            warehouse.setMembers(warehouse.getMembers()+1);

            List<String> ids=new ArrayList<>();
            if(warehouse.getMemberIds()!=null){
                ids.addAll(Arrays.asList(warehouse.getMemberIds().split(",")));
            }
            ids.add(toUser.getId().toString());
            warehouse.setMemberIds(ArrayUtil.listToString(ids));
            if(warehouse.getMemberIds().length()>500){
                log.error("Warehouse.memberIds:超出长度了");
            }


            List<String> avatars=new ArrayList<>();
            if(warehouse.getMemberAvatars()!=null){
                avatars.addAll(Arrays.asList(warehouse.getMemberAvatars().split(",")));
            }
            avatars.add(toUser.getAvatar()==null?"":toUser.getAvatar());
            String str=ArrayUtil.listToString(avatars);
            if(str.length()<2000) {
                warehouse.setMemberAvatars(str);
            }else{
                log.error("Warehouse.memberAvatars:超出长度了");
            }

            warehouseDao.save(warehouse);
            subWarehouseDao.save(subWarehouse);
        }
        return subWarehouse;
    }

    private void closeSessionByMaterialBill(MaterialBill bill){
        if(bill.getToUser()==null)//没接收者不处理
            return;
        if(bill.getType()==1)//采购单不处理
            return;

        //结束成员之间会话
        closeSubWarehouseSessionByMaterialBill(bill);

    }

    private SubWarehouseSession startSubWarehouseSessionByMaterialBill(MaterialBill bill){
        User from=bill.getUser().getId()<bill.getToUser().getId()?bill.getUser():bill.getToUser();
        User to=bill.getUser().getId()>bill.getToUser().getId()?bill.getUser():bill.getToUser();
        int sessionStatus=bill.getUser().equals(from)?1:2;
        SubWarehouseSession subWarehouseSession=subWarehouseSessionDao.findByWarehouseAndFromAndTo(bill.getWarehouse(),from,to);
        if(subWarehouseSession==null){
           subWarehouseSession=new SubWarehouseSession();
           subWarehouseSession.setWarehouse(bill.getWarehouse());
           subWarehouseSession.setFrom(from);
           subWarehouseSession.setTo(to);
           subWarehouseSession.setCreateTime(new Date());
        }
        if(subWarehouseSession.getSessionStatus()>0){
            throw new BusinessException("你有待处理的物料单没完成，请先处理");
        }
        subWarehouseSession.setUpdateTime(new Date());
        subWarehouseSession.setBillId(bill.getId());
        subWarehouseSession.setSessionStatus(sessionStatus);
        subWarehouseSession=subWarehouseSessionDao.save(subWarehouseSession);
        return subWarehouseSession;
    }

    private SubWarehouseSession closeSubWarehouseSessionByMaterialBill(MaterialBill bill){
        if(bill.getToUser()==null)
            return null;
        User from=bill.getUser().getId()<bill.getToUser().getId()?bill.getUser():bill.getToUser();
        User to=bill.getUser().getId()>bill.getToUser().getId()?bill.getUser():bill.getToUser();

        SubWarehouseSession subWarehouseSession=subWarehouseSessionDao.findByWarehouseAndFromAndTo(bill.getWarehouse(),from,to);
        if(subWarehouseSession==null)
            return null;
        subWarehouseSession.setUpdateTime(new Date());
        subWarehouseSession.setSessionStatus(0);
        subWarehouseSession=subWarehouseSessionDao.save(subWarehouseSession);

        return subWarehouseSession;
    }




    @Override
    public MaterialBill updateMaterialBill(MaterialBillUpdateDTO params) {
        synchronized (this) {

            MaterialBill bill=materialBillDao.getOne(params.getId());

            if(bill.getStatus()!=2){
                throw new BusinessException("只有已确认的订单才可修改");
            }

            double amount = 0;
            double quantity=0;
            for (MaterialBillMaterialUpdateDTO m: params.getMaterials()){
                amount += m.getAmount();
                //数量要加上变化量
                quantity+=m.getQuantity()+m.getQuantityInc();
                //更新物料
                MaterialBillMaterial material = materialBillMaterialDao.getOne(m.getId());
                //价格有可能变动
                material.setAmount(m.getAmount());
                material.setQuantityInc(m.getQuantityInc());
                materialBillMaterialDao.save(material);
            }


            bill.setAmount(amount);
            bill.setQuantity(quantity);

            //保存记录
            MaterialBillRecord record= saveMaterialBillRecordByUpdate(bill,params.getMaterials());
            bill.setRecordId(record.getId());

            bill.setStatus(3);
            bill=materialBillDao.save(bill);

            //更新仓库
            updateWarehouseByUpdateMaterialBill(bill);


            if(bill.getUser().equals(bill.getToUser())) {
                //如果是自己，直接确认
                revcMaterialBill(bill.getId(),bill.getToUser().getId());
            }

            //重新开始
            startSessionByMaterialBill(bill);

            //设置仓库更新时间
            Warehouse warehouse=bill.getWarehouse();

            //采购单更新采购成本
            if(bill.getType()==1){
                if(params.getTotalAmountInc()!=0){
                    warehouse.setTotalAmount(Arith.add(warehouse.getTotalAmount(),params.getTotalAmountInc()));
                }
            }

            warehouse.setQuantityUpdateTime(new Date());
            warehouseDao.save(warehouse);

            return bill;
        }
    }



    @Override
    public MaterialBill setMaterialBillToUser(Long billId, Long userId) {
       synchronized (this){

           MaterialBill bill=materialBillDao.getOne(billId);

           if(bill.getStatus()>1){
               throw new BusinessException("只有未确定的物料单才能更换接收者");
           }

           if(bill.getType()==1||bill.getType()==3){
               throw new BusinessException("不能更换接收者");
           }

           if(bill.getType()==0&&bill.getUser().getId().equals(userId)){
               throw new BusinessException("发送者和接收者不能相同");
           }

           //先撤消原来的接收者
           closeSessionByMaterialBill(bill);

           bill.setToUser(userDao.getOne(userId));
           bill.setStatus(1);
           materialBillDao.save(bill);

           if(bill.getUser().equals(bill.getToUser())) {
               //如果是自己，直接确认
               revcMaterialBill(bill.getId(),bill.getToUser().getId());
           }

           startSessionByMaterialBill(bill);

           return bill;
       }

    }

    @Override
    public void cancelMaterialBillById(Long id) {
        synchronized (this) {

            MaterialBill bill = materialBillDao.getOne(id);

            if(bill.getStatus()!=3){
                throw new BusinessException("不能取消");
            }

            //处理物料单会更新仓库、锁住仓库
            //处理主仓库物料
            updateWarehouseByCancelMaterialBill(bill);

            closeSessionByMaterialBill(bill);

            if(bill.getRecordId()!=null){
                materialBillRecordDao.deleteById(bill.getRecordId());
                bill.setRecordId(null);
            }

            List<MaterialBillMaterial> materials=materialBillMaterialDao.findAllByBill(bill);
            for(MaterialBillMaterial m:materials){
                if(m.getQuantityInc()!=0){
                    m.setQuantityInc(0);
                    materialBillMaterialDao.save(m);
                }
            }

            bill.setStatus(2);
            materialBillDao.save(bill);

            //设置仓库更新时间
            Warehouse warehouse=bill.getWarehouse();
            warehouse.setQuantityUpdateTime(new Date());
            warehouseDao.save(warehouse);


        }

    }

    @Override
    public void deleteMaterialBillById(Long id) {
        synchronized (this) {

            MaterialBill bill = materialBillDao.getOne(id);

            if(bill.getStatus()==3){
                this.cancelMaterialBillById(id);
                return;
            }

            if(bill.getStatus()>1){
                throw new BusinessException("不能删除");
            }

            //处理物料单会更新仓库、锁住仓库
            //处理主仓库物料
            updateWarehouseByCancelMaterialBill(bill);

            closeSessionByMaterialBill(bill);

            materialBillRecordDao.deleteAllByBill(bill);
            materialBillMaterialDao.deleteAllByBill(bill);
            materialBillDao.delete(bill);

            //设置仓库更新时间
            Warehouse warehouse=bill.getWarehouse();
            warehouse.setQuantityUpdateTime(new Date());
            warehouseDao.save(warehouse);


        }

    }


    private MaterialBillRecord saveMaterialBillRecordByUpdate(MaterialBill bill, List<MaterialBillMaterialUpdateDTO> materials){
        MaterialBillRecord record=new MaterialBillRecord();
        record.setBill(bill);
        record.setCreateTime(new Date());

        List<MaterialBillRecordMaterialVO> materialBillRecordMaterialVOS=new ArrayList<>();
        for (MaterialBillMaterialUpdateDTO m: materials){
            if(m.getQuantityInc()!=0){
                MaterialBillMaterial material=materialBillMaterialDao.getOne(m.getId());
                MaterialBillRecordMaterialVO vo=new MaterialBillRecordMaterialVO();
                vo.setId(material.getId());
                vo.setName(material.getMaterial().getColor()+material.getMaterial().getMaterial().getName());
                vo.setUnit(material.getUnit());
                vo.setQuantityInc(m.getQuantityInc());
                materialBillRecordMaterialVOS.add(vo);
            }
        }

        record.setMaterials(JsonUtil.toJson(materialBillRecordMaterialVOS));

        record=materialBillRecordDao.save(record);

        return record;
    }

    private void saveMaterialBillRecordByNew(MaterialBill bill, List<MaterialBillMaterial> materials){
        MaterialBillRecord record=new MaterialBillRecord();
        record.setBill(bill);
        record.setCreateTime(new Date());

        List<MaterialBillRecordMaterialVO> materialBillRecordMaterialVOS=new ArrayList<>();
        for (MaterialBillMaterial m: materials){
            if(m.getQuantityInc()>0){
                MaterialBillRecordMaterialVO vo=new MaterialBillRecordMaterialVO();
                vo.setId(m.getId());
                vo.setName(m.getMaterial().getColor()+m.getMaterial().getMaterial().getName());
                vo.setUnit(m.getUnit());
                vo.setQuantityInc(m.getQuantityInc());
                materialBillRecordMaterialVOS.add(vo);
            }
        }

        record.setMaterials(JsonUtil.toJson(materialBillRecordMaterialVOS));

        record=materialBillRecordDao.save(record);
    }

    @Override
    public List<MaterialBillRecord> getMaterialBillRecordListByBillId(Long billId) {
        List<MaterialBillRecord> list=materialBillRecordDao.findAllByBillOrderByIdDesc(materialBillDao.getOne(billId));
        return list;
    }



    @Override
    public List<MaterialBillMaterialVO> getMaterialBillMaterialVOListByBillId(Long billId) {
        QMaterialBillMaterial e=QMaterialBillMaterial.materialBillMaterial;
        return toList(QF.select(MaterialBillMaterialVO.select(e)).from(e).where(e.bill.id.eq(billId))
                , MaterialBillMaterialVO.class,e);
    }

    @Override
    public MaterialBillVO getMaterialBillVOById(Long id) {
        QMaterialBill e=QMaterialBill.materialBill;
        Tuple tuple=QF.select(MaterialBillVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();
        MaterialBillVO vo=new MaterialBillVO().fromTuple(tuple,e);
        if(vo.getToUserId()!=null){
            ContactUserVO userVO=userService.getContactUserVOListByIdIn(ArrayUtil.stringToLongList(vo.getToUserId().toString())).get(0);
            vo.setToUser(userVO);
        }
        vo.setMaterials(getMaterialBillMaterialVOListByBillId(id));
        return vo;
    }

    @Override
    public Page<MaterialBillVO> getMaterialBillVOPageByWarehouseId(Long warehouseId, Integer type, String keyword, Pageable pageable) {
        QMaterialBill e=QMaterialBill.materialBill;

        Predicate p=null;

        if(keyword!=null&&keyword.length()>0){
            QUserDetails u=QUserDetails.userDetails;
            List<Long> ids=QF.select(u.user.id).from(u).where(u.realName.like("%"+keyword+"%")).fetch();
            p=e.user.id.in(ids).or(e.toUser.id.in(ids));
        }

        if(type!=null){
            p=e.type.eq(type).and(p);
        }

        p = e.warehouse.id.eq(warehouseId).and(p);

        Page<MaterialBillVO> page= toPage(QF.select(MaterialBillVO.select(e)).from(e).where(p),pageable, MaterialBillVO.class,e);


        List<Long> toIds=new ArrayList<>();
        for (MaterialBillVO vo :page.getContent()){
            if(vo.getToUserId()!=null)
                toIds.add(vo.getToUserId());
        }
        if(toIds.size()>0) {
            List<ContactUserVO> tos = userService.getContactUserVOListByIdIn(toIds);
            Map<Long,ContactUserVO> map=new HashMap<>();
            for (ContactUserVO vo :tos){
                map.put(vo.getId(),vo);
            }
            for (MaterialBillVO vo :page.getContent()){
                if(vo.getToUserId()!=null)
                    vo.setToUser(map.get(vo.getToUserId()));
            }
        }

        return page;
    }

    @Override
    public Page<MaterialBillVO> getMaterialBillVOPageBySubWarehouseId(Long subWarehouseId, Integer type, String keyword, Pageable pageable){

        SubWarehouse subWarehouse=subWarehouseDao.getOne(subWarehouseId);

        QMaterialBill e=QMaterialBill.materialBill;

        Predicate p=null;

        if(keyword!=null&&keyword.length()>0){
            QUserDetails u=QUserDetails.userDetails;
            List<Long> ids=QF.select(u.user.id).from(u).where(u.realName.like("%"+keyword+"%")).fetch();
            p=e.user.id.in(ids).or(e.toUser.id.in(ids));
        }

        Long userId=subWarehouse.getUser().getId();

        if(type==null){
            p=e.user.id.eq(userId).or(e.toUser.id.eq(userId)).and(p);
            if(subWarehouse.getMemberType()==1){
                p=e.type.ne(1).and(p);
            }
        }else if(type==0){
            p=e.user.id.eq(subWarehouse.getUser().getId()).and(p);
            if(subWarehouse.getMemberType()==1){
                p=e.type.in(0,3).and(p);
            }
        }else {
            p=e.toUser.id.eq(subWarehouse.getUser().getId()).and(p);
            if(subWarehouse.getMemberType()==1){
                p=e.type.in(0,2).and(p);
            }
        }

        p = e.warehouse.id.eq(subWarehouse.getWarehouse().getId()).and(p);

        Page<MaterialBillVO> page= toPage(QF.select(MaterialBillVO.select(e)).from(e).where(p),pageable, MaterialBillVO.class,e);


        List<Long> toIds=new ArrayList<>();
        for (MaterialBillVO vo :page.getContent()){
            if(vo.getToUserId()!=null)
                toIds.add(vo.getToUserId());
        }
        if(toIds.size()>0) {
            List<ContactUserVO> tos = userService.getContactUserVOListByIdIn(toIds);
            Map<Long,ContactUserVO> map=new HashMap<>();
            for (ContactUserVO vo :tos){
                map.put(vo.getId(),vo);
            }
            for (MaterialBillVO vo :page.getContent()){
                if(vo.getToUserId()!=null)
                    vo.setToUser(map.get(vo.getToUserId()));
            }
        }

        return page;
    }

    @Override
    public SubWarehouseVO getSubWarehouseVOByUserId(Long warehouseId, Long userId) {
        QSubWarehouse e=QSubWarehouse.subWarehouse;
        Tuple tuple=QF.select(SubWarehouseVO.select(e)).from(e).where(e.user.id.eq(userId).and(e.warehouse.id.eq(warehouseId))).fetchOne();
        if(tuple==null){
            throw new BusinessException("不是成员");
        }
        SubWarehouseVO vo=new SubWarehouseVO().fromTuple(tuple,e);

        vo.setWarehouse(getWarehouseVOForSub(vo.getWarehouseId()));

        vo.setMaterials(getSubWarehouseMaterialVOListBySubWarehouseId(vo.getId()));

        vo.setSubs(getSubWarehouseVOListBySubWarehouseVO(vo));

        return vo;
    }

    @Override
    public SubWarehouseVO getSubWarehouseVOById(Long id) {
        QSubWarehouse e=QSubWarehouse.subWarehouse;
        Tuple tuple=QF.select(SubWarehouseVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();
        if(tuple==null){
            throw new BusinessException("子仓库不存在");
        }
        SubWarehouseVO vo=new SubWarehouseVO().fromTuple(tuple,e);


        vo.setWarehouse(getWarehouseVOForSub(vo.getWarehouseId()));

        vo.setMaterials(getSubWarehouseMaterialVOListBySubWarehouseId(vo.getId()));

        vo.setSubs(getSubWarehouseVOListBySubWarehouseVO(vo));

        return vo;
    }

    private WarehouseVO getWarehouseVOForSub(Long id){
        QWarehouse e=QWarehouse.warehouse;
        Tuple tuple=QF.select(WarehouseVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();
        WarehouseVO vo=new WarehouseVO().fromTuple(tuple,e);
        return vo;
    }

    @Override
    public List<SubWarehouseMaterialVO> getSubWarehouseMaterialVOListBySubWarehouseId(Long subWarehouseId) {
        QSubWarehouseMaterial e=QSubWarehouseMaterial.subWarehouseMaterial;
        return toList(QF.select(SubWarehouseMaterialVO.select(e)).from(e).where(e.subWarehouse.id.eq(subWarehouseId)), SubWarehouseMaterialVO.class,e);
    }

    @Override
    public List<SubWarehouseMaterialVO> getSubWarehouseMaterialVOListByUserId(Long warehouseId, Long userId) {
        SubWarehouse subWarehouse=subWarehouseDao.findByWarehouseAndUser(warehouseDao.getOne(warehouseId),userDao.getOne(userId));
        QSubWarehouseMaterial e=QSubWarehouseMaterial.subWarehouseMaterial;
        return toList(QF.select(SubWarehouseMaterialVO.select(e)).from(e).where(e.subWarehouse.id.eq(subWarehouse.getId())), SubWarehouseMaterialVO.class,e);
    }

    private List<SubWarehouseVO> getSubWarehouseVOListByWarehouseVO(WarehouseVO warehouseVO) {
        QSubWarehouse e=QSubWarehouse.subWarehouse;
        List<SubWarehouseVO> list= toList(QF.select(SubWarehouseVO.select(e)).from(e).where(e.warehouse.id.eq(warehouseVO.getId())).orderBy(e.updateTime.desc()), SubWarehouseVO.class,e);

        //获取会话状态
        QSubWarehouseSession q=QSubWarehouseSession.subWarehouseSession;
        Long userId=warehouseVO.getManagerId();
        List<SubWarehouseSession> sessions=QF.select(q).from(q).where(
                q.warehouse.id.eq(warehouseVO.getId()).and(q.from.id.eq(userId).or(q.to.id.eq(userId)))
        ).fetch();

        Map<Long, SubWarehouseSession> map=new HashMap<>();
        for (SubWarehouseSession session:sessions){
            if(session.getFrom().getId().equals(userId)){
                map.put(session.getTo().getId(),session);
            }else{
                map.put(session.getFrom().getId(),session);
            }
        }

        for (SubWarehouseVO vo:list){
            SubWarehouseSession session=map.get(vo.getUser().getId());
            if(session!=null&&session.getSessionStatus()>0){
                setSessionStatus(vo,session);
            }
        }


        return list;
    }

    private List<SubWarehouseVO> getSubWarehouseVOListBySubWarehouseVO(SubWarehouseVO subWarehouseVO) {

        //获取有关联的会话状态
        QSubWarehouseSession q=QSubWarehouseSession.subWarehouseSession;
        Long userId=subWarehouseVO.getUser().getId();
        List<SubWarehouseSession> sessions=QF.select(q).from(q).where(
                q.warehouse.id.eq(subWarehouseVO.getWarehouseId()).and(q.from.id.eq(userId).or(q.to.id.eq(userId)))
        ).fetch();

        //获取有关联的成员
        Set<Long> userIds=new HashSet<>();
        for (SubWarehouseSession session:sessions){
            userIds.add(session.getFrom().getId());
            userIds.add(session.getTo().getId());
        }
        userIds.remove(userId);


        QSubWarehouse e=QSubWarehouse.subWarehouse;
        List<SubWarehouseVO> list= toList(QF.select(SubWarehouseVO.select(e)).from(e).where(
                e.warehouse.id.eq(subWarehouseVO.getWarehouseId()).and(e.user.id.in(userIds))
        ).orderBy(e.updateTime.desc()), SubWarehouseVO.class,e);


        Map<Long, SubWarehouseSession> map=new HashMap<>();
        for (SubWarehouseSession session:sessions){
            if(session.getFrom().getId().equals(userId)){
                map.put(session.getTo().getId(),session);
            }else{
                map.put(session.getFrom().getId(),session);
            }
        }

        for (SubWarehouseVO vo:list){
            SubWarehouseSession session=map.get(vo.getUser().getId());
            if(session!=null&&session.getSessionStatus()>0){
                setSessionStatus(vo,session);
            }
        }

        return list;
    }

    private void setSessionStatus(SubWarehouseVO vo, SubWarehouseSession session){
        //仓库之间的会话状态 0--正常 1--收到物料单 2--发了物料单
        Long userId=vo.getUser().getId();
        vo.setBillId(session.getBillId());
        if(session.getSessionStatus()==1){
            //to 收到
            if(session.getTo().getId().equals(userId)){
                vo.setSessionStatus(1);
            }else{
                vo.setSessionStatus(2);
            }
        }else{
            // from 收到
            if(session.getFrom().getId().equals(userId)){
                vo.setSessionStatus(1);
            }else{
                vo.setSessionStatus(2);
            }
        }
    }

    @Override
    public Page<WarehouseSnapshoot> getWarehouseSnapshootPage(Long warehouseId, Long subWarehouseId, Pageable pageable) {
        if(subWarehouseId!=null)
            return warehouseSnapshootDao.findAllBySubWarehouseId(subWarehouseId,pageable);
        return warehouseSnapshootDao.findAllByWarehouseIdAndType(warehouseId,0,pageable);
    }


    /*
    private WarehouseSnapshoot getWarehouseSnapshootFirstByWarehouseId(Long warehouseId){
        QWarehouseSnapshoot e=QWarehouseSnapshoot.warehouseSnapshoot;
        WarehouseSnapshoot snapshoot=QF.select(e).from(e).where(e.warehouseId.eq(warehouseId).and(e.type.eq(0)))
                .orderBy(e.id.asc()).offset(0).limit(1).fetchOne();
        return snapshoot;
    }

    private WarehouseSnapshoot getWarehouseSnapshootLastByWarehouseId(Long warehouseId){
        QWarehouseSnapshoot e=QWarehouseSnapshoot.warehouseSnapshoot;
        WarehouseSnapshoot snapshoot=QF.select(e).from(e).where(e.warehouseId.eq(warehouseId).and(e.type.eq(0)))
                .orderBy(e.id.desc()).offset(0).limit(1).fetchOne();
        return snapshoot;
    }
    */


    private WarehouseSnapshoot getWarehouseSnapshootBeforeByWarehouseId(Long warehouseId,Date time){
        QWarehouseSnapshoot e=QWarehouseSnapshoot.warehouseSnapshoot;
        List<WarehouseSnapshoot> snapshoots=QF.select(e).from(e).where(e.warehouseId.eq(warehouseId).and(e.type.eq(0)).and(e.createTime.before(time)))
                .orderBy(e.id.desc()).offset(0).limit(1).fetch();
        return snapshoots.isEmpty()?null:snapshoots.get(0);
    }

    /*
    private WarehouseSnapshoot getWarehouseSnapshootFirstBySubWarehouseId(Long subWarehouseId){
        QWarehouseSnapshoot e=QWarehouseSnapshoot.warehouseSnapshoot;
        List<WarehouseSnapshoot> snapshoots=QF.select(e).from(e).where(e.subWarehouseId.eq(subWarehouseId))
                .orderBy(e.id.asc()).offset(0).limit(1).fetch();
        return snapshoots.isEmpty()?null:snapshoots.get(0);
    }

    private WarehouseSnapshoot getWarehouseSnapshootLastBySubWarehouseId(Long subWarehouseId){
        QWarehouseSnapshoot e=QWarehouseSnapshoot.warehouseSnapshoot;
        WarehouseSnapshoot snapshoot=QF.select(e).from(e).where(e.subWarehouseId.eq(subWarehouseId))
                .orderBy(e.id.desc()).offset(0).limit(1).fetchOne();
        return snapshoot;
    }
*/
    private WarehouseSnapshoot getWarehouseSnapshootBeforeBySubWarehouseId(Long subWarehouseId,Date time){
        QWarehouseSnapshoot e=QWarehouseSnapshoot.warehouseSnapshoot;
        List<WarehouseSnapshoot> snapshoots=QF.select(e).from(e).where(e.subWarehouseId.eq(subWarehouseId).and(e.createTime.before(time)))
                .orderBy(e.id.desc()).offset(0).limit(1).fetch();
        return snapshoots.isEmpty()?null:snapshoots.get(0);
    }

    private List<Date> getWarehouseSnapshootTimes(Warehouse warehouse){

        int c=7;

        List<Date> times=new ArrayList<>();

        Date startTime=warehouse.getCreateTime();
        startTime=DateUtil.zeroTime(startTime);
        startTime=DateUtil.addDays(startTime,1);
        Date endTime=warehouse.getQuantityUpdateTime()==null?new Date():warehouse.getQuantityUpdateTime();
        endTime=DateUtil.zeroTime(endTime);
        endTime=DateUtil.addDays(endTime,1);

        int days=getDayInterval(startTime,endTime);

        if(days<1){
            times.add(endTime);
            return times;
        }

        int d=(days-1)/c+1;


        Date time=endTime;
        times.add(time);
        int s=0;
        do {
            time=DateUtil.addDays(time,-d);
            times.add(0,time);
            s++;
        }while (time.getTime()>startTime.getTime()&&s<c);

        return times;
    }

    private int getDayInterval(Date time1,Date time2){
        long base = 24*3600*1000L;
        long day1=time1.getTime()/base;
        long day2=time2.getTime()/base;
        return (int)(day2-day1);
    }

    private void setWarehouseStatisVO(WarehouseStatisVO statisVO, Warehouse warehouse, List<Date> times){


        WarehouseStatisSubVO subVO=new WarehouseStatisSubVO();
        statisVO.getDatas().add(subVO);
        subVO.setUser("主仓库",warehouse.getId());


        WarehouseSnapshoot snapshoot=null;

        for (int i=times.size()-1;i>=0;i--){
            Date time=times.get(i);

            if(snapshoot==null||time.getTime()<snapshoot.getCreateTime().getTime()){
                snapshoot=getWarehouseSnapshootBeforeByWarehouseId(warehouse.getId(),time);
            }

            if(snapshoot!=null){
                subVO.getMaterialNum().add(0,snapshoot.getQuantity());
            }else{
                subVO.getMaterialNum().add(0,0D);
            }

        }


    }

    private void setSubWarehouseStatisVO(WarehouseStatisVO statisVO, SubWarehouse subWarehouse, List<Date> times){


        WarehouseStatisSubVO subVO=new WarehouseStatisSubVO();
        statisVO.getDatas().add(subVO);
        subVO.setUser(subWarehouse.getUser().getDetails().getRealName(),subWarehouse.getId());

        WarehouseSnapshoot snapshoot=null;

        for (int i=times.size()-1;i>=0;i--){
            Date time=times.get(i);

            if(snapshoot==null||time.getTime()<snapshoot.getCreateTime().getTime()){
                snapshoot=getWarehouseSnapshootBeforeBySubWarehouseId(subWarehouse.getId(),time);
            }

            if(snapshoot!=null){
                subVO.getMaterialNum().add(0,snapshoot.getQuantity());
            }else{
                subVO.getMaterialNum().add(0,0D);
            }

        }


    }

    @Override
    public WarehouseStatisVO getWarehouseStatisVOByWarehouseId(Long warehouseId) {


        Warehouse warehouse=warehouseDao.getOne(warehouseId);
        List<SubWarehouse> subWarehouses=subWarehouseDao.findAllByWarehouse(warehouse);


        List<Date> times=getWarehouseSnapshootTimes(warehouse);


        WarehouseStatisVO statisVO=new WarehouseStatisVO();

        for (Date time:times){
            statisVO.getDate().add(DateUtil.getDateShortString(DateUtil.addDays(time,-1)));
        }


        setWarehouseStatisVO(statisVO,warehouse,times);

        for (SubWarehouse subWarehouse: subWarehouses){
            setSubWarehouseStatisVO(statisVO,subWarehouse,times);
        }


        return statisVO;
    }






    private void saveWarehouseSnapshoot(Warehouse warehouse){
        try {

            synchronized (this){
                Date now=new Date();
                Date time = DateUtil.zeroTime(now);
                WarehouseSnapshoot snapshoot=warehouseSnapshootDao.findByTypeAndWarehouseIdAndCreateTimeAfter(0,warehouse.getId(),time);
                if(snapshoot==null){
                    snapshoot=new WarehouseSnapshoot();
                    snapshoot.setWarehouseId(warehouse.getId());
                    snapshoot.setUserId(warehouse.getUser().getId());
                    //snapshoot.setFullname(warehouse.getUser().getDetails().getRealName());
                    snapshoot.setType(0);
                }
                snapshoot.setCreateTime(now);
                snapshoot.setTotalQuantity(warehouse.getTotalQuantity());
                snapshoot.setQuantity(warehouse.getQuantity());
                snapshoot.setTotalAmount(warehouse.getTotalAmount());
                snapshoot.setAmount(warehouse.getAmount());
                snapshoot=warehouseSnapshootDao.save(snapshoot);
            }
        }catch (Exception e){
            log.error("统计数据出错了saveWarehouseSnapshoot-------",e);
        }
    }


    private void saveSubWarehouseSnapshoot(SubWarehouse subWarehouse){
        try {

            synchronized (this){
                Date now=new Date();
                Date time = DateUtil.zeroTime(now);
                WarehouseSnapshoot snapshoot=warehouseSnapshootDao.findBySubWarehouseIdAndCreateTimeAfter(subWarehouse.getId(),time);
                if(snapshoot==null){
                    snapshoot=new WarehouseSnapshoot();
                    snapshoot.setWarehouseId(subWarehouse.getWarehouse().getId());
                    snapshoot.setSubWarehouseId(subWarehouse.getId());
                    snapshoot.setUserId(subWarehouse.getUser().getId());
                    //snapshoot.setFullname(subWarehouse.getUser().getDetails().getRealName());
                    snapshoot.setType(1);
                }
                snapshoot.setCreateTime(now);
                snapshoot.setQuantity(subWarehouse.getQuantity());
                snapshoot.setAmount(subWarehouse.getAmount());
                snapshoot=warehouseSnapshootDao.save(snapshoot);
            }
        }catch (Exception e){
            log.error("统计数据出错了saveWarehouseSnapshoot-------",e);
        }
    }

    @Override
    public void setWarehouseProductionTemplate(Long warehouseId, Long templateId) {
        QWarehouse e =QWarehouse.warehouse;
        QF.update(e).set(e.productionTemplate,templateId).where(e.id.eq(warehouseId)).execute();
    }

    @Override
    public Long getWarehouseProductionTemplate(Long warehouseId) {
        QWarehouse e =QWarehouse.warehouse;
        List<Long> ids=QF.select(e.productionTemplate).from(e).where(e.id.eq(warehouseId)).fetch();
        return ids.size()>0?ids.get(0):null;
    }

    public static Expression<?>[] selectQWarehouseTheme(QWarehouseTheme e){
        return new Expression<?>[]{
                e.id,e.bgcolor,e.createTime, e.imgUrl, e.isDeleted, e.updateTime
        };
    }

    @Override
    public Page<WarehouseThemeVO> getThemeList(Pageable pageable) {
        QWarehouseTheme e=QWarehouseTheme.warehouseTheme;
        Page<WarehouseThemeVO> page= toPage(QF.select(selectQWarehouseTheme(e)).from(e)
                        .where(e.isDeleted.eq(false))
                        .orderBy(e.updateTime.desc()).orderBy(e.id.desc()),
                pageable, WarehouseThemeVO.class, e);
        return page;
    }


}
