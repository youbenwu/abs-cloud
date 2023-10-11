package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.hotel.common.constant.HotelRoomStatus;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.dao.HotelRoomDao;
import com.outmao.ebs.hotel.dao.HotelRoomTypeDao;
import com.outmao.ebs.hotel.domain.HotelRoomDomain;
import com.outmao.ebs.hotel.domain.conver.HotelRoomVOConver;
import com.outmao.ebs.hotel.dto.GetHotelRoomListDTO;
import com.outmao.ebs.hotel.dto.HotelRoomDTO;
import com.outmao.ebs.hotel.dto.SetHotelRoomStatusDTO;
import com.outmao.ebs.hotel.dto.SetHotelStatusDTO;
import com.outmao.ebs.hotel.entity.HotelRoom;
import com.outmao.ebs.hotel.entity.QHotelRoom;
import com.outmao.ebs.hotel.vo.HotelRoomVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;


@Component
public class HotelRoomDomainImpl extends BaseDomain implements HotelRoomDomain {


    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Autowired
    private HotelRoomTypeDao hotelRoomTypeDao;

    @Autowired
    private HotelDao hotelDao;

    private HotelRoomVOConver hotelRoomVOConver=new HotelRoomVOConver();

    @Transactional()
    @Override
    public HotelRoom saveHotelRoom(HotelRoomDTO request) {

        Assert.notNull(request.getHotelId(),"酒店ID不能为空");
        Assert.notNull(request.getRoomNo(),"房间号不能为空");

        HotelRoom room=request.getId()==null?null:hotelRoomDao.findByIdForUpdate(request.getId());

        if(room==null){
            room=new HotelRoom();
            room.setHotel(hotelDao.getOne(request.getHotelId()));
            room.setOrgId(room.getHotel().getOrgId());
            room.setCreateTime(new Date());
        }

        security.hasPermission(room.getOrgId(),null);

        room.setType(hotelRoomTypeDao.getOne(request.getTypeId()));

        if(!room.getHotel().getId().equals(room.getType().getHotel().getId())){
            throw new BusinessException("酒店不一致");
        }

        BeanUtils.copyProperties(request,room);

        room.setUpdateTime(new Date());

        room.setKeyword(getKeyword(room));

        hotelRoomDao.save(room);

        return room;
    }

    @Override
    public HotelRoom getHotelRoom(Long hotelId, String roomNo) {
        return hotelRoomDao.findByHotelIdAndRoomNo(hotelId,roomNo);
    }

    private String getKeyword(HotelRoom room){
        StringBuffer s=new StringBuffer();

        s.append(room.getRoomNo());

        if(!StringUtils.isEmpty(room.getName())){
            s.append(" "+room.getName());
        }
        if(!StringUtils.isEmpty(room.getIntro())){
            s.append(" "+room.getIntro());
        }

        return s.toString();
    }

    @Transactional()
    @Override
    public void deleteHotelRoomById(Long id) {
        HotelRoom room=hotelRoomDao.getOne(id);
        hotelRoomDao.delete(room);
    }

    @Transactional()
    @Override
    public HotelRoom setHotelRoomStatus(SetHotelRoomStatusDTO request) {

        HotelRoom room=hotelRoomDao.findByIdForUpdate(request.getId());

        security.hasPermission(room.getOrgId(),null);

        if(request.getStatus()==room.getStatus()){
            if(request.getStatus()==HotelRoomStatus.Idle.getStatus()){
                throw new BusinessException("房间已是空闲");
            }
            if(request.getStatus()== HotelRoomStatus.Stay.getStatus()){
                throw new BusinessException("房间已有客人");
            }
            if(request.getStatus()==HotelRoomStatus.Repair.getStatus()){
                throw new BusinessException("房间已是维修");
            }
        }

        if(request.getStatus()==HotelRoomStatus.Stay.getStatus()){
            if(request.getStatus()==HotelRoomStatus.Repair.getStatus()){
                throw new BusinessException("房间住客状态不能维修");
            }
        }

        if(request.getStatus()==HotelRoomStatus.Repair.getStatus()){
            if(request.getStatus()==HotelRoomStatus.Stay.getStatus()){
                throw new BusinessException("房间维修状态不能入住");
            }
        }

        BeanUtils.copyProperties(request,room);

        hotelRoomDao.save(room);

        return room;
    }



    @Override
    public HotelRoomVO getHotelRoomVOById(Long id) {
        QHotelRoom e=QHotelRoom.hotelRoom;

        HotelRoomVO vo=queryOne(e,e.id.eq(id),hotelRoomVOConver);

        return vo;
    }


    @Override
    public HotelRoomVO getHotelRoomVO(Long hotelId, String roomNo) {
        QHotelRoom e=QHotelRoom.hotelRoom;

        HotelRoomVO vo=queryOne(e,e.hotel.id.eq(hotelId).and(e.roomNo.eq(roomNo)),hotelRoomVOConver);

        return vo;
    }

    @Override
    public Page<HotelRoomVO> getHotelRoomVOPage(GetHotelRoomListDTO request, Pageable pageable) {

        QHotelRoom e=QHotelRoom.hotelRoom;

        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getHotelId()!=null){
            p=e.hotel.id.eq(request.getHotelId()).and(p);
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        Page<HotelRoomVO> page=queryPage(e,p,hotelRoomVOConver,pageable);

        return page;
    }




}
