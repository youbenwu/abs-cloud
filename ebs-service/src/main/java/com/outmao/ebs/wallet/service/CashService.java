package com.outmao.ebs.wallet.service;


import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.CashDTO;
import com.outmao.ebs.wallet.dto.GetCashListDTO;
import com.outmao.ebs.wallet.dto.GetStatsCashDTO;
import com.outmao.ebs.wallet.dto.SetCashStatusDTO;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.vo.CashVO;
import com.outmao.ebs.wallet.vo.StatsCashStatusVO;
import com.outmao.ebs.wallet.vo.StatsCashVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CashService extends TradeStatusListener {

    public Cash saveCash(CashDTO request);

    public void deleteCashById(Long id);

    public Cash setCashStatus(SetCashStatusDTO request);

    public Page<CashVO> getCashVOPage(GetCashListDTO request, Pageable pageable);

    public StatsCashVO getStatsCashVO(GetStatsCashDTO request);

    public List<StatsCashStatusVO> getStatsCashStatusVOList();

}
