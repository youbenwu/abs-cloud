package com.outmao.ebs.mall.manufacturer.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.manufacturer.dao.CounselorDao;
import com.outmao.ebs.mall.manufacturer.dao.ManufacturerDao;
import com.outmao.ebs.mall.manufacturer.domain.CounselorDomain;
import com.outmao.ebs.mall.manufacturer.domain.conver.CounselorVOConver;
import com.outmao.ebs.mall.manufacturer.dto.CounselorDTO;
import com.outmao.ebs.mall.manufacturer.dto.GetCounselorDTO;
import com.outmao.ebs.mall.manufacturer.entity.Counselor;
import com.outmao.ebs.mall.manufacturer.entity.QCounselor;
import com.outmao.ebs.mall.manufacturer.vo.CounselorVO;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class CounselorDomainImpl extends BaseDomain implements CounselorDomain {


    @Autowired
    private CounselorDao counselorDao;

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Autowired
    private UserDao userDao;

    private CounselorVOConver counselorVOConver=new CounselorVOConver();

    @Transactional
    @Override
    public Counselor saveCounselor(CounselorDTO request) {

        Counselor c=request.getId()==null?null:counselorDao.getOne(request.getId());

        if(c==null){
            c=new Counselor();
            c.setUser(userDao.getOne(request.getUserId()));
            c.setManufacturer(manufacturerDao.getOne(request.getManufacturerId()));
            c.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,c);

        c.setKeyword(getKeyword(c));

        counselorDao.save(c);

        return c;
    }

    @Transactional
    @Override
    public void deleteCounselorById(Long id) {
        counselorDao.deleteById(id);
    }

    @Override
    public CounselorVO getCounselorVOById(Long id) {
        QCounselor e=QCounselor.counselor;
        return queryOne(e,e.id.eq(id),counselorVOConver);
    }

    @Override
    public Page<CounselorVO> getCounselorVOPage(GetCounselorDTO request, Pageable pageable) {
        QCounselor e=QCounselor.counselor;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        return queryPage(e,p,counselorVOConver,pageable);
    }

    private String getKeyword(Counselor m){
        StringBuffer s=new StringBuffer();
        if(m.getName()!=null){
            s.append(" "+m.getName());
        }
        if(m.getPhone()!=null){
            s.append(" "+m.getPhone());
        }
        return s.toString();
    }




}
