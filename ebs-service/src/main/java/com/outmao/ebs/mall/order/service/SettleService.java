package com.outmao.ebs.mall.order.service;

import com.outmao.ebs.mall.order.dto.CreateSettleDTO;
import com.outmao.ebs.mall.order.dto.SettleDTO;
import com.outmao.ebs.mall.order.dto.ToOrderDTO;
import com.outmao.ebs.mall.order.dto.UpdateSettleDTO;
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




}
