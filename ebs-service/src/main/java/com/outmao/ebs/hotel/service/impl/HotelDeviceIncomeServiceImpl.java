package com.outmao.ebs.hotel.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.domain.HotelDeviceIncomeDomain;
import com.outmao.ebs.hotel.dto.GetHotelDeviceIncomeListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceIncomeDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceIncome;
import com.outmao.ebs.hotel.service.HotelDeviceIncomeService;
import com.outmao.ebs.hotel.vo.HotelDeviceIncomeVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeStatsVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeTypeStatsVO;
import com.outmao.ebs.hotel.vo.RenterTotalHotelDeviceIncomeStatsVO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.wallet.dto.TradeRechargeDTO;
import com.outmao.ebs.wallet.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class HotelDeviceIncomeServiceImpl extends BaseService implements HotelDeviceIncomeService {


    @Autowired
    private HotelDeviceIncomeDomain hotelDeviceIncomeDomain;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private UserService userService;

    @Transactional()
    @Override
    public HotelDeviceIncome saveHotelDeviceIncome(HotelDeviceIncomeDTO request) {
        if(request.getStatus()==0){
            if(request.getRenterFee()>0.01) {
                User user = userService.getUserById(request.getRenterId());
                TradeRechargeDTO rechargeDTO = new TradeRechargeDTO();
                rechargeDTO.setAmount((long) (request.getRenterFee() * 100));
                rechargeDTO.setRemark(request.getRemark());
                rechargeDTO.setCurrencyId("RMB");
                rechargeDTO.setWalletId(user.getWalletId());
                tradeService.tradeRecharge(rechargeDTO);
            }
            request.setStatus(1);
        }
        return hotelDeviceIncomeDomain.saveHotelDeviceIncome(request);
    }

    @Override
    public Page<HotelDeviceIncomeVO> getHotelDeviceIncomeVOPage(GetHotelDeviceIncomeListDTO request, Pageable pageable) {
        return hotelDeviceIncomeDomain.getHotelDeviceIncomeVOPage(request,pageable);
    }

    @Override
    public RenterHotelDeviceIncomeStatsVO getRenterHotelDeviceIncomeStatsVO(Long renterId,Long deviceId) {

        return hotelDeviceIncomeDomain.getRenterHotelDeviceIncomeStatsVO(renterId,deviceId);

    }


    @Override
    public RenterTotalHotelDeviceIncomeStatsVO getRenterTotalHotelDeviceIncomeStatsVO(Long renterId) {
        return hotelDeviceIncomeDomain.getRenterTotalHotelDeviceIncomeStatsVO(renterId);
    }


    @Override
    public List<RenterHotelDeviceIncomeTypeStatsVO> getRenterHotelDeviceIncomeTypeStatsVOList(Long renterId) {
        return hotelDeviceIncomeDomain.getRenterHotelDeviceIncomeTypeStatsVOList(renterId);
    }




}
