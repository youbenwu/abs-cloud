package com.outmao.ebs.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ebs_Subway")
public class Subway  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Subway parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @OrderBy(value = "id ASC")
    private List<Subway> children;

    /**
     * 多级分类中所处的级别，级别从0开始
     * 0--城市 1--线路 2--站点
     *
     */
    private int level;

    /**
     *
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf;

    /**
     *
     * 名称
     *
     */
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

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;



}
