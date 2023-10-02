package com.outmao.ebs.mall.merchant.domain;


import com.outmao.ebs.mall.merchant.dto.GetMerchantCustomerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantCustomerDTO;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantCustomerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

public interface MerchantCustomerDomain {


    public MerchantCustomer saveMerchantCustomer(MerchantCustomerDTO request);

    public void deleteMerchantCustomerById(Long id);

    public MerchantCustomer getMerchantCustomer(Long merchantId, Long userId);

    public MerchantCustomerVO getMerchantCustomerVOById(Long id);

    public Page<MerchantCustomerVO> getMerchantCustomerVOPage(GetMerchantCustomerListDTO request, Pageable pageable);

    public List<SimpleMerchantCustomerVO> getSimpleMerchantCustomerVOByIdIn(Collection<Long> idIn);



}
