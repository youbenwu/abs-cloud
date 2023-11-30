package com.outmao.ebs.wallet.domain;



import com.outmao.ebs.wallet.dto.CashDTO;
import com.outmao.ebs.wallet.dto.GetCashListDTO;
import com.outmao.ebs.wallet.dto.GetStatsCashDTO;
import com.outmao.ebs.wallet.dto.SetCashStatusDTO;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.vo.CashVO;
import com.outmao.ebs.wallet.vo.StatsCashVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CashDomain {


    public Cash saveCash(CashDTO request);

    public Cash getCashByOrderNo(String orderNo);

    public Cash setCashStatus(SetCashStatusDTO request);

    public Page<CashVO> getCashVOPage(GetCashListDTO request, Pageable pageable);

    public StatsCashVO getStatsCashVO(GetStatsCashDTO request);


}
