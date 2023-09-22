package com.outmao.ebs.wallet.dao;

import com.outmao.ebs.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletDao  extends JpaRepository<Wallet,Long> {


	public Wallet findByUserIdAndType(Long userId, int type);

	public Wallet findByAccount(String account);




}
