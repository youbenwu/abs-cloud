package com.outmao.ebs.data.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.data.domain.AreaDomain;
import com.outmao.ebs.data.dto.AreaDTO;
import com.outmao.ebs.data.dto.GetAreaListDTO;
import com.outmao.ebs.data.entity.Area;
import com.outmao.ebs.data.service.AreaService;
import com.outmao.ebs.data.vo.AreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Order(10)
@Service
public class AreaServiceImpl extends BaseService implements AreaService, CommandLineRunner {


    @Autowired
    private AreaDomain areaDomain;

   // @Autowired
    //private AreaSpider areaSpider;


    @Override
    public void run(String... args) throws Exception {
        //areaSpider.spider();
    }

    @Override
    public Area saveArea(AreaDTO request) {
        return areaDomain.saveArea(request);
    }

    @Override
    public void deleteAreaById(Long id) {
        areaDomain.deleteAreaById(id);
    }

    @Override
    public List<Area> getAreaList() {
        return areaDomain.getAreaList();
    }

    @Override
    public List<AreaVO> getAreaVOList() {
        return areaDomain.getAreaVOList();
    }

    @Override
    public List<AreaVO> getProvinceVOList() {
        return areaDomain.getProvinceVOList();
    }

    @Override
    public List<AreaVO> getAreaVOChildren(Long parentId) {
        return areaDomain.getAreaVOChildren(parentId);
    }

    @Override
    public List<AreaVO> getCityVOList() {
        return areaDomain.getCityVOList();
    }


    @Override
    public Page<AreaVO> getAreaVOPage(GetAreaListDTO request, Pageable pageable) {
        return areaDomain.getAreaVOPage(request,pageable);
    }
}
