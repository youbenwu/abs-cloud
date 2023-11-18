package com.outmao.ebs.org.domain.impl;




import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.org.dao.EnterpriseAccountInformationDao;
import com.outmao.ebs.org.dao.EnterpriseBrandInformationDao;
import com.outmao.ebs.org.dao.EnterpriseDao;
import com.outmao.ebs.org.domain.EnterpriseDomain;
import com.outmao.ebs.org.domain.conver.EnterpriseVOConver;
import com.outmao.ebs.org.dto.EnterpriseDTO;
import com.outmao.ebs.data.dto.GetEnterpriseListDTO;
import com.outmao.ebs.org.entity.enterprise.Enterprise;
import com.outmao.ebs.org.entity.enterprise.EnterpriseAccountInformation;
import com.outmao.ebs.org.entity.enterprise.EnterpriseBrandInformation;
import com.outmao.ebs.org.entity.enterprise.QEnterprise;
import com.outmao.ebs.org.vo.EnterpriseVO;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class EnterpriseDomainImpl extends BaseDomain implements EnterpriseDomain {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private EnterpriseBrandInformationDao enterpriseBrandInformationDao;

    @Autowired
    private EnterpriseAccountInformationDao enterpriseAccountInformationDao;


    private EnterpriseVOConver enterpriseVOConver=new EnterpriseVOConver();


    @Transactional
    @Override
    public Enterprise saveEnterprise(EnterpriseDTO request) {

        Enterprise enterprise=request.getId()==null?null:enterpriseDao.getOne(request.getId());

        if(enterprise==null){
            if(enterpriseDao.findByEnterpriseName(request.getEnterpriseName())!=null){
                throw new BusinessException("企业已存在");
            }
            enterprise=new Enterprise();
            enterprise.setUser(userDao.getOne(request.getUserId()));
            enterprise.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,enterprise,"accountInformation","brandInformation");

        enterprise.setStatus(Status.NotAudit.getStatus());
        enterprise.setStatusRemark(Status.NotAudit.getStatusRemark());

        enterprise.setUpdateTime(new Date());
        enterpriseDao.save(enterprise);

        saveAccountInformation(enterprise,request.getAccountInformation());
        saveBrandInformation(enterprise,request.getBrandInformation());

        return enterprise;
    }

    private void saveAccountInformation(Enterprise enterprise, List<EnterpriseAccountInformation> list){
        if(enterprise.getAccountInformation()!=null&&enterprise.getAccountInformation().size()>0){
            List<EnterpriseAccountInformation> dels=enterprise.getAccountInformation().stream().filter(t->!list.contains(t)).collect(Collectors.toList());
            if(dels.size()>0){
                enterpriseAccountInformationDao.deleteAll(dels);
            }
        }
        enterpriseAccountInformationDao.saveAll(list);
    }

    private void saveBrandInformation(Enterprise enterprise, List<EnterpriseBrandInformation> list){
        if(enterprise.getBrandInformation()!=null&&enterprise.getBrandInformation().size()>0){
            List<EnterpriseBrandInformation> dels=enterprise.getBrandInformation().stream().filter(t->!list.contains(t)).collect(Collectors.toList());
            if(dels.size()>0){
                enterpriseBrandInformationDao.deleteAll(dels);
            }
        }
        enterpriseBrandInformationDao.saveAll(list);
    }


    @Transactional
    @Override
    public Enterprise setEnterpriseStatus(Long id, int status, String statusRemark) {
        Enterprise enterprise=enterpriseDao.getOne(id);

        enterprise.setStatus(status);
        enterprise.setStatusRemark(statusRemark);

        enterpriseDao.save(enterprise);

        return enterprise;
    }

    @Override
    public Enterprise getEnterpriseById(Long id) {
        return enterpriseDao.findById(id).orElse(null);
    }


    @Override
    public List<Enterprise> getEnterpriseListByUserId(Long userId) {
        return enterpriseDao.findAllByUserId(userId);
    }

    @Override
    public Page<Enterprise> getEnterprisePage(GetEnterpriseListDTO request, Pageable pageable) {

        QEnterprise e= QEnterprise.enterprise;

        Predicate p=null;

        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId());
        }

        if(request.getStatusIn()!=null){
            p=e.status.in(request.getStatusIn()).and(p);
        }

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.enterpriseName.like("%"+request.getKeyword()+"%").and(p);
        }

        return enterpriseDao.findAll(p,pageable);
    }


    @Override
    public EnterpriseVO getEnterpriseVOById(Long id) {
        QEnterprise e=QEnterprise.enterprise;
        EnterpriseVO vo=queryOne(e,e.id.eq(id),enterpriseVOConver);
        if(vo!=null) {
            List<EnterpriseVO> list = new ArrayList<>(1);
            list.add(vo);
            loadSubData(list);
        }
        return vo;
    }

    @Override
    public List<EnterpriseVO> getEnterpriseVOListByUserId(Long userId) {

        QEnterprise e= QEnterprise.enterprise;

        List<EnterpriseVO> list=queryList(e,e.user.id.eq(userId),enterpriseVOConver);

        loadSubData(list);

        return list;
    }

    @Override
    public Page<EnterpriseVO> getEnterpriseVOPage(GetEnterpriseListDTO request, Pageable pageable) {

        QEnterprise e= QEnterprise.enterprise;

        Predicate p=null;

        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId());
        }

        if(request.getStatusIn()!=null){
            p=e.status.in(request.getStatusIn()).and(p);
        }

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.enterpriseName.like("%"+request.getKeyword()+"%").and(p);
        }

        Page<EnterpriseVO> page=queryPage(e,p,enterpriseVOConver,pageable);

        loadSubData(page.getContent());


        return page;
    }


    private void loadSubData(List<EnterpriseVO> list){

        if(list==null||list.isEmpty())
            return;

        List<Long> ids=list.stream().map(t->t.getId()).collect(Collectors.toList());

        List<EnterpriseBrandInformation> brandInformations=enterpriseBrandInformationDao.findAllByEnterpriseIdIn(ids);

        List<EnterpriseAccountInformation> accountInformations=enterpriseAccountInformationDao.findAllByEnterpriseIdIn(ids);

        list.forEach(t->{
            t.setBrandInformation(brandInformations.stream().filter(b->b.getEnterprise().getId().equals(t.getId())).collect(Collectors.toList()));
            t.setAccountInformation(accountInformations.stream().filter(a->a.getEnterprise().getId().equals(t.getId())).collect(Collectors.toList()));
        });


    }





}
