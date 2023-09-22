package com.outmao.ebs.wallet.domain;

import com.outmao.ebs.wallet.dto.GetRechargeListDTO;
import com.outmao.ebs.wallet.dto.RechargeDTO;
import com.outmao.ebs.wallet.dto.SetRechargeStatusDTO;
import com.outmao.ebs.wallet.entity.Recharge;
import com.outmao.ebs.wallet.vo.RechargeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RechargeDomain {


    public Recharge saveRecharge(RechargeDTO request);

    public Recharge setRechargeStatus(SetRechargeStatusDTO request);

    public Page<RechargeVO> getRechargeVOPage(GetRechargeListDTO request, Pageable pageable);


}
