package com.outmao.ebs.mall.product.domain;

import com.outmao.ebs.mall.product.entity.ProductSnapshot;
import com.outmao.ebs.mall.product.vo.ProductVO;

import java.util.Collection;
import java.util.List;

public interface ProductSnapshotDomain {

    public ProductSnapshot saveProductSnapshot(ProductSnapshot snapshot);

    public ProductSnapshot saveProductSnapshot(ProductVO product, Long orderId);

    public List<ProductSnapshot> getProductSnapshotListByIdIn(Collection<Long> idIn);

    public List<ProductVO> getProductSnapshotVOListByIdIn(Collection<Long> idIn);



}
