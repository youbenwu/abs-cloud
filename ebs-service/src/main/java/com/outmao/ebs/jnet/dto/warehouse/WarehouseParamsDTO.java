package com.outmao.ebs.jnet.dto.warehouse;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "WarehouseParamsDTO", description = "保存仓库信息")
public class WarehouseParamsDTO {

    @ApiModelProperty(name = "id", value = "ID、不传为新增")
    private Long id;


    /*
     *
     * 创建用户
     *
     * */
    @ApiModelProperty(name = "userId", value = "创建用户ID")
    private Long userId;

    /*
     *
     * 管理员
     *
     * */
    @ApiModelProperty(name = "managerId", value = "管理员ID")
    private Long managerId;


    @ApiModelProperty(name = "status", value = "0--正常，1--关闭")
    private int status;

    /*
     *
     * 仓库名称
     *
     * */
    @ApiModelProperty(name = "name", value = "仓库名称")
    private String name;


    /*
     *
     * 仓库主题图片
     *
     * */
    @ApiModelProperty(name = "themeImage", value = "仓库主题图片")
    private String themeImage;


    /*
     *
     * 仓库主题背景色
     *
     * */
    @ApiModelProperty(name = "themeColor", value = "仓库主题背景色")
    private String themeColor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }
}
