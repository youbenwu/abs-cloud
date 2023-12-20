package com.outmao.ebs.mall.order.service;

import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.vo.QyPadToOrderVO;
import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.mall.order.vo.ToOrderVO;


public interface SettleService {

    public SettleVO saveSettle(SettleDTO request);

    public void deleteById(Long id);

    public SettleVO createSettle(CreateSettleDTO request);

    public SettleVO updateSettle(UpdateSettleDTO request);

    public SettleVO resettle(Long id);

    public SettleVO checkSettleChanged(Long id);

    public SettleVO getSettleById(Long id);

    public ToOrderVO buy(ToOrderDTO request);


    public QyPadToOrderVO toOrderAndPay(QyPadToOrderDTO request);


}
