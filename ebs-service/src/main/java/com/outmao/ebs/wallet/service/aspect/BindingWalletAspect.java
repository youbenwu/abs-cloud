package com.outmao.ebs.wallet.service.aspect;


import com.outmao.ebs.wallet.common.constant.WalletType;
import com.outmao.ebs.wallet.common.data.BindingWallet;
import com.outmao.ebs.wallet.dto.RegisterWalletDTO;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.service.WalletService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@Component
public class BindingWalletAspect {

	@Autowired
	private WalletService walletService;


	@Pointcut("@annotation(com.outmao.ebs.wallet.common.annotation.BindingWallet)")
	public void BindingWallet() {

	}



	@Transactional
	@AfterReturning(returning = "bindingWallet",value = "BindingWallet()")
	public void afterBindingSubject(JoinPoint jp, BindingWallet bindingWallet) {
		if(bindingWallet.getWalletId()!=null)
			return;

		int type= WalletType.WALLET_TYPE_USER.getType();

		if(bindingWallet.toItem().getType().equals("Merchant")){
			type=WalletType.WALLET_TYPE_MERCHANT.getType();
		}

		RegisterWalletDTO walletDTO=new RegisterWalletDTO();
		walletDTO.setUserId(bindingWallet.getUserId());
		walletDTO.setType(type);

        Wallet wallet=walletService.registerWallet(walletDTO);

        bindingWallet.setWalletId(wallet.getId());

	}


}
