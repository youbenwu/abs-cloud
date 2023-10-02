package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.order.domain.SettleDomain;
import com.outmao.ebs.mall.order.dto.CreateSettleDTO;
import com.outmao.ebs.mall.order.dto.SettleDTO;
import com.outmao.ebs.mall.order.dto.ToOrderDTO;
import com.outmao.ebs.mall.order.dto.UpdateSettleDTO;
import com.outmao.ebs.mall.order.service.SettleService;
import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.mall.order.vo.ToOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SettleServiceImpl extends BaseService implements SettleService {

    @Autowired
    private SettleDomain settleDomain;

    @Override
    public SettleVO saveSettle(SettleDTO request) {
        return settleDomain.saveSettle(request);
    }

    @Override
    public void deleteById(Long id) {
        settleDomain.deleteById(id);
    }

    @Override
    public SettleVO createSettle(CreateSettleDTO request) {
        return settleDomain.createSettle(request);
    }

    @Override
    public SettleVO updateSettle(UpdateSettleDTO request) {
        return settleDomain.updateSettle(request);
    }

    @Override
    public SettleVO resettle(Long id) {
        return settleDomain.resettle(id);
    }

    @Override
    public SettleVO checkSettleChanged(Long id) {
        return settleDomain.checkSettleChanged(id);
    }

    @Override
    public SettleVO getSettleById(Long id) {
        return settleDomain.getSettleById(id);
    }

    @Override
    public ToOrderVO buy(ToOrderDTO request) {
        return settleDomain.buy(request);
    }
}
