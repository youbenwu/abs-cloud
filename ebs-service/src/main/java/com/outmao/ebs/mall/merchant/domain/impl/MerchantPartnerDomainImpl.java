package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.merchant.common.annotation.SetMerchantPartnerStats;
import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchant;
import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchantBroker;
import com.outmao.ebs.mall.store.common.annotation.SetSimpleStore;
import com.outmao.ebs.mall.merchant.common.annotation.SetUserCommission;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.dao.MerchantBrokerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantPartnerDao;
import com.outmao.ebs.mall.merchant.domain.MerchantPartnerDomain;
import com.outmao.ebs.mall.merchant.domain.UserCommissionDomain;
import com.outmao.ebs.mall.merchant.domain.conver.MerchantPartnerVOConver;
import com.outmao.ebs.mall.merchant.dto.GetMerchantPartnerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantPartnerDTO;
import com.outmao.ebs.mall.merchant.dto.UserCommissionDTO;
import com.outmao.ebs.mall.merchant.entity.*;
import com.outmao.ebs.mall.merchant.vo.MerchantPartnerVO;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
public class MerchantPartnerDomainImpl extends BaseDomain implements MerchantPartnerDomain {


    @Autowired
    private MerchantPartnerDao merchantPartnerDao;

    @Autowired
    private MerchantBrokerDao merchantBrokerDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCommissionDomain userCommissionDomain;

    @Autowired
    private QrCodeService qrcodeService;

    @Autowired
    private MerchantPartnerVOConver merchantPartnerVOConver;

    @Transactional
    @Override
    public MerchantPartner saveMerchantPartner(MerchantPartnerDTO request) {
        MerchantPartner partner=request.getId()==null?null:merchantPartnerDao.findByIdForUpdate(request.getId());
        if(partner==null){
            partner=new MerchantPartner();
            partner.setCreateTime(new Date());
            partner.setMerchant(merchantDao.getOne(request.getMerchantId()));
            partner.setUser(userDao.getOne(request.getUserId()));
            if(request.getBrokerId()!=null) {
                partner.setBroker(merchantBrokerDao.getOne(request.getBrokerId()));
            }
            if(request.getParentId()!=null){
                MerchantPartner parent=merchantPartnerDao.findByIdForUpdate(request.getParentId());
                partner.setParent(parent);
                partner.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                }
            }
            MerchantPartnerStats stats=new MerchantPartnerStats();
            stats.setPartner(partner);
            partner.setStats(stats);
        }


        BeanUtils.copyProperties(request,partner);

        partner.setKeyword(getKeyword(partner));

        partner.setUpdateTime(new Date());

        merchantPartnerDao.save(partner);

        if(partner.getCommissionId()==null){
            UserCommissionDTO commissionDTO=new UserCommissionDTO();
            if(partner.getParent()!=null) {
                commissionDTO.setParentId(partner.getParent().getCommissionId());
            }
            commissionDTO.setMerchantId(partner.getMerchant().getId());
            commissionDTO.setTargetId(partner.getId());
            commissionDTO.setType(1);
            commissionDTO.setUserId(partner.getUser().getId());
            UserCommission commission=userCommissionDomain.saveUserCommission(commissionDTO);
            partner.setCommissionId(commission.getId());
        }

        if(partner.getUrl()==null){
            String url=config.getBaseUrl()+"/merchant/partner?id="+partner.getId();
            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
            partner.setUrl(url);
            partner.setQrCode(qrCode);
        }

        if(partner.getBrokerQrCode()==null){
            String url=config.getBaseUrl()+"/merchant/partner/addCustomer?id="+partner.getId();
            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
            partner.setBrokerQrCode(qrCode);
        }

        if(partner.getPyramidQrCode()==null){
            String url=config.getBaseUrl()+"/merchant/partner/addChildren?id="+partner.getId();
            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
            partner.setBrokerQrCode(qrCode);
        }

        partner.setCode(partner.getId().toString());

        return partner;
    }

    private String getKeyword(MerchantPartner member){
        StringBuffer s=new StringBuffer();
        if(member.getName()!=null){
            s.append(" "+member.getName());
        }
        if(member.getPhone()!=null){
            s.append(" "+member.getPhone());
        }
        if(member.getEmail()!=null){
            s.append(" "+member.getEmail());
        }
        s.append(member.getUser().getKeyword());
        return s.toString();
    }

    @Transactional
    @Override
    public void deleteMerchantPartnerById(Long id) {
        MerchantPartner partner=merchantPartnerDao.findByIdForUpdate(id);
        merchantPartnerDao.delete(partner);
    }

    @SetMerchantPartnerStats
    @SetSimpleMerchant
    @SetUserCommission
    @SetSimpleUser
    @SetSimpleMerchantBroker
    @SetSimpleStore
    @Override
    public MerchantPartnerVO getMerchantPartnerVOById(Long id) {
        QMerchantPartner e=QMerchantPartner.merchantPartner;

        MerchantPartnerVO vo=queryOne(e,e.id.eq(id),merchantPartnerVOConver);

        return vo;
    }

    @SetMerchantPartnerStats
    @SetSimpleMerchant
    @SetUserCommission
    @SetSimpleUser
    @SetSimpleMerchantBroker
    @SetSimpleStore
    @Override
    public List<MerchantPartnerVO> getMerchantPartnerVOListByUserId(Long userId) {
        QMerchantPartner e=QMerchantPartner.merchantPartner;
        return queryList(e,e.user.id.eq(userId),merchantPartnerVOConver);
    }

    @SetMerchantPartnerStats
    @SetSimpleMerchant
    @SetUserCommission
    @SetSimpleUser
    @SetSimpleMerchantBroker
    @SetSimpleStore
    @Override
    public Page<MerchantPartnerVO> getMerchantPartnerVOPage(GetMerchantPartnerListDTO request, Pageable pageable) {

        QMerchantPartner e=QMerchantPartner.merchantPartner;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getMerchantId()!=null) {
            p = e.merchant.id.eq(request.getMerchantId()).and(p);
        }

        if(request.getBrokerId()!=null){
            p=e.broker.id.eq(request.getBrokerId()).and(p);
        }

        Page<MerchantPartnerVO> page=queryPage(e,p,merchantPartnerVOConver,pageable);

        return page;
    }



}
