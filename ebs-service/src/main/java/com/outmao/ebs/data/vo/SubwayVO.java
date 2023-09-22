package com.outmao.ebs.data.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SubwayVO {

    @ApiModelProperty(name = "id", value = "id")
    private Long id;

    private Long parentId;

    private List<SubwayVO> children;

    /**
     * 多级分类中所处的级别，级别从0开始
     *
     */
    private int level;

    /**
     *
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf;

    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    @ApiModelProperty(name = "country", value = "国家")
    private String country;

    @ApiModelProperty(name = "province", value = "省")
    private String province;

    @ApiModelProperty(name = "city", value = "市")
    private String city;

    @ApiModelProperty(name = "cityCode", value = "市编码")
    private String cityCode;

    @ApiModelProperty(name = "district", value = "区")
    private String district;

    @ApiModelProperty(name = "township", value = "社区街道")
    private String township;

    @ApiModelProperty(name = "townCode", value = "乡镇街道编码")
    private String townCode;


    @ApiModelProperty(name = "latitude", value = "纬度")
    private Double latitude;// 纬度

    @ApiModelProperty(name = "longitude", value = "经度")
    private Double longitude;

}
