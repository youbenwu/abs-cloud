package com.outmao.ebs.mall.distribution.vo;

import com.outmao.ebs.mall.distribution.entity.QyDistributionConfigLevel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * 迁眼平台分销设置
 *
 * */
@Data
public class QyDistributionConfigVO implements Serializable {



    /**
     * 自动编号
     */
    private Long id;

    /**
     * 0--启用 1--禁用
     */
    private int status;

    //4个等级
    //设备数量
    private QyDistributionConfigLevel levelA;


    private QyDistributionConfigLevel levelB;


    private QyDistributionConfigLevel levelC;


    private QyDistributionConfigLevel levelD;


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


}
