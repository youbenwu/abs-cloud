package com.outmao.ebs.mall.manufacturer.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.manufacturer.dao.ManufacturerDao;
import com.outmao.ebs.mall.manufacturer.domain.ManufacturerDomain;
import com.outmao.ebs.mall.manufacturer.domain.conver.CounselorVOConver;
import com.outmao.ebs.mall.manufacturer.domain.conver.ManufacturerVOConver;
import com.outmao.ebs.mall.manufacturer.dto.ManufacturerDTO;
import com.outmao.ebs.mall.manufacturer.entity.Manufacturer;
import com.outmao.ebs.mall.manufacturer.entity.QManufacturer;
import com.outmao.ebs.mall.manufacturer.vo.ManufacturerVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class ManufacturerDomainImpl extends BaseDomain implements ManufacturerDomain {

    @Autowired
    private ManufacturerDao manufacturerDao;


    private ManufacturerVOConver manufacturerVOConver=new ManufacturerVOConver();

    private CounselorVOConver counselorVOConver=new CounselorVOConver();


    @Transactional
    @Override
    public Manufacturer saveManufacturer(ManufacturerDTO request) {
        Manufacturer m=request.getId()==null?null:manufacturerDao.getOne(request.getId());
        if(m==null){
            m=new Manufacturer();
            m.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,m);

        m.setKeyword(getKeyword(m));

        manufacturerDao.save(m);

        return m;
    }

    private String getKeyword(Manufacturer m){
        StringBuffer s=new StringBuffer();
        if(m.getName()!=null){
            s.append(" "+m.getName());
        }

        return s.toString();
    }

    @Transactional
    @Override
    public void deleteManufacturerById(Long id) {
        manufacturerDao.deleteById(id);
    }

    @Override
    public ManufacturerVO getManufacturerVOById(Long id) {
        QManufacturer e=QManufacturer.manufacturer;
        ManufacturerVO vo=queryOne(e,e.id.eq(id),manufacturerVOConver);
        return vo;
    }

    @Override
    public Page<ManufacturerVO> getManufacturerVOPage(String keyword, Pageable pageable) {
        QManufacturer e=QManufacturer.manufacturer;

        Predicate p=null;

        if(StringUtil.isNotEmpty(keyword)){
            p=e.keyword.like("%"+keyword+"%");
        }

        return queryPage(e,p,manufacturerVOConver,pageable);
    }


}
