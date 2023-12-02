package com.outmao.ebs.jnet.service.storage.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.jnet.domain.storage.StorageDomain;
import com.outmao.ebs.jnet.dto.storage.DemandBillParamsDTO;
import com.outmao.ebs.jnet.entity.storage.DemandBill;
import com.outmao.ebs.jnet.service.storage.StorageService;
import com.outmao.ebs.jnet.vo.storage.DemandBillVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleSpecVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleVO;
import com.outmao.ebs.jnet.vo.storage.StorageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("ZStorageService")
public class StorageServiceImpl extends BaseService implements StorageService {

    @Autowired
    StorageDomain storageDomain;

    @Override
    public DemandBill saveDemandBill(DemandBillParamsDTO params) {
        return storageDomain.saveDemandBill(params);
    }

    @Override
    public StorageVO getStorageVOById(Long id) {
        return storageDomain.getStorageVOById(id);
    }

    @Override
    public List<StorageStyleVO> getStorageStyleVOListByStorageId(Long storageId) {
        return storageDomain.getStorageStyleVOListByStorageId(storageId);
    }


    @Override
    public List<StorageStyleSpecVO> getStorageStyleSpecListVOByStyleId(Long styleId) {
        return storageDomain.getStorageStyleSpecVOListByStyleId(styleId);
    }

    @Override
    public Page<DemandBillVO> getDemandBillVOPage(Long storageId, Pageable pageable) {
        return storageDomain.getDemandBillVOPage(storageId,pageable);
    }
}
