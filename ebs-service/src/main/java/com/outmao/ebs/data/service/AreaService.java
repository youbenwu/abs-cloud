package com.outmao.ebs.data.service;

import com.outmao.ebs.data.dto.AreaDTO;
import com.outmao.ebs.data.dto.GetAreaListDTO;
import com.outmao.ebs.data.entity.Area;
import com.outmao.ebs.data.vo.AreaVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaService {

    public Area saveArea(AreaDTO request);

    public void deleteAreaById(Long id);

    public List<Area> getAreaList();

    //获取（国家、省、市）
    public List<AreaVO> getAreaVOList();

    //获取国内（省、市，区）
    public List<AreaVO> getProvinceVOList();

    //获取国内城市列表
    public List<AreaVO> getCityVOList();

    //获取子一级地区列表
    public List<AreaVO> getAreaVOChildren(Long parentId);

    public Page<AreaVO> getAreaVOPage(GetAreaListDTO request, Pageable pageable);


}
