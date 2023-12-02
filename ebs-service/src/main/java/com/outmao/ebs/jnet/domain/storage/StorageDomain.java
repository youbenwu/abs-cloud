package com.outmao.ebs.jnet.domain.storage;

import com.outmao.ebs.jnet.dto.storage.DemandBillParamsDTO;
import com.outmao.ebs.jnet.dto.storage.StorageParamsDTO;
import com.outmao.ebs.jnet.entity.storage.DemandBill;
import com.outmao.ebs.jnet.entity.storage.Storage;
import com.outmao.ebs.jnet.vo.storage.DemandBillVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleSpecVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleVO;
import com.outmao.ebs.jnet.vo.storage.StorageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StorageDomain {


    public Storage saveStorage(StorageParamsDTO params);

    public Storage getStorageByOrderId(Long orderId);

    public StorageVO getStorageVOById(Long id);

    public List<StorageStyleVO> getStorageStyleVOListByStorageId(Long storageId);
    public List<StorageStyleSpecVO> getStorageStyleSpecVOListByStyleId(Long styleId);


    //加单
    public DemandBill saveDemandBill(DemandBillParamsDTO params);
    //确认加单
    public DemandBill setDemandBillStatus(Long id, int status);

    //加单记录
    public Page<DemandBillVO> getDemandBillVOPage(Long storageId, Pageable pageable);





}
