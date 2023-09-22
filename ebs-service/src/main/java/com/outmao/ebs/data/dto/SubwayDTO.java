package com.outmao.ebs.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubwayDTO {

    public SubwayDTO(Long id,Long parentId,String name,Double latitude,Double longitude){
       this.id=id;
       this.parentId=parentId;
       this.name=name;
       this.latitude=latitude;
       this.longitude=longitude;
    }

    @ApiModelProperty(name = "id", value = "id")
    private Long id;

    @ApiModelProperty(name = "parentId", value = "上级id")
    private Long parentId;

    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    @ApiModelProperty(name = "country", value = "国家")
    private String country;

    @ApiModelProperty(name = "province", value = "省")
    private String province;

    @ApiModelProperty(name = "city", value = "市")
    private String city;

    @ApiModelProperty(name = "citycode", value = "市编码")
    private String citycode;

    @ApiModelProperty(name = "district", value = "区")
    private String district;

    @ApiModelProperty(name = "township", value = "社区街道")
    private String township;

    @ApiModelProperty(name = "towncode", value = "乡镇街道编码")
    private String towncode;

    @ApiModelProperty(name = "latitude", value = "纬度")
    private Double latitude;// 纬度

    @ApiModelProperty(name = "longitude", value = "经度")
    private Double longitude;

}
