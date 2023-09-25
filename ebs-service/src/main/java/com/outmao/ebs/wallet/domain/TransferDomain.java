package com.outmao.ebs.wallet.domain;


import com.outmao.ebs.wallet.dto.GetTransferListDTO;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.entity.Transfer;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.vo.TransferVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferDomain {


    /**
     *
     * 转帐
     *
     */
    public Transfer transfer(
            Trade trade, //所属交易
            Wallet from, //转出钱包
            Wallet to, //转入钱包
            Transfer.TransferType fromType, //转帐类型
            Transfer.TransferType toType, //转帐类型
            long amount, //转帐金额
            int businessType, //业务类型
            String business, //业务说明
            String remark//转帐备注
    );


    /**
     *
     * 佘额转帐
     * 发起方佘额-->收款方佘额
     *
     */
    public Transfer transferBalance(Trade trade);


    /**
     *
     * 预付款
     * 发起方佘额-->发起方预付款
     *
     */
    public Transfer transferAdvance(Trade trade);


    /**
     *
     * 确定支付
     * 发起方预付款-->收款方佘额
     *
     */
    public Transfer transferTo(Trade trade);

    /**
     *
     * 确定支付
     *
     */
    public Transfer transferTo(
            Trade trade, //所属交易
            Wallet to, //转入钱包
            long amount, //转帐金额
            int businessType, //业务类型
            String business, //业务说明
            String remark//转帐备注
    );


    /**
     *
     * 手续费
     *
     */
    public Transfer transferFee(Trade trade);



    /**
     *
     * 退款
     *
     */
    public Transfer transferRefund(
            Trade trade, //所属交易
            long amount,//退款金额
            int businessType, //业务类型
            String business,//业务说明
            String remark//退款备注
    );



    /**
     *
     * 获取交易明细
     *
     */
    public Page<TransferVO> getTransferVOPage(GetTransferListDTO request, Pageable pageable);




}
