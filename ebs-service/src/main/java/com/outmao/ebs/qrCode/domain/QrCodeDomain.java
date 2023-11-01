package com.outmao.ebs.qrCode.domain;

import com.outmao.ebs.qrCode.dto.*;
import com.outmao.ebs.qrCode.entity.QrCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.OutputStream;

public interface QrCodeDomain {

	/*
	 * 
	 * 生成码返回图片地址 code--码的值 width--码宽 height--码高
	 * 
	 */
	public String generateQrCode(GenerateQrCodeDTO request);

	/*
	 * 
	 * 生成码并输出到文件 code--码的值 width--码宽 height--码高
	 * 
	 */
	public void generateQrCode(GenerateQrCodeDTO request, OutputStream outputStream);

	/*
	 *
	 * 生成码并输出流 code--码的值 width--码宽 height--码高
	 *
	 */
	public void generateQrCode(GenerateQrCodeDTO request, String outputFile);


	/*
	 * 生成码
	 */
	public QrCode saveQrCode(QrCodeDTO request);


	/*
	 * 批量生成码
	 */
	public void batchGenerateQrCode(BatchGenerateQrCodeDTO request);



	public void batchGenerateQrCodeAsync(BatchGenerateQrCodeDTO request);




	public QrCode getQrCodeById(Long id);




	public QrCode getQrCodeByCode(String code);

	/*
	 * 激活一个码
	 */
	public QrCode activateQrCode(ActivateQrCodeDTO request);



	public Page<QrCode> getQrCodePage(GetQrCodeListDTO request, Pageable pageable);



}
