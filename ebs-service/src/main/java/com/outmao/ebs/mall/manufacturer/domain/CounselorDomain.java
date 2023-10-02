package com.outmao.ebs.mall.manufacturer.domain;

import com.outmao.ebs.mall.manufacturer.dto.CounselorDTO;
import com.outmao.ebs.mall.manufacturer.dto.GetCounselorDTO;
import com.outmao.ebs.mall.manufacturer.entity.Counselor;
import com.outmao.ebs.mall.manufacturer.vo.CounselorVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounselorDomain {

    public Counselor saveCounselor(CounselorDTO request);

    public void deleteCounselorById(Long id);


    public CounselorVO getCounselorVOById(Long id);


    public Page<CounselorVO> getCounselorVOPage(GetCounselorDTO request, Pageable pageable);


}
