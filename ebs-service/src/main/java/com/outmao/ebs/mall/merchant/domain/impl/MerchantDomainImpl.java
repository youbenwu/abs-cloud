package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.org.domain.EnterpriseDomain;
import com.outmao.ebs.mall.merchant.common.annotation.SetMerchantStats;
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
import com.outmao.ebs.mall.shop.domain.ShopDomain;
import com.outmao.ebs.mall.shop.dto.ShopDTO;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.store.domain.StoreDomain;
import com.outmao.ebs.mall.store.dto.StoreDTO;
import com.outmao.ebs.org.entity.enterprise.Enterprise;
import com.outmao.ebs.org.service.OrgService;
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
    private ShopDomain shopDomain;

    @Autowired
    private StoreDomain storeDomain;

    @Autowired
    private QrCodeService qrcodeService;


    @Autowired
    private EnterpriseDomain enterpriseDomain;

    @Autowired
    private OrgService orgService;



    private MerchantVOConver merchantVOConver=new MerchantVOConver();
    private SimpleMerchantVOConver simpleMerchantVOConver=new SimpleMerchantVOConver();

    @Transactional
    @Override
    public Merchant saveMerchant(MerchantDTO request) {

        Merchant merchant=request.getId()==null?null:merchantDao.getOne(request.getId());

        if(merchant==null){
            merchant=new Merchant();
            merchant.setCreateTime(new Date());
            merchant.setUser(userDao.getOne(request.getUserId()));
        }

        if(request.getEnterprise()!=null){
            request.getEnterprise().setUserId(request.getUserId());
            Enterprise enterprise=enterpriseDomain.saveEnterprise(request.getEnterprise());
            merchant.setEnterpriseId(enterprise.getId());
        }

        if(request.getContact()!=null){
            if(merchant.getContact()==null){
                merchant.setContact(new MerchantContact());
            }
            BeanUtils.copyProperties(request.getContact(),merchant.getContact());
        }

        BeanUtils.copyProperties(request,merchant,"contact");
        merchant.setUpdateTime(new Date());

        merchant.setKeyword(getKeyword(merchant));

        merchant.setStatus(Status.NotAudit.getStatus());
        merchant.setStatusRemark(Status.NotAudit.getStatusRemark());

        merchantDao.save(merchant);

        if(merchant.getOrgId()==null){
            orgService.registerOrg(merchant);
        }

//        if(merchant.getUrl()==null){
//            String url=config.getBaseUrl()+"/merchant?id="+merchant.getId();
//            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
//            merchant.setUrl(url);
//            merchant.setQrCode(qrCode);
//        }

        if(merchant.getShopId()==null){

            //创建店铺
            ShopDTO shopDTO=new ShopDTO();
            shopDTO.setMerchantId(merchant.getId());
            shopDTO.setTitle(merchant.getId()+":"+merchant.getName());
            Shop shop= shopDomain.saveShop(shopDTO);
            merchant.setShopId(shop.getId());

            //创建默认仓库
            StoreDTO storeDTO=new StoreDTO();
            storeDTO.setMerchantId(merchant.getId());
            storeDTO.setType(1);
            storeDTO.setTitle(merchant.getId()+":"+"默认仓库");
            storeDomain.saveStore(storeDTO);

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
        if(vo.getEnterpriseId()!=null){
            vo.setEnterprise(enterpriseDomain.getEnterpriseVOById(vo.getEnterpriseId()));
        }
        return vo;
    }


    @Override
    public MerchantVO getMerchantVOByOrgId(Long orgId) {
        return null;
    }

    @SetMerchantStats
    @Override
    public MerchantVO getMerchantVOByUserId(Long userId) {
        QMerchant e=QMerchant.merchant;

        MerchantVO vo=queryOne(e,e.user.id.eq(userId),merchantVOConver);
        if(vo.getEnterpriseId()!=null){
            vo.setEnterprise(enterpriseDomain.getEnterpriseVOById(vo.getEnterpriseId()));
        }
        return vo;
    }

    @Override
    public MerchantVO getMerchantVOByShopId(Long shopId) {
        QMerchant e=QMerchant.merchant;

        MerchantVO vo=queryOne(e,e.shopId.eq(shopId),merchantVOConver);
        if(vo.getEnterpriseId()!=null){
            vo.setEnterprise(enterpriseDomain.getEnterpriseVOById(vo.getEnterpriseId()));
        }
        return vo;
    }

    @SetMerchantStats
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
