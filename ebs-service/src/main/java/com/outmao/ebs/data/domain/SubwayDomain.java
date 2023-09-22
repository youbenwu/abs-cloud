package com.outmao.ebs.data.domain;

import com.outmao.ebs.data.dto.SubwayDTO;
import com.outmao.ebs.data.entity.Subway;
import com.outmao.ebs.data.vo.SubwayVO;

import java.util.List;

public interface SubwayDomain {

    public Subway saveSubway(SubwayDTO request);

    public List<SubwayVO> getSubwayVOList();



}
