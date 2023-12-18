package com.outmao.ebs.mall.merchant.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.vo.MinHotelDeviceRenterVO;
import com.outmao.ebs.mall.merchant.domain.MerchantCustomerDomain;
import com.outmao.ebs.mall.merchant.dto.GetMerchantCustomerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantCustomerDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.merchant.service.MerchantCustomerService;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerVO;
import com.outmao.ebs.mall.merchant.vo.QyCustomerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantCustomerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MerchantCustomerServiceImpl extends BaseService implements MerchantCustomerService {

    @Autowired
    private MerchantCustomerDomain merchantCustomerDomain;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private HotelDeviceLeaseService hotelDeviceLeaseService;

    @Override
    public MerchantCustomer saveMerchantCustomer(MerchantCustomerDTO request) {
        if(request.getMerchantId()==null){
            Merchant merchant=merchantService.getMerchant();
            request.setMerchantId(merchant.getId());
        }
        return merchantCustomerDomain.saveMerchantCustomer(request);
    }

    @Override
    public void deleteMerchantCustomerById(Long id) {
        merchantCustomerDomain.deleteMerchantCustomerById(id);
    }

    @Override
    public MerchantCustomer getMerchantCustomer(Long merchantId, Long userId) {
        return merchantCustomerDomain.getMerchantCustomer(merchantId,userId);
    }

    @Override
    public MerchantCustomerVO getMerchantCustomerVOById(Long id) {
        return merchantCustomerDomain.getMerchantCustomerVOById(id);
    }

    @Override
    public Page<MerchantCustomerVO> getMerchantCustomerVOPage(GetMerchantCustomerListDTO request, Pageable pageable) {
        return merchantCustomerDomain.getMerchantCustomerVOPage(request,pageable);
    }

    @Override
    public List<SimpleMerchantCustomerVO> getSimpleMerchantCustomerVOByIdIn(Collection<Long> idIn) {
        return merchantCustomerDomain.getSimpleMerchantCustomerVOByIdIn(idIn);
    }


    @Override
    public Page<QyCustomerVO> getQyCustomerVOPage(GetMerchantCustomerListDTO request, Pageable pageable) {
        Page<MerchantCustomerVO> page=getMerchantCustomerVOPage(request,pageable);

        List<QyCustomerVO> content=new ArrayList<>(page.getContent().size());



        page.getContent().forEach(t->{
            QyCustomerVO vo=new QyCustomerVO();
            BeanUtils.copyProperties(t,vo);
            content.add(vo);
        });

        if(content.size()>0){
            List<MinHotelDeviceRenterVO> vos=hotelDeviceLeaseService.getMinHotelDeviceRenterVOListByUserIdIn(content.stream().map(t->t.getUserId()).collect(Collectors.toList()));
            Map<Long,MinHotelDeviceRenterVO> vosMap=vos.stream().collect(Collectors.toMap(t->t.getUserId(),t->t));
            content.forEach(t->{
                MinHotelDeviceRenterVO vo=vosMap.get(t.getUserId());
                if(vo!=null){
                    t.setRenter(vo);
                }
            });
        }

        return new PageImpl<QyCustomerVO>(content,pageable,page.getTotalElements());
    }
}
