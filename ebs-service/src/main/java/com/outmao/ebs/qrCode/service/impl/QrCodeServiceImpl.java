package com.outmao.ebs.qrCode.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.qrCode.domain.QrCodeDomain;
import com.outmao.ebs.qrCode.dto.*;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

@Slf4j
@Service
public class QrCodeServiceImpl extends BaseService implements QrCodeService {
	
	@Autowired
	private QrCodeDomain qrCodeDomain;

	@Override
	public String generateQrCode(GenerateQrCodeDTO request) {
		return qrCodeDomain.generateQrCode(request);
	}

	@Override
	public void generateQrCode(GenerateQrCodeDTO request, OutputStream outputStream) {
		qrCodeDomain.generateQrCode(request,outputStream);
	}

	@Override
	public void generateQrCode(GenerateQrCodeDTO request, String outputFile) {
		qrCodeDomain.generateQrCode(request,outputFile);
	}

	@Override
	public QrCode saveQrCode(QrCodeDTO request) {
		return qrCodeDomain.saveQrCode(request);
	}

	@Override
	public QrCode updateQrCode(UpdateQrCodeDTO request) {
		return qrCodeDomain.updateQrCode(request);
	}

	@Override
	public void batchGenerateQrCode(BatchGenerateQrCodeDTO request) {
		qrCodeDomain.batchGenerateQrCode(request);
	}

	@Override
	public void batchGenerateQrCodeAsync(BatchGenerateQrCodeDTO request) {
		qrCodeDomain.batchGenerateQrCodeAsync(request);
	}

	@Override
	public QrCode getQrCodeById(Long id) {
		return qrCodeDomain.getQrCodeById(id);
	}

	@Override
	public QrCode getQrCodeByCode(String code) {
		return qrCodeDomain.getQrCodeByCode(code);
	}

	@Override
	public QrCode activateQrCode(ActivateQrCodeDTO request) {
		return qrCodeDomain.activateQrCode(request);
	}

	@Override
	public Page<QrCode> getQrCodePage(GetQrCodeListDTO request, Pageable pageable) {
		return qrCodeDomain.getQrCodePage(request,pageable);
	}
}
