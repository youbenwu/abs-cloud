package com.outmao.ebs.jnet.service.order.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.jnet.domain.order.ZOrderDomain;
import com.outmao.ebs.jnet.domain.storage.StorageDomain;
import com.outmao.ebs.jnet.domain.warehouse.WarehouseDomain;
import com.outmao.ebs.jnet.dto.order.OrderParamsDTO;
import com.outmao.ebs.jnet.dto.storage.StorageParamsDTO;
import com.outmao.ebs.jnet.dto.warehouse.WarehouseParamsDTO;
import com.outmao.ebs.jnet.entity.order.Order;
import com.outmao.ebs.jnet.entity.storage.Storage;
import com.outmao.ebs.jnet.entity.warehouse.Warehouse;
import com.outmao.ebs.jnet.service.order.ZOrderService;
import com.outmao.ebs.jnet.vo.order.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("ZOrderService")
public class ZOrderServiceImpl extends BaseService implements ZOrderService {

    @Autowired
    ZOrderDomain zOrderDomain;

    @Autowired
    StorageDomain storageDomain;

    @Autowired
    WarehouseDomain warehouseDomain;

    @Override
    public Order saveOrder(OrderParamsDTO params) {
        Order order= zOrderDomain.saveOrder(params);

        if(order.getProducer()!=null){
            if(order.getStorage()==null) {
                Storage storage = storage = storageDomain.saveStorage(
                        new StorageParamsDTO(null, order.getId(), params.getProducerId(), params.getProducerId(), order.getSpec()));
                order.setStorage(storage);
                order = zOrderDomain.saveOrder(order);
            }
            if(order.getWarehouse()==null){
                WarehouseParamsDTO paramsDTO=new WarehouseParamsDTO();
                paramsDTO.setUserId(order.getProducer().getId());
                paramsDTO.setManagerId(paramsDTO.getUserId());
                paramsDTO.setName(order.getSpec());
                Warehouse warehouse=warehouseDomain.saveWarehouse(paramsDTO);
                order.setWarehouse(warehouse);
                order = zOrderDomain.saveOrder(order);
            }
        }

        return order;
    }

    @Override
    public Page<TaskVO> getTaskVOPage(Integer type, Long userId, Pageable pageable) {
        return zOrderDomain.getTaskVOPage(type,userId,pageable);
    }


}
