package com.outmao.ebs.data.dto;

import lombok.Data;

@Data
public class BrandDTO {

    private Long id;
    private Long userId;
    private int                   type;// 类型 0: Inland在中国申请或注册； 1: Foreign在国外申请或注册
    private String                name;// 品牌名 中文名和英文名至少填写一个
    private String                nameCn;// 品牌名-中文名
    private String                nameEn;// 品牌名-英文名
    private String                image;// 品牌图片
    private String                birthplace;// 品牌发源地/国家名称
    private String                owner;// 品牌所有人
    private String                trademark;// 商标注册号/申请号
    private String                license;// 商标注册证/受理通知书正面和背面,逗号隔开
    private String                entryBill;// 报关单
    private String                packaging;// 商品外包装图片,逗号隔开

}
