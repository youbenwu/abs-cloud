package com.outmao.ebs.qrCode.web;


import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/qrCode")
public class QrCodeController {

	@Autowired
	private QrCodeService qrCodeService;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public void qrCode(Long id, HttpServletResponse response) throws IOException {
		QrCode qrCode=qrCodeService.getQrCodeById(id);
        response.sendRedirect(qrCode.getUrl());
	}




}
