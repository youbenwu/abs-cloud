package com.outmao.ebs.wallet.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.wallet.common.constant.WalletStatus;
import com.outmao.ebs.wallet.dao.WalletDao;
import com.outmao.ebs.wallet.domain.AssetDomain;
import com.outmao.ebs.wallet.domain.CurrencyDomain;
import com.outmao.ebs.wallet.domain.WalletDomain;
import com.outmao.ebs.wallet.domain.conver.WalletVOConver;
import com.outmao.ebs.wallet.dto.GetWalletListDTO;
import com.outmao.ebs.wallet.dto.SetWalletPasswordDTO;
import com.outmao.ebs.wallet.dto.SetWalletStatusDTO;
import com.outmao.ebs.wallet.dto.WalletDTO;
import com.outmao.ebs.wallet.entity.QWallet;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.vo.AssetVO;
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
import java.util.stream.Collectors;


@Component
public class WalletDomainImpl extends BaseDomain implements WalletDomain {

	@Autowired
    private UserDao userDao;

	@Autowired
    private WalletDao walletDao;

	@Autowired
	private AssetDomain assetDomain;

	@Autowired
	private CurrencyDomain currencyDomain;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private WalletVOConver walletVOConver;


	@Transactional
	@Override
	public Wallet saveWallet(WalletDTO request) {
		Wallet w=request.getId()!=null?walletDao.getOne(request.getId()):walletDao.findByUserIdAndType(request.getUserId(),request.getType());

		if(w==null){
			w=new Wallet();
			w.setCreateTime(new Date());
			w.setType(request.getType());
			w.setUser(userDao.getOne(request.getUserId()));
			w.setStatus(WalletStatus.WALLET_STATUS_NotOpen.getStatus());
			w.setStatusRemark(WalletStatus.WALLET_STATUS_NotOpen.getStatusRemark());
		}else{
			w.setStatus(WalletStatus.WALLET_STATUS_NotAudit.getStatus());
			w.setStatusRemark(WalletStatus.WALLET_STATUS_NotAudit.getStatusRemark());
		}

		BeanUtils.copyProperties(request,w,"id","type");

		if(StringUtil.isNotEmpty(request.getPassword())) {
			w.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		w.setUpdateTime(new Date());
		walletDao.save(w);

		assetDomain.saveWalletAssets(w.getId());

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
		if(vo!=null) {
			vo.setAssets(assetDomain.getAssetVOListByWalletId(id));
			if(vo.getAssets().size()!=currencyDomain.getCurrencyList().size()){
				assetDomain.saveWalletAssets(id);
				vo.setAssets(assetDomain.getAssetVOListByWalletId(id));
			}
		}
		return vo;
	}

	@Override
	public List<WalletVO> getWalletListVOByUserId(Long userId) {
		QWallet e=QWallet.wallet;
		List<WalletVO> list=queryList(e,e.user.id.eq(userId),walletVOConver);
        setAssets(list);
		return list;
	}


	@Override
	public Page<WalletVO> getWalletVOPage(GetWalletListDTO request, Pageable pageable) {

		QWallet e=QWallet.wallet;

		Predicate p=null;

		if(request.getType()!=null){
			p=e.type.eq(request.getType());
		}
		if(request.getStatusIn()!=null){
			p=e.status.in(request.getStatusIn()).and(p);
		}

		Page<WalletVO> page=queryPage(e,p,walletVOConver,pageable);

		setAssets(page.getContent());

		return page;
	}

	private void setAssets(List<WalletVO> list){
		if(list.isEmpty())
			return;
		List<AssetVO> assets=assetDomain.getAssetVOListByWalletIdIn(list.stream().map(t->t.getId()).collect(Collectors.toList()));
		list.forEach(t->{
			t.setAssets(assets.stream().filter(a->a.getWalletId()==t.getId()).collect(Collectors.toList()));
		});
	}



}
