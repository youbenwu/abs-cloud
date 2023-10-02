package com.outmao.ebs.mall.product.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.product.domain.ProductSnapshotDomain;
import com.outmao.ebs.mall.product.entity.ProductSnapshot;
import com.outmao.ebs.mall.product.service.ProductSnapshotService;
import com.outmao.ebs.mall.product.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class ProductSnapshotServiceImpl extends BaseService implements ProductSnapshotService {


    @Autowired
    private ProductSnapshotDomain productSnapshotDomain;

    @Override
    public ProductSnapshot saveProductSnapshot(ProductSnapshot snapshot) {
        return productSnapshotDomain.saveProductSnapshot(snapshot);
    }

    @Override
    public ProductSnapshot saveProductSnapshot(ProductVO product, Long orderId) {
        return productSnapshotDomain.saveProductSnapshot(product,orderId);
    }

    @Override
    public List<ProductSnapshot> getProductSnapshotListByIdIn(Collection<Long> idIn) {
        return productSnapshotDomain.getProductSnapshotListByIdIn(idIn);
    }

    @Override
    public List<ProductVO> getProductSnapshotVOListByIdIn(Collection<Long> idIn) {
        return productSnapshotDomain.getProductSnapshotVOListByIdIn(idIn);
    }
}
