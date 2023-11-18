package com.outmao.ebs.org.entity.enterprise;


import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class EnterpriseCreditInformation {


    /**
     *
     * 信用等级
     * 信用评定机构有既定的符号来标识企业未来偿还债务能力及偿债意愿可能性的级别结果
     *
     * */
    private String creditRating;

    /**
     *
     * 信用评定机构
     * 对企业进行信用评定等级的组织
     *
     * */
    private String creditRatingAgency;










}
