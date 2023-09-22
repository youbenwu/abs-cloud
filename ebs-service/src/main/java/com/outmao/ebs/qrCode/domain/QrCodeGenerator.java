package com.outmao.ebs.qrCode.domain;

import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;

import java.io.OutputStream;

public interface QrCodeGenerator {

    public String generateQrCode(GenerateQrCodeDTO request);

    public void generateQrCode(GenerateQrCodeDTO request, OutputStream outputStream);

    public void generateQrCode(GenerateQrCodeDTO request, String outputFile);


}
