package com.outmao.ebs.jnet.service.storage;

import com.outmao.ebs.jnet.dto.storage.DemandBillParamsDTO;
import com.outmao.ebs.jnet.entity.storage.DemandBill;
import com.outmao.ebs.jnet.vo.storage.DemandBillVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleSpecVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleVO;
import com.outmao.ebs.jnet.vo.storage.StorageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StorageService {

    //加单
    public DemandBill saveDemandBill(DemandBillParamsDTO params);

    //加单记录
    public Page<DemandBillVO> getDemandBillVOPage(Long storageId, Pageable pageable);



    public StorageVO getStorageVOById(Long id);
    public List<StorageStyleVO> getStorageStyleVOListByStorageId(Long storageId);
    public List<StorageStyleSpecVO> getStorageStyleSpecListVOByStyleId(Long styleId);



}
