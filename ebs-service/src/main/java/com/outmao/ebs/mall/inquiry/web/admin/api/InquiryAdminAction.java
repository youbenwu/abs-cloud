package com.outmao.ebs.mall.inquiry.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.mall.inquiry.dto.GetInquiryListDTO;
import com.outmao.ebs.mall.inquiry.dto.InquiryDTO;
import com.outmao.ebs.mall.inquiry.dto.SetInquiryStatusDTO;
import com.outmao.ebs.mall.inquiry.entity.Inquiry;
import com.outmao.ebs.mall.inquiry.service.InquiryService;
import com.outmao.ebs.mall.inquiry.vo.StatsInquiryVO;
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
import java.util.Date;
import java.util.List;

@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "询盘管理",url = "/mall/inquiry",name = "",children = {
                @AccessPermission(title = "保存询盘信息",url = "/mall/inquiry",name = "save"),
                @AccessPermission(title = "删除询盘信息",url = "/mall/inquiry",name = "delete"),
                @AccessPermission(title = "读取询盘信息",url = "/mall/inquiry",name = "read"),
                @AccessPermission(title = "设置询盘状态",url = "/mall/inquiry",name = "status"),
        }),
})


@Api(value = "account-mall-inquiry", tags = "后台-电商-询盘")
@RestController
@RequestMapping("/api/admin/mall/inquiry")
public class InquiryAdminAction {


    @Autowired
    private InquiryService inquiryService;



    @PreAuthorize("hasPermission('/mall/inquiry','save')")
    @ApiOperation(value = "保存询盘信息", notes = "保存询盘信息")
    @PostMapping("/save")
    public Inquiry saveInquiry(InquiryDTO request) {
        return inquiryService.saveInquiry(request);
    }

    @PreAuthorize("hasPermission('/mall/inquiry','status')")
    @ApiOperation(value = "设置询盘状态", notes = "设置询盘状态")
    @PostMapping("/setStatus")
    public Inquiry setInquiryStatus(SetInquiryStatusDTO request) {
        return inquiryService.setInquiryStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/inquiry','delete')")
    @ApiOperation(value = "删除询盘", notes = "删除询盘")
    @PostMapping("/delete")
    public void deleteInquiryById(Long id) {
        inquiryService.deleteInquiryById(id);
    }

    @PreAuthorize("hasPermission('/mall/inquiry','read')")
    @ApiOperation(value = "获取询盘信息", notes = "获取询盘信息")
    @PostMapping("/get")
    public Inquiry getInquiryById(Long id) {
        return inquiryService.getInquiryById(id);
    }

    @PreAuthorize("hasPermission('/mall/inquiry','read')")
    @ApiOperation(value = "获取询盘信息列表", notes = "获取询盘信息列表")
    @PostMapping("/page")
    public Page<InquiryVO> getInquiryVOPage(GetInquiryListDTO request, Pageable pageable) {
        return inquiryService.getInquiryVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission('/mall/inquiry','read')")
    @ApiOperation(value = "获取询盘按天统计", notes = "获取询盘按天统计days--统计最近天数")
    @PostMapping("/statsInquiry/days")
    public List<StatsInquiryVO> getDayInquiryCountListVOByDays(Date fromTime, Date toTime) {
        return inquiryService.getStatsInquiryVOListByDays(fromTime,toTime);
    }


}
