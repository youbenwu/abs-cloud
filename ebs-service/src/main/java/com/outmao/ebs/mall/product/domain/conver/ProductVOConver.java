package com.outmao.ebs.mall.product.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.product.entity.QProduct;
import com.outmao.ebs.mall.product.vo.ProductVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ProductVOConver implements BeanConver<QProduct,ProductVO> {


    @Override
    public ProductVO fromTuple(Tuple t, QProduct e) {
        ProductVO vo=new ProductVO();
        vo.setId(t.get(e.id));
        vo.setSubjectId(t.get(e.subjectId));
        vo.setShopId(t.get(e.shopId));
        vo.setHotelId(t.get(e.hotelId));
        vo.setCategoryId(t.get(e.categoryId));
        vo.setSpcId(t.get(e.spcId));
        vo.setBrandId(t.get(e.brandId));
        vo.setCounselorId(t.get(e.counselorId));
        vo.setAddressId(t.get(e.addressId));
        vo.setSalesAddressId(t.get(e.salesAddressId));
        vo.setLocation(t.get(e.location));
        vo.setType(t.get(e.type));
        vo.setQrCode(t.get(e.qrCode));
        vo.setUrl(t.get(e.url));
        vo.setLetter(t.get(e.letter));
        vo.setBarcode(t.get(e.barcode));
        vo.setImage(t.get(e.image));
        vo.setVideo(t.get(e.video));
        vo.setTitle(t.get(e.title));
        vo.setSubtitle(t.get(e.subtitle));
        vo.setDescription(t.get(e.description));
        vo.setDetails(t.get(e.details));
        vo.setMarks(t.get(e.marks));
        vo.setCommissionType(t.get(e.commissionType));
        vo.setCommissionRate(t.get(e.commissionRate));
        vo.setCommissionAmount(t.get(e.commissionAmount));
        vo.setPrice(t.get(e.price));
        vo.setMaxPrice(t.get(e.maxPrice));
        vo.setUnitPrice(t.get(e.unitPrice));
        vo.setMarketPrice(t.get(e.marketPrice));
        vo.setCostPrice(t.get(e.costPrice));
        vo.setStock(t.get(e.stock));
        vo.setAlarmStock(t.get(e.alarmStock));
        vo.setWeight(t.get(e.weight));
        vo.setRoughWeight(t.get(e.roughWeight));
        vo.setVolume(t.get(e.volume));
        vo.setCustom(t.get(e.custom));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setOnSell(t.get(e.onSell));
        vo.setSalesStatus(t.get(e.salesStatus));
        vo.setSales(t.get(e.sales));
        vo.setMarketTime(t.get(e.marketTime));
        vo.setDeliveryTime(t.get(e.deliveryTime));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        vo.setUseStoreStock(t.get(e.useStoreStock));
        return vo;
    }

    @Override
    public Expression<?>[] select(QProduct e) {
        return new Expression[]{
                e.id,
                e.subjectId,
                e.shopId,
                e.hotelId,
                e.categoryId,
                e.spcId,
                e.brandId,
                e.counselorId,
                e.location,
                e.addressId,
                e.salesAddressId,
                e.type,
                e.qrCode,
                e.url,
                e.letter,
                e.barcode,
                e.image,
                e.video,
                e.title,
                e.subtitle,
                e.description,
                e.details,
                e.marks,
                e.commissionType,
                e.commissionRate,
                e.commissionAmount,
                e.price,
                e.maxPrice,
                e.unitPrice,
                e.marketPrice,
                e.costPrice,
                e.stock,
                e.alarmStock,
                e.weight,
                e.roughWeight,
                e.volume,
                e.custom,
                e.status,
                e.statusRemark,
                e.onSell,
                e.salesStatus,
                e.sales,
                e.marketTime,
                e.deliveryTime,
                e.createTime,
                e.updateTime,
                e.useStoreStock
        };
    }
}
