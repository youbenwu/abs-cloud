package com.outmao.ebs.mall.merchant.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.merchant.domain.MerchantDomain;
import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.merchant.vo.*;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Order(2)
@Service
public class MerchantServiceImpl extends BaseService implements MerchantService, CommandLineRunner {

    @Autowired
    private MerchantDomain merchantDomain;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user=userService.getUserByUsername("admin");
        //给系统用户创建一个商家
        if(user!=null){
            Merchant m=merchantDomain.getMerchantByUserId(user.getId());
            if(m==null){
                MerchantDTO merchantDTO=new MerchantDTO();
                merchantDTO.setUserId(user.getId());
                merchantDTO.setName("系统商家");
                merchantDTO.setType(0);
                saveMerchant(merchantDTO);
            }
        }
    }

    @Override
    public Merchant saveMerchant(MerchantDTO request) {

        return merchantDomain.saveMerchant(request);
    }

    @Override
    public Merchant setMerchantStatus(SetMerchantStatusDTO request) {
        return merchantDomain.setMerchantStatus(request);
    }

    @Override
    public Merchant getMerchant() {
        User user=userService.getUserByUsername("admin");
        if(user!=null){
            return merchantDomain.getMerchantByUserId(user.getId());
        }
        return null;
    }


    @Override
    public Merchant getMerchantByOrgId(Long orgId) {
        return merchantDomain.getMerchantByOrgId(orgId);
    }

    @Override
    public Merchant getMerchantByUserId(Long userId) {
        return merchantDomain.getMerchantByUserId(userId);
    }


    @Override
    public MerchantVO getMerchantVOByOrgId(Long orgId) {
        return merchantDomain.getMerchantVOByOrgId(orgId);
    }

    @Override
    public MerchantVO getMerchantVOById(Long id) {
        return merchantDomain.getMerchantVOById(id);
    }

    @Override
    public MerchantVO getMerchantVOByShopId(Long shopId) {
        return merchantDomain.getMerchantVOByShopId(shopId);
    }

    @Override
    public MerchantVO getMerchantVOByUserId(Long userId) {
        return merchantDomain.getMerchantVOByUserId(userId);
    }

    @Override
    public Page<MerchantVO> getMerchantVOPage(GetMerchantListDTO request, Pageable pageable) {
        return merchantDomain.getMerchantVOPage(request,pageable);
    }

    @Override
    public List<SimpleMerchantVO> getSimpleMerchantVOListByIdIn(Collection<Long> idIn) {
        return merchantDomain.getSimpleMerchantVOListByIdIn(idIn);
    }


}
