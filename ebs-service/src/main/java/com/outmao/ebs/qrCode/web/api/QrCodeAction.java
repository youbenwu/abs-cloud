package com.outmao.ebs.qrCode.web.api;

import com.outmao.ebs.qrCode.dto.ActivateQrCodeDTO;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "qrCode", tags = "二维码")
@RestController
@RequestMapping("/api/qrCode")
public class QrCodeAction {

	@Autowired
	private QrCodeService qrCodeService;



	@ApiOperation(value = "获取二维吗", notes = "获取二维吗,反回地地址")
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public QrCode getQrCodeById(Long id){
		return qrCodeService.getQrCodeById(id);
	}

	@ApiOperation(value = "获取二维吗", notes = "获取二维吗")
	@RequestMapping(value = "/getByCode", method = RequestMethod.POST)
	public QrCode getQrCodeByCode(String code){
		return qrCodeService.getQrCodeByCode(code);
	}


	@ApiOperation(value = "获取二维吗", notes = "获取二维吗")
	@RequestMapping(value = "/activate", method = RequestMethod.POST)
	public QrCode activateQrCode(ActivateQrCodeDTO request){
		return qrCodeService.activateQrCode(request);
	}


}
