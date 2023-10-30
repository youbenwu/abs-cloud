package com.outmao.ebs.wallet.service;

import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.GetRechargeListDTO;
import com.outmao.ebs.wallet.dto.RechargeDTO;
import com.outmao.ebs.wallet.dto.RechargePayPrepare;
import com.outmao.ebs.wallet.dto.SetRechargeStatusDTO;
import com.outmao.ebs.wallet.entity.Recharge;
import com.outmao.ebs.wallet.vo.RechargeVO;
import com.outmao.ebs.wallet.vo.TradeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RechargeService extends TradeStatusListener {

    public Recharge saveRecharge(RechargeDTO request);

    public Recharge setRechargeStatus(SetRechargeStatusDTO request);

    public Page<RechargeVO> getRechargeVOPage(GetRechargeListDTO request, Pageable pageable);

    public TradeVO rechargePayPrepare(RechargePayPrepare request);

}
