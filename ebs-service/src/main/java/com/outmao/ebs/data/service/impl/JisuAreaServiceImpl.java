package com.outmao.ebs.data.service.impl;

import com.outmao.ebs.data.domain.JisuAreaDomain;
import com.outmao.ebs.data.service.JisuAreaService;
import com.outmao.ebs.data.vo.JisuAreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JisuAreaServiceImpl implements JisuAreaService {

    @Autowired
    private JisuAreaDomain jisuAreaDomain;


    @Override
    public List<JisuAreaVO> getJisuAreaVOList() {
        return jisuAreaDomain.getJisuAreaVOList();
    }


}
