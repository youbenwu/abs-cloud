package com.outmao.ebs.hotel.service.scheduler;

import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.hotel.common.constant.HotelDeviceIncomeType;
import com.outmao.ebs.hotel.dao.HotelDeviceRenterDao;
import com.outmao.ebs.hotel.dto.HotelDeviceIncomeDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceRenter;
import com.outmao.ebs.hotel.service.HotelDeviceIncomeService;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.vo.SimpleHotelDeviceVO;
import com.outmao.ebs.mall.distribution.service.QyDistributionConfigService;
import com.outmao.ebs.mall.distribution.vo.QyDistributionConfigVO;
import com.outmao.ebs.portal.service.AdvertPvLogService;
import com.outmao.ebs.portal.vo.QyStatsAdvertPvForDeviceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 每天计算一次设备广告收益
 **/
@Slf4j
@Component
public class IncomeCalculateJob {

    @Autowired
    private QyDistributionConfigService qyDistributionConfigService;

    @Autowired
    private HotelDeviceRenterDao hotelDeviceRenterDao;

    @Autowired
    private AdvertPvLogService advertPvLogService;

    @Autowired
    private HotelDeviceIncomeService hotelDeviceIncomeService;

    @Autowired
    private HotelDeviceService hotelDeviceService;

    // 每天计算一次
    @Scheduled(cron="0 0 1 * * ?")
    public void process() {

        //获取收益配置
        QyDistributionConfigVO config=qyDistributionConfigService.getQyDistributionConfigVO();

        //获取所有机主
        List<HotelDeviceRenter> renters=hotelDeviceRenterDao.findAllByQuantityAfter(0);

        Map<Long,HotelDeviceRenter> renterMap=renters.stream().collect(Collectors.toMap(t->t.getUserId(),t->t));

        Date to= DateUtil.format_yyyy_MM_dd(new Date());
        Date from=DateUtil.addDays(to,-1);

        List<QyStatsAdvertPvForDeviceVO> list=advertPvLogService.getQyStatsAdvertPvForDeviceVOList(from,to);

        List<SimpleHotelDeviceVO> devices=hotelDeviceService.getSimpleHotelDeviceVOByUserIdIn(list.stream().map(t->t.getUserId()).collect(Collectors.toList()));

        Map<Long,SimpleHotelDeviceVO> deviceMap=devices.stream().collect(Collectors.toMap(t->t.getUserId(),t->t));


        for (QyStatsAdvertPvForDeviceVO vo:list){
            SimpleHotelDeviceVO device=deviceMap.get(vo.getUserId());
            if(device==null)
                continue;
            HotelDeviceRenter renter=device.getRenterId()!=null?renterMap.get(device.getRenterId()):null;
            saveIncome(vo,from,deviceMap.get(vo.getUserId()),renter,config);
        }


    }


    private void saveIncome(QyStatsAdvertPvForDeviceVO vo,Date time,SimpleHotelDeviceVO device,HotelDeviceRenter renter,QyDistributionConfigVO config){

        double renterC=config.getRenterCommission(renter.getQuantity());

        HotelDeviceIncomeDTO dto=new HotelDeviceIncomeDTO();
        dto.setTime(time);
        dto.setTotalAmount(vo.getAmount());
        dto.setTotalFee(vo.getAmount());
        if(device.getLeaseStatus()==1&&renter!=null){
            dto.setRenterId(renter.getUserId());
            dto.setRenterFee(vo.getAmount()*renterC);
        }
        dto.setFee(dto.getTotalFee()-dto.getRenterFee());
        dto.setDeviceId(device.getId());
        dto.setType(vo.getIncomeType());

        HotelDeviceIncomeType type=HotelDeviceIncomeType.get(vo.getIncomeType());
        dto.setRemark(type.getDescription()+"收益");

        dto.setStatus(0);


        try {

            hotelDeviceIncomeService.saveHotelDeviceIncome(dto);

        }catch (Exception e){
            log.error("保存设备广告收益出错",e);
        }



    }



}
