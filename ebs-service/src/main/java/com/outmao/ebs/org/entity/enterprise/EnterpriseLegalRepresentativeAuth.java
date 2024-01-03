package com.outmao.ebs.org.entity.enterprise;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class EnterpriseLegalRepresentativeAuth {


    /**
     *
     * 法人代表人证件类型
     *
     */
    @ApiModelProperty(name = "certificateType", value = "法人代表人证件类型")
    @Column(name = "legalRepresentativeCertificateType")
    private String certificateType;

    /**
     *
     * 法人代表人证件号
     *
     */
    @ApiModelProperty(name = "certificateNumber", value = "法人代表人证件号")
    @Column(name = "legalRepresentativeCertificateNumber")
    private String certificateNumber;

    /**
     *
     * 法定代表人证件电子版
     *
     */
    @ApiModelProperty(name = "certificate", value = "法定代表人证件电子版")
    @Column(name = "legalRepresentativeCertificate")
    private String certificate;



}
