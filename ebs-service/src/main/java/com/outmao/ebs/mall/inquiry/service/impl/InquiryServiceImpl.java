package com.outmao.ebs.mall.inquiry.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.inquiry.domain.InquiryDomain;
import com.outmao.ebs.mall.inquiry.dto.GetInquiryListDTO;
import com.outmao.ebs.mall.inquiry.dto.InquiryDTO;
import com.outmao.ebs.mall.inquiry.dto.SetInquiryStatusDTO;
import com.outmao.ebs.mall.inquiry.entity.Inquiry;
import com.outmao.ebs.mall.inquiry.service.InquiryService;
import com.outmao.ebs.mall.inquiry.vo.StatsInquiryVO;
import com.outmao.ebs.mall.inquiry.vo.InquiryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class InquiryServiceImpl extends BaseService implements InquiryService {


    @Autowired
    private InquiryDomain inquiryDomain;


    @Override
    public Inquiry saveInquiry(InquiryDTO request) {
        return inquiryDomain.saveInquiry(request);
    }

    @Override
    public Inquiry setInquiryStatus(SetInquiryStatusDTO request) {
        return inquiryDomain.setInquiryStatus(request);
    }

    @Override
    public void deleteInquiryById(Long id) {
        inquiryDomain.deleteInquiryById(id);
    }

    @Override
    public Inquiry getInquiryById(Long id) {
        return inquiryDomain.getInquiryById(id);
    }

    @Override
    public Page<Inquiry> getInquiryPage(GetInquiryListDTO request, Pageable pageable) {
        return inquiryDomain.getInquiryPage(request,pageable);
    }


    @Override
    public Page<InquiryVO> getInquiryVOPage(GetInquiryListDTO request, Pageable pageable) {
        return inquiryDomain.getInquiryVOPage(request,pageable);
    }

    @Override
    public List<StatsInquiryVO> getStatsInquiryVOListByUserIdIn(Collection<Long> userIdIn) {
        return inquiryDomain.getStatsInquiryVOListByUserIdIn(userIdIn);
    }

    @Override
    public List<StatsInquiryVO> getStatsInquiryVOListByDays(Date fromTime, Date toTime) {
        return inquiryDomain.getStatsInquiryVOListByDays(fromTime,toTime);
    }
}
