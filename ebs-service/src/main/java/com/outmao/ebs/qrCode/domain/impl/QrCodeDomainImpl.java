package com.outmao.ebs.qrCode.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.qrCode.common.QrCodeStatus;
import com.outmao.ebs.qrCode.dao.QrCodeDao;
import com.outmao.ebs.qrCode.domain.QrCodeDomain;
import com.outmao.ebs.qrCode.domain.QrCodeGenerator;
import com.outmao.ebs.qrCode.dto.*;
import com.outmao.ebs.qrCode.entity.QQrCode;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.Date;

@Slf4j
@Component
public class QrCodeDomainImpl extends BaseDomain implements QrCodeDomain {


	@Autowired
	private QrCodeDao qrCodeDao;

	@Autowired
	private QrCodeGenerator qrCodeGenerator;

	@Value("${config.base-url}")
	private String baseUrl;

	private String generateCode(){
		return baseUrl+"/qrCode?id=${id}";
	}


	@Override
	public String generateQrCode(GenerateQrCodeDTO request) {
		return qrCodeGenerator.generateQrCode(request);
	}

	@Override
	public void generateQrCode(GenerateQrCodeDTO request, OutputStream outputStream) {
		qrCodeGenerator.generateQrCode(request,outputStream);
	}

	@Override
	public void generateQrCode(GenerateQrCodeDTO request, String outputFile) {
		qrCodeGenerator.generateQrCode(request,outputFile);
	}

	@Transactional
	@Override
	public QrCode saveQrCode(QrCodeDTO request) {
		QrCode qrCode=request.getId()==null?null:qrCodeDao.findByIdForUpdate(request.getId());

		if(qrCode==null){
			qrCode=new QrCode();
			qrCode.setCreateTime(new Date());
			if(request.getCode()!=null) {
				generateQrCode(qrCode,request.getCode());
			}
		}

		BeanUtils.copyProperties(request,qrCode,"code");
		qrCode.setUpdateTime(new Date());

		if(qrCode.getStatus()==QrCodeStatus.Activated.getStatus()){
			if(qrCode.getActivateTime()==null){
				qrCode.setActivateTime(new Date());
			}
		}

		qrCodeDao.save(qrCode);

		if(qrCode.getCode()==null){
			String code=generateCode().replace("${id}",qrCode.getId().toString());
			generateQrCode(qrCode,code);
			qrCodeDao.save(qrCode);
		}

		return qrCode;
	}

	private void generateQrCode(QrCode qrCode, String code){
		String path = qrCodeGenerator.generateQrCode(new GenerateQrCodeDTO(code,500, 500));
		qrCode.setCode(code);
		qrCode.setPath(path);
	}

	@Override
	public void batchGenerateQrCode(BatchGenerateQrCodeDTO request) {
		QrCodeDTO codeDTO=new QrCodeDTO();
		for(int i=0;i<request.getCount();i++){
			try {
				saveQrCode(codeDTO);
			} catch (Exception e) {
				log.error("生成二维码出错", e);
			}
		}
	}


	@Async
	@Override
	public void batchGenerateQrCodeAsync(BatchGenerateQrCodeDTO request) {
		batchGenerateQrCode(request);
	}

	@Override
	public QrCode getQrCodeById(Long id) {
		return qrCodeDao.getOne(id);
	}

	@Override
	public QrCode getQrCodeByCode(String code) {
		return qrCodeDao.findByCode(code);
	}

	@Transactional
	@Override
	public QrCode activateQrCode(ActivateQrCodeDTO request) {

		QQrCode e=QQrCode.qrCode;
		Long id=QF.select(e.id).from(e).where(e.status.eq(QrCodeStatus.NotActivated.getStatus())).fetchFirst();

		if(id!=null){
			QrCode qrCode=qrCodeDao.findByIdForUpdate(id);
			if(qrCode.getStatus()==QrCodeStatus.NotActivated.getStatus()){
				qrCode.setUrl(request.getUrl());
				qrCode.setStatus(QrCodeStatus.Activated.getStatus());
				qrCode.setStatusRemark(QrCodeStatus.Activated.getStatusRemark());
				qrCode.setActivateTime(new Date());
				qrCode.setUpdateTime(new Date());
				return qrCodeDao.save(qrCode);
			}
		}

		QrCodeDTO dto=new QrCodeDTO();
		dto.setUrl(request.getUrl());
		dto.setStatus(QrCodeStatus.Activated.getStatus());
		dto.setStatusRemark(QrCodeStatus.Activated.getStatusRemark());
		QrCode qrCode=saveQrCode(dto);

		new Thread(new Runnable() {
			@Override
			public void run() {
				batchGenerateQrCodeAsync(new BatchGenerateQrCodeDTO(100));
			}
		}).start();


		return qrCode;
	}

	@Override
	public Page<QrCode> getQrCodePage(GetQrCodeListDTO request, Pageable pageable) {
		QQrCode e=QQrCode.qrCode;
		Predicate p=null;
		if(request.getStatus()!=null){
			p=e.status.eq(request.getStatus());
		}
		if(p!=null){
			return qrCodeDao.findAll(p,pageable);
		}
		return qrCodeDao.findAll(pageable);
	}


}
