package com.outmao.ebs.clients.shpanhe.vo;


import lombok.Data;

@Data
public class SearchScenicListRequest {

    private String keyWord;

    private int pageIndex;

    private int pageSize;

    private String cityName;

    private String provinceName;

    private String country;

    private  String themeGroup;

    private String star;

    private double longitude;

    private double latitude;

    private  int distanceRange;

    private int sort;

    private int isExistAvailableSaleProduct;



}
