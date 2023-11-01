package com.outmao.ebs.qrCode.dao;


import com.outmao.ebs.qrCode.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.LockModeType;

public interface QrCodeDao extends JpaRepository<QrCode, Long>, QuerydslPredicateExecutor<QrCode> {
	
	public QrCode findByCode(String code);

	@Lock(value = LockModeType.PESSIMISTIC_READ)
	@Query("select r from QrCode r where r.id=?1")
	public QrCode findByIdForUpdate(Long id);

}
