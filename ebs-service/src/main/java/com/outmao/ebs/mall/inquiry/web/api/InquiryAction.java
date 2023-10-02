package com.outmao.ebs.mall.inquiry.web.api;


import com.outmao.ebs.mall.inquiry.dto.GetInquiryListDTO;
import com.outmao.ebs.mall.inquiry.dto.InquiryDTO;
import com.outmao.ebs.mall.inquiry.entity.Inquiry;
import com.outmao.ebs.mall.inquiry.service.InquiryService;
import com.outmao.ebs.mall.inquiry.vo.InquiryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "mall-inquiry", tags = "电商-询盘")
@RestController
@RequestMapping("/api/mall/inquiry")
public class InquiryAction {


    @Autowired
    private InquiryService inquiryService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存询盘信息", notes = "保存询盘信息")
    @PostMapping("/save")
    public Inquiry saveInquiry(InquiryDTO request) {
        return inquiryService.saveInquiry(request);
    }


    @ApiOperation(value = "获取询盘信息", notes = "获取询盘信息")
    @PostMapping("/get")
    public Inquiry getInquiryById(Long id) {
        return inquiryService.getInquiryById(id);
    }

    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "获取询盘信息列表", notes = "获取询盘信息列表")
    @PostMapping("/page")
    public Page<InquiryVO> getInquiryVOPage(GetInquiryListDTO request, Pageable pageable) {
        return inquiryService.getInquiryVOPage(request,pageable);
    }




}
