package com.outmao.ebs.mall.product.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.mall.product.dao.ProductSnapshotDao;
import com.outmao.ebs.mall.product.domain.ProductSnapshotDomain;
import com.outmao.ebs.mall.product.entity.ProductSnapshot;
import com.outmao.ebs.mall.product.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class ProductSnapshotDomainImpl extends BaseDomain implements ProductSnapshotDomain {

    @Autowired
    private ProductSnapshotDao productSnapshotDao;


    @Transactional
    @Override
    public ProductSnapshot saveProductSnapshot(ProductSnapshot snapshot) {
        snapshot.setCreateTime(new Date());
        productSnapshotDao.save(snapshot);
        return snapshot;
    }


    @Transactional
    @Override
    public ProductSnapshot saveProductSnapshot(ProductVO product, Long orderId) {
        String json= JsonUtil.toJson(product);
        ProductSnapshot snapshot=new ProductSnapshot();
        snapshot.setOrderId(orderId);
        snapshot.setProductId(product.getId());
        snapshot.setData(json);
        return saveProductSnapshot(snapshot);
    }

    @Override
    public List<ProductSnapshot> getProductSnapshotListByIdIn(Collection<Long> idIn) {
        return productSnapshotDao.findAllByIdIn(idIn);
    }



    @Override
    public List<ProductVO> getProductSnapshotVOListByIdIn(Collection<Long> idIn) {

        List<ProductSnapshot> list=getProductSnapshotListByIdIn(idIn);

        List<ProductVO> vos=new ArrayList<>(list.size());

        list.forEach(t->{
            ProductVO vo=JsonUtil.fromJson(t.getData(),ProductVO.class);
            vo.setSnapshotId(t.getId());
            vos.add(vo);
        });

        return vos;
    }


}
