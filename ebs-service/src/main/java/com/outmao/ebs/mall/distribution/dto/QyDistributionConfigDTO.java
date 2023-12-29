package com.outmao.ebs.mall.distribution.dto;

import com.outmao.ebs.mall.distribution.entity.QyDistributionConfigLevel;
import lombok.Data;


/**
 *
 * 迁眼平台分销设置
 *
 * */
@Data
public class QyDistributionConfigDTO {


    private Long id;

    /**
     * 0--启用 1--禁用
     */
    private int status;


    private QyDistributionConfigLevel levelA;


    private QyDistributionConfigLevel levelB;


    private QyDistributionConfigLevel levelC;


    private QyDistributionConfigLevel levelD;



}
