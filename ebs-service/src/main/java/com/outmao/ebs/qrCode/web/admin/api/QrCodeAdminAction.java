package com.outmao.ebs.qrCode.web.admin.api;

import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.qrCode.dto.BatchGenerateQrCodeDTO;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import com.outmao.ebs.qrCode.dto.GetQrCodeListDTO;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@AccessPermissionGroup(title="二维码",url="/qrCode",name="",children = {
		@AccessPermissionParent(title = "二维码管理",url = "/qrCode/qrCode",name = "",children = {
				@AccessPermission(title = "保存二维码",url = "/qrCode/qrCode",name = "save"),
				@AccessPermission(title = "删除二维码",url = "/qrCode/qrCode",name = "delete"),
				@AccessPermission(title = "读取二维码",url = "/qrCode/qrCode",name = "read"),
		}),
})

@Api(value = "account-qrCode", tags = "后台-二维码")
@RestController
@RequestMapping("/api/admin/qrCode")
public class QrCodeAdminAction {

	@Autowired
	private QrCodeService qrCodeService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "二维码内容", type = "form", required = true, dataType = "String"),
			@ApiImplicitParam(name = "width", value = "二维码宽", type = "form", required = true, dataType = "int"),
			@ApiImplicitParam(name = "height", value = "二维码高", type = "form", required = true, dataType = "int") })
	@PreAuthorize("hasPermission('/qrCode/qrCode','save')")
	@ApiOperation(value = "生成二维吗", notes = "生成二维吗,直接输出流")
	@RequestMapping(value = "/generateStream", method = RequestMethod.POST)
	public void generateQrCode(GenerateQrCodeDTO dto, HttpServletResponse response) throws IOException {
		qrCodeService.generateQrCode(dto, response.getOutputStream());
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "二维码内容", type = "form", required = true, dataType = "String"),
			@ApiImplicitParam(name = "width", value = "二维码宽", type = "form", required = true, dataType = "int"),
			@ApiImplicitParam(name = "height", value = "二维码高", type = "form", required = true, dataType = "int") })
	@PreAuthorize("hasPermission('/qrCode/qrCode','save')")
	@ApiOperation(value = "生成二维吗", notes = "生成二维吗,反回地地址")
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public String generateQrCode(GenerateQrCodeDTO dto) throws IOException {
		String path = qrCodeService.generateQrCode(dto);
		return path;
	}

	/*
	 * 批量生成码
	 */
	@PreAuthorize("hasPermission('/qrCode/qrCode','save')")
	@ApiOperation(value = "批量生成码", notes = "批量生成码")
	@RequestMapping(value = "/batchGenerateQrCodeAsync", method = RequestMethod.POST)
	public void batchGenerateQrCodeAsync(BatchGenerateQrCodeDTO request){
		qrCodeService.batchGenerateQrCodeAsync(request);
	}

	@PreAuthorize("hasPermission('/qrCode/qrCode','save')")
	@ApiOperation(value = "获取二维码列表", notes = "获取二维码列表")
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public Page<QrCode> getQrCodePage(GetQrCodeListDTO request, Pageable pageable){
		return qrCodeService.getQrCodePage(request,pageable);
	}



}
