package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.org.common.annotation.BindingOrg;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.domain.MerchantDomain;
import com.outmao.ebs.mall.merchant.domain.conver.MerchantVOConver;
import com.outmao.ebs.mall.merchant.domain.conver.SimpleMerchantVOConver;
import com.outmao.ebs.mall.merchant.dto.GetMerchantListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantDTO;
import com.outmao.ebs.mall.merchant.dto.SetMerchantStatusDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantContact;
import com.outmao.ebs.mall.merchant.entity.QMerchant;
import com.outmao.ebs.mall.merchant.vo.MerchantVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantVO;
import com.outmao.ebs.qrCode.dto.ActivateQrCodeDTO;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.wallet.common.annotation.BindingWallet;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Component
public class MerchantDomainImpl extends BaseDomain implements MerchantDomain {


    @Autowired
    private UserDao userDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private QrCodeService qrcodeService;

    private MerchantVOConver merchantVOConver=new MerchantVOConver();
    private SimpleMerchantVOConver simpleMerchantVOConver=new SimpleMerchantVOConver();

    @Transactional
    @BindingWallet
    @BindingOrg
    @Override
    public Merchant saveMerchant(MerchantDTO request) {

        Merchant merchant=request.getId()==null?merchantDao.findByUserId(request.getUserId()):merchantDao.getOne(request.getId());

        if(merchant==null){
            merchant=new Merchant();
            merchant.setCreateTime(new Date());
            merchant.setUser(userDao.getOne(request.getUserId()));
            merchant.setContact(new MerchantContact());
        }

        if(request.getContact()!=null){
            BeanUtils.copyProperties(request.getContact(),merchant.getContact());
        }

        BeanUtils.copyProperties(request,merchant,"id","contact");
        merchant.setUpdateTime(new Date());

        merchant.setKeyword(getKeyword(merchant));

        merchant.setStatus(Status.NotAudit.getStatus());
        merchant.setStatusRemark(Status.NotAudit.getStatusRemark());

        merchantDao.save(merchant);

        if(merchant.getUrl()==null){
            String url=config.getBaseUrl()+"/merchant?id="+merchant.getId();
            QrCode qrCode=qrcodeService.activateQrCode(new ActivateQrCodeDTO(url));
            merchant.setUrl(url);
            merchant.setQrCode(qrCode.getPath());
        }

        return merchant;

    }



    private String getKeyword(Merchant m){
        StringBuffer s=new StringBuffer();
        if(m.getName()!=null){
            s.append(" "+m.getName());
        }
        if(m.getIntro()!=null){
            s.append(" "+m.getIntro());
        }
        if(m.getContact()!=null){
            if(m.getContact().getName()!=null)
                s.append(" "+m.getContact().getName());
            if(m.getContact().getPhone()!=null)
                s.append(" "+m.getContact().getPhone());
            if(m.getContact().getEmail()!=null)
                s.append(" "+m.getContact().getEmail());
        }
        return s.toString();
    }



    @Transactional
    @Override
    public Merchant setMerchantStatus(SetMerchantStatusDTO request) {
        Merchant m=merchantDao.getOne(request.getId());

        m.setStatus(request.getStatus());
        m.setStatusRemark(request.getStatusRemark());

        merchantDao.save(m);

        return m;

    }


    @Override
    public Merchant getMerchantByUserId(Long userId) {
        return merchantDao.findByUserId(userId);
    }

    @Override
    public Merchant getMerchantByOrgId(Long orgId) {
        return merchantDao.findByOrgId(orgId);
    }

    @SetSimpleUser
    @Override
    public MerchantVO getMerchantVOById(Long id) {
        QMerchant e=QMerchant.merchant;

        MerchantVO vo=queryOne(e,e.id.eq(id),merchantVOConver);

        return vo;
    }

    @SetSimpleUser
    @Override
    public MerchantVO getMerchantVOByOrgId(Long orgId) {
        QMerchant e=QMerchant.merchant;

        MerchantVO vo=queryOne(e,e.orgId.eq(orgId),merchantVOConver);

        return vo;
    }

    @Override
    public MerchantVO getMerchantVOByUserId(Long userId) {
        QMerchant e=QMerchant.merchant;

        MerchantVO vo=queryOne(e,e.user.id.eq(userId),merchantVOConver);

        return vo;
    }

    @Override
    public MerchantVO getMerchantVOByShopId(Long shopId) {
        QMerchant e=QMerchant.merchant;

        MerchantVO vo=queryOne(e,e.shopId.eq(shopId),merchantVOConver);

        return vo;
    }

    @Override
    public Page<MerchantVO> getMerchantVOPage(GetMerchantListDTO request, Pageable pageable) {

        QMerchant e=QMerchant.merchant;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%").and(p);
        }

        Page<MerchantVO> page=queryPage(e,p,merchantVOConver,pageable);



        return page;
    }

    @Override
    public List<SimpleMerchantVO> getSimpleMerchantVOListByIdIn(Collection<Long> idIn) {
        QMerchant e=QMerchant.merchant;
        List<SimpleMerchantVO> list=queryList(e,e.id.in(idIn),simpleMerchantVOConver);
        return list;
    }



}
