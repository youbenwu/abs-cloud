package com.outmao.ebs.data.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.data.domain.SubwayDomain;
import com.outmao.ebs.data.dto.SubwayDTO;
import com.outmao.ebs.data.entity.Subway;
import com.outmao.ebs.data.service.SubwayService;
import com.outmao.ebs.data.vo.SubwayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Order(10)
@Service
public class SubwayServiceImpl extends BaseService implements SubwayService, CommandLineRunner {

    @Autowired
    private SubwayDomain subwayDomain;


    @Autowired
    private SubwaySpider subwaySpider;

    @Override
    public void run(String... args) throws Exception {
        //subwaySpider.spider();
    }

    @Override
    public Subway saveSubway(SubwayDTO request) {
        return subwayDomain.saveSubway(request);
    }

    @Override
    public List<SubwayVO> getSubwayVOList() {
        return subwayDomain.getSubwayVOList();
    }

    @Override
    public List<SubwayVO> getSubwayVOListByCity(String city) {
        List<SubwayVO> list=subwayDomain.getSubwayVOList();
        for(SubwayVO vo:list){
            if(vo.getName().contains(city))
                return vo.getChildren();
        }
        return null;
    }


}
