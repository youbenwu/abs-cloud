package com.outmao.ebs.mall.product.web.api;


import com.outmao.ebs.mall.product.service.ProductSnapshotService;
import com.outmao.ebs.mall.product.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@Api(value = "mall-product-snapshot", tags = "电商-商品-快照")
@RestController
@RequestMapping("/api/mall/product/snapshot")
public class ProductSnapshotAction {

    @Autowired
    private ProductSnapshotService productSnapshotService;

    @ApiOperation(value = "获取商品快照列表", notes = "获取商品快照列表ids--快照ID列表")
    @PostMapping("/list")
    public List<ProductVO> getProductSnapshotVOListByIdIn(@RequestBody Collection<Long> ids) {
        return productSnapshotService.getProductSnapshotVOListByIdIn(ids);
    }



}
