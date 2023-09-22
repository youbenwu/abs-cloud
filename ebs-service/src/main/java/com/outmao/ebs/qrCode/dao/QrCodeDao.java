package com.outmao.ebs.qrCode.dao;

import com.outmao.ebs.qrCode.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QrCodeDao extends JpaRepository<QrCode, Long>, QuerydslPredicateExecutor<QrCode> {
	
	public QrCode findByCode(String code);

}
