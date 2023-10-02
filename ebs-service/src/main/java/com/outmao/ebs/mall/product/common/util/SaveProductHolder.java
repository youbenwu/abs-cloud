package com.outmao.ebs.mall.product.common.util;

import com.outmao.ebs.mall.product.dto.ProductDTO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductProperty;
import com.outmao.ebs.mall.product.entity.ProductPropertyItem;

import java.util.List;
import java.util.Map;

public class SaveProductHolder {
    public ProductDTO request;
    public Product product;
    public List<ProductProperty> properties;
    public Map<Long,List<ProductPropertyItem>> propertyItemsMap;
}
