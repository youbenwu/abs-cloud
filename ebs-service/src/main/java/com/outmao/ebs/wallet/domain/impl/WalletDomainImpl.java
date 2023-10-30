package com.outmao.ebs.wallet.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.wallet.common.constant.WalletStatus;
import com.outmao.ebs.wallet.common.exception.WalletRegisterRepeatException;
import com.outmao.ebs.wallet.dao.BankAccountDao;
import com.outmao.ebs.wallet.dao.WalletDao;
import com.outmao.ebs.wallet.domain.WalletDomain;
import com.outmao.ebs.wallet.domain.conver.WalletVOConver;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.QWallet;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.vo.WalletVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Component
public class WalletDomainImpl extends BaseDomain implements WalletDomain {

	@Autowired
    private UserDao userDao;

	@Autowired
    private WalletDao walletDao;

	@Autowired
	private BankAccountDao bankAccountDao;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private WalletVOConver walletVOConver;


	@Transactional
	@Override
	public Wallet registerWallet(RegisterWalletDTO request) {

		Wallet w=walletDao.findByUserIdAndType(request.getUserId(),request.getType());

		if(w!=null){
			//只能注册一个
			throw new WalletRegisterRepeatException();
		}

		w=new Wallet();
		w.setUser(userDao.getOne(request.getUserId()));

		BeanUtils.copyProperties(request,w);

		if(request.getBankAccountId()!=null){
			w.setBankAccount(bankAccountDao.getOne(request.getBankAccountId()));
		}

		if(StringUtil.isNotEmpty(request.getPassword())) {
			w.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		w.setStatus(WalletStatus.WALLET_STATUS_NORMAL.getStatus());
		w.setStatusRemark(WalletStatus.WALLET_STATUS_NORMAL.getStatusRemark());

		w.setCreateTime(new Date());
		w.setUpdateTime(new Date());
		walletDao.save(w);

		return w;
	}

	@Transactional
	@Override
	public Wallet saveWallet(WalletDTO request) {
		Wallet w=walletDao.getOne(request.getId());

		BeanUtils.copyProperties(request,w,"password");

		if(request.getBankAccountId()!=null){
			w.setBankAccount(bankAccountDao.getOne(request.getBankAccountId()));
		}

		if(StringUtil.isNotEmpty(request.getPassword())) {
			w.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		w.setUpdateTime(new Date());
		walletDao.save(w);

		return w;
	}

	@Transactional
	@Override
	public Wallet setWalletPassword(SetWalletPasswordDTO request) {
		Wallet w=walletDao.getOne(request.getId());
		w.setPassword(passwordEncoder.encode(request.getPassword()));
		walletDao.save(w);
		return w;
	}

	@Transactional
	@Override
	public Wallet setWalletStatus(SetWalletStatusDTO request) {
		Wallet w=walletDao.getOne(request.getId());
		w.setStatus(request.getStatus());
		w.setStatusRemark(request.getStatusRemark());
		walletDao.save(w);
		return w;
	}


	@Override
	public WalletVO getWalletVOById(Long id) {
		QWallet e=QWallet.wallet;
		WalletVO vo=queryOne(e,e.id.eq(id),walletVOConver);
		return vo;
	}

	@Override
	public List<WalletVO> getWalletListVOByUserId(Long userId) {
		QWallet e=QWallet.wallet;
		List<WalletVO> list=queryList(e,e.user.id.eq(userId),walletVOConver);
		return list;
	}


	@Override
	public Page<WalletVO> getWalletVOPage(GetWalletListDTO request, Pageable pageable) {

		QWallet e=QWallet.wallet;

		Predicate p=null;

		if(request.getType()!=null){
			p=e.type.eq(request.getType());
		}
		if(request.getStatus()!=null){
			p=e.status.eq(request.getStatus()).and(p);
		}

		Page<WalletVO> page=queryPage(e,p,walletVOConver,pageable);

		return page;
	}




}
