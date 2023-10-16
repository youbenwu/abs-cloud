package com.outmao.ebs.mall.product.domain;


import com.outmao.ebs.mall.product.dto.GetHouseListDTO;
import com.outmao.ebs.mall.product.dto.HouseDTO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.vo.HouseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;


public interface HouseDomain {

      public Product saveHouse(HouseDTO request);

      public HouseVO getHouseVOById(Long id);

      public Page<HouseVO> getHouseVOPage(GetHouseListDTO request, Pageable pageable);

      public List<HouseVO> getHouseVOListByIdIn(Collection<Long> idIn);

      public List<HouseVO> getHouseSnapshotVOListByIdIn(Collection<Long> idIn);

}
