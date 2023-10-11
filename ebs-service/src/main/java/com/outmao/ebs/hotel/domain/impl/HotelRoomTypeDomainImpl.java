package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.dao.HotelRoomDao;
import com.outmao.ebs.hotel.dao.HotelRoomTypeDao;
import com.outmao.ebs.hotel.domain.HotelRoomTypeDomain;
import com.outmao.ebs.hotel.domain.conver.HotelRoomTypeVOConver;
import com.outmao.ebs.hotel.dto.GetHotelRoomTypeListDTO;
import com.outmao.ebs.hotel.dto.HotelRoomTypeDTO;
import com.outmao.ebs.hotel.entity.HotelRoomType;
import com.outmao.ebs.hotel.entity.QHotelRoomType;
import com.outmao.ebs.hotel.vo.HotelRoomTypeVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class HotelRoomTypeDomainImpl extends BaseDomain implements HotelRoomTypeDomain {


    @Autowired
    private HotelRoomTypeDao hotelRoomTypeDao;

    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Autowired
    private HotelDao hotelDao;

    private HotelRoomTypeVOConver hotelRoomTypeVOConver=new HotelRoomTypeVOConver();


    @Transactional()
    @Override
    public HotelRoomType saveHotelRoomType(HotelRoomTypeDTO request) {

        HotelRoomType type=request.getId()==null?null:hotelRoomTypeDao.getOne(request.getId());

        if(type==null){
            type=hotelRoomTypeDao.findByHotelIdAndName(request.getHotelId(),request.getName());
            if(type!=null){
                throw new BusinessException("房型名称重复");
            }
            type=new HotelRoomType();
            type.setCreateTime(new Date());
            type.setHotel(hotelDao.getOne(request.getHotelId()));
            type.setOrgId(type.getHotel().getOrgId());
        }

        BeanUtils.copyProperties(request,type,"id","orgId");
        type.setUpdateTime(new Date());

        type.setKeyword(getKeyword(type));

        hotelRoomTypeDao.save(type);

        return type;
    }

    public String getKeyword(HotelRoomType data){
        StringBuffer s=new StringBuffer();

        if(!StringUtils.isEmpty(data.getName())){
            s.append(" "+data.getName());
        }

        if(!StringUtils.isEmpty(data.getIntro())){
            s.append(" "+data.getIntro());
        }

        return s.toString().trim();
    }

    @Transactional()
    @Override
    public void deleteHotelRoomTypeById(Long id) {
        if(hotelRoomDao.countByTypeId(id)>0){
            throw new BusinessException("房型正在被使用，不能删除");
        }
        HotelRoomType type=hotelRoomTypeDao.getOne(id);
        hotelRoomTypeDao.delete(type);
    }

    @Override
    public HotelRoomTypeVO getHotelRoomTypeVOById(Long id) {
        QHotelRoomType e=QHotelRoomType.hotelRoomType;
        HotelRoomTypeVO vo=queryOne(e,e.id.eq(id),hotelRoomTypeVOConver);
        return vo;
    }

    @Override
    public Page<HotelRoomTypeVO> getHotelRoomTypeVOPage(GetHotelRoomTypeListDTO request, Pageable pageable) {
        QHotelRoomType e=QHotelRoomType.hotelRoomType;

        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getHotelId()!=null){
            p=e.hotel.id.eq(request.getHotelId()).and(p);
        }

        Page<HotelRoomTypeVO> page=queryPage(e,p,hotelRoomTypeVOConver,pageable);

        return page;
    }




}
