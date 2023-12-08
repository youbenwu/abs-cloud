package com.outmao.ebs.data.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.data.domain.AreaDomain;
import com.outmao.ebs.data.dto.AreaDTO;
import com.outmao.ebs.data.dto.GetAreaListDTO;
import com.outmao.ebs.data.entity.Area;
import com.outmao.ebs.data.service.AreaService;
import com.outmao.ebs.data.service.JisuAreaService;
import com.outmao.ebs.data.vo.AreaVO;
import com.outmao.ebs.data.vo.JisuAreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Order(10)
@Service
public class AreaServiceImpl extends BaseService implements AreaService, CommandLineRunner {


    @Autowired
    private AreaDomain areaDomain;

   // @Autowired
    //private AreaSpider areaSpider;

    @Autowired
    private JisuAreaService jisuAreaService;


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //areaSpider.spider();
        if(areaDomain.getAreaCount()==0){

            AreaDTO areaDTO=new AreaDTO();
            areaDTO.setName("中国");
            Area country=saveArea(areaDTO);

            List<JisuAreaVO> list=jisuAreaService.getJisuAreaVOList();
            saveFromJisu(list,country);
        }
    }

    private void saveFromJisu(List<JisuAreaVO> list,Area parent){

        //北京 天津 上海 重庆
        List<String> ZX= ArrayUtil.arrayToList(new String[]{"北京","天津","上海","重庆"});


        list.forEach(t->{
            if(t.getName().equals("国外"))
                return;
            AreaDTO areaDTO=new AreaDTO();
            areaDTO.setName(t.getName());
            areaDTO.setZipCode(t.getZipcode());
            areaDTO.setAreaCode(t.getAreacode());
            areaDTO.setType(parent==null?1:(parent.getType()+1));
            areaDTO.setParentId(parent==null?null:parent.getId());
            Area area=saveArea(areaDTO);
            if(t.getChildren()!=null&&t.getChildren().size()>0){
                saveFromJisu(t.getChildren(),area);
            }
        });

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
