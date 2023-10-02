package com.outmao.ebs.mall.inquiry.domain;

import com.outmao.ebs.mall.inquiry.dto.GetInquiryListDTO;
import com.outmao.ebs.mall.inquiry.dto.InquiryDTO;
import com.outmao.ebs.mall.inquiry.dto.SetInquiryStatusDTO;
import com.outmao.ebs.mall.inquiry.entity.Inquiry;
import com.outmao.ebs.mall.inquiry.vo.StatsInquiryVO;
import com.outmao.ebs.mall.inquiry.vo.InquiryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface InquiryDomain {



    public Inquiry saveInquiry(InquiryDTO request);

    public Inquiry setInquiryStatus(SetInquiryStatusDTO request);

    public void deleteInquiryById(Long id);

    public Inquiry getInquiryById(Long id);

    public long getInquiryCount();

    public Page<Inquiry> getInquiryPage(GetInquiryListDTO request, Pageable pageable);

    public Page<InquiryVO> getInquiryVOPage(GetInquiryListDTO request, Pageable pageable);


    public List<StatsInquiryVO> getStatsInquiryVOListByUserIdIn(Collection<Long> userIdIn);

    public List<StatsInquiryVO> getStatsInquiryVOListByDays(Date fromTime, Date toTime);


}
