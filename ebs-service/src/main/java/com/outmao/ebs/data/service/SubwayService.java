package com.outmao.ebs.data.service;

import com.outmao.ebs.data.dto.SubwayDTO;
import com.outmao.ebs.data.entity.Subway;
import com.outmao.ebs.data.vo.SubwayVO;

import java.util.List;

public interface SubwayService {

    public Subway saveSubway(SubwayDTO request);


    public List<SubwayVO> getSubwayVOList();


    public List<SubwayVO> getSubwayVOListByCity(String city);


}
