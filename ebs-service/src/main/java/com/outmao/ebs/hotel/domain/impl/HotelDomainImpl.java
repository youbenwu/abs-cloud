package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.dao.HotelDeviceDao;
import com.outmao.ebs.hotel.domain.HotelDomain;
import com.outmao.ebs.hotel.domain.conver.HotelVOConver;
import com.outmao.ebs.hotel.domain.conver.SimpleHotelVOConver;
import com.outmao.ebs.hotel.dto.GetHotelListDTO;
import com.outmao.ebs.hotel.dto.HotelDTO;
import com.outmao.ebs.hotel.dto.RegisterHotelDTO;
import com.outmao.ebs.hotel.dto.SetHotelStatusDTO;
import com.outmao.ebs.hotel.entity.Hotel;
import com.outmao.ebs.hotel.entity.HotelContact;
import com.outmao.ebs.hotel.entity.QHotel;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.outmao.ebs.hotel.vo.SimpleHotelVO;
import com.outmao.ebs.hotel.vo.StatsHotelCountVO;
import com.outmao.ebs.org.common.annotation.BindingOrg;
import com.outmao.ebs.org.service.OrgService;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class HotelDomainImpl extends BaseDomain implements HotelDomain {


    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private HotelDeviceDao hotelDeviceDao;

    private HotelVOConver hotelVOConver=new HotelVOConver();

    private SimpleHotelVOConver simpleHotelVOConver=new SimpleHotelVOConver();


    @Transactional()
    @Override
    public Hotel registerHotel(RegisterHotelDTO request) {

        Assert.notNull(request.getUserId(),"用户ID不能为空");
        Assert.notNull(request.getName(),"酒店名称不能为空");

        checkContact(request.getContact());

        String area=request.getContact().getAddress().toShortAddress();

        Hotel hotel=request.getId()==null?null:hotelDao.getOne(request.getId());

        if(hotel==null){
            if(hotelDao.findByAreaAndName(area,request.getName())!=null){
                throw new BusinessException("酒店名称同名");
            }
            hotel=new Hotel();
            hotel.setContact(new HotelContact());
            hotel.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,hotel,"contact");

        BeanUtils.copyProperties(request.getContact(),hotel.getContact());

        hotel.setArea(area);

        hotel.setUpdateTime(new Date());

        hotel.setStatus(Status.NotAudit.getStatus());

        hotel.setKeyword(getKeyword(hotel));

        hotelDao.save(hotel);

        hotelDeviceDao.updateAddress(hotel.getId(),hotel.getContact().getAddress().getProvince(),hotel.getContact().getAddress().getCity());

        return hotel;
    }

    @Transactional()
    @Override
    public Hotel saveHotel(HotelDTO request) {

        checkContact(request.getContact());

        Hotel hotel=hotelDao.getOne(request.getId());

        security.hasPermission(hotel.getOrgId(),null);

        BeanUtils.copyProperties(request,hotel,"contact");

        if(request.getContact()!=null) {
            BeanUtils.copyProperties(request.getContact(), hotel.getContact());
        }

        hotel.setArea(hotel.getContact().getAddress().toShortAddress());

        hotel.setUpdateTime(new Date());

        hotel.setKeyword(getKeyword(hotel));

        hotelDao.save(hotel);

        hotelDeviceDao.updateAddress(hotel.getId(),hotel.getContact().getAddress().getProvince(),hotel.getContact().getAddress().getCity());

        return hotel;
    }


    @Override
    public Hotel getHotelByUserId(Long userId) {
        return hotelDao.findByUserId(userId);
    }

    private void checkContact(Contact contact){
        Assert.notNull(contact.getName(),"联系人名称不能为空");
        Assert.notNull(contact.getPhone(),"手机号不能为空");
        Assert.notNull(contact.getAddress(),"地址不能为空");
        Assert.notNull(contact.getAddress().getProvince(),"省份不能为空");
        //Assert.notNull(contact.getAddress().getCity(),"城市不能为空");
        Assert.notNull(contact.getAddress().getDistrict(),"地区不能为空");
    }


    private String getKeyword(Hotel hotel){
        StringBuffer s=new StringBuffer();
        s.append(hotel.getName());
        if(!StringUtils.isEmpty(hotel.getIntro())){
            s.append(" "+hotel.getIntro());
        }
        if(!StringUtils.isEmpty(hotel.getContact())){
            if(!StringUtils.isEmpty(hotel.getContact().getName())){
                s.append(" "+hotel.getContact().getName());
            }
            if(!StringUtils.isEmpty(hotel.getContact().getPhone())){
                s.append(" "+hotel.getContact().getPhone());
            }
            if(!StringUtils.isEmpty(hotel.getContact().getAddress())){
                s.append(" "+hotel.getContact().getAddress().toFullAddress());
            }
        }
        return s.toString();
    }

    @Transactional()
    @Override
    public Hotel setHotelStatus(SetHotelStatusDTO request)
    {
        Assert.notNull(request.getId(),"酒店ID不能为空");
        Hotel hotel=hotelDao.getOne(request.getId());
        BeanUtils.copyProperties(request,hotel);
        hotelDao.save(hotel);
        return hotel;
    }

    @Override
    public long getHotelCount() {
        return hotelDao.count();
    }

    @Override
    public HotelVO getHotelVOById(Long id) {
        QHotel e=QHotel.hotel;
        HotelVO vo=queryOne(e,e.id.eq(id),hotelVOConver);
        return vo;
    }

    @Override
    public HotelVO getHotelVOByOrgId(Long orgId) {
        QHotel e=QHotel.hotel;
        HotelVO vo=queryOne(e,e.orgId.eq(orgId),hotelVOConver);
        return vo;
    }

    @Override
    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable) {
        QHotel e=QHotel.hotel;
        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        Page<HotelVO> page=queryPage(e,p,hotelVOConver,pageable);

        return page;
    }


    @Override
    public List<HotelVO> getHotelVOListByOrgIdIn(Collection<Long> orgIdIn) {
        QHotel e=QHotel.hotel;
        Predicate p=e.orgId.in(orgIdIn);
        return queryList(e,p,hotelVOConver);
    }

    @Override
    public List<SimpleHotelVO> getSimpleHotelVOListByIdIn(Collection<Long> idIn) {
        QHotel e=QHotel.hotel;
        return queryList(e,e.id.in(idIn),simpleHotelVOConver);
    }

    @Override
    public List<StatsHotelCountVO> getStatsHotelCountVOListByDays(Date fromTime, Date toTime) {
        QHotel e=QHotel.hotel;
        List<Tuple> list=QF.select(e.count(),e.createTime.year(),e.createTime.month(),e.createTime.dayOfMonth()).groupBy(e.createTime.year(),e.createTime.month(),e.createTime.dayOfMonth()).from(e).where(e.createTime.between(fromTime,toTime)).fetch();

        List<StatsHotelCountVO> vos=new ArrayList<>(list.size());

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");

        list.forEach(t->{
            StatsHotelCountVO vo=new StatsHotelCountVO();
            calendar.set(t.get(e.createTime.year()),t.get(e.createTime.month())-1,t.get(e.createTime.dayOfMonth()));
            vo.setTime(calendar.getTime());
            vo.setIndex(formatter.format(calendar.getTime()));
            vo.setCount(t.get(e.count()));
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public List<StatsHotelCountVO> getStatsHotelCountVOListByMonths(Date fromTime, Date toTime) {
        QHotel e=QHotel.hotel;
        List<Tuple> list=QF.select(e.count(),e.createTime.year(),e.createTime.month()).groupBy(e.createTime.year(),e.createTime.month()).from(e).where(e.createTime.between(fromTime,toTime)).fetch();

        List<StatsHotelCountVO> vos=new ArrayList<>(list.size());

        Calendar calendar=Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");

        list.forEach(t->{
            StatsHotelCountVO vo=new StatsHotelCountVO();
            calendar.set(t.get(e.createTime.year()),t.get(e.createTime.month())-1,1);
            vo.setTime(calendar.getTime());
            vo.setIndex(formatter.format(calendar.getTime()));
            vo.setCount(t.get(e.count()));
            vos.add(vo);
        });
        return vos;
    }



}
