package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.enterprise.QEnterprise;
import com.outmao.ebs.org.vo.EnterpriseVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class EnterpriseVOConver implements BeanConver<QEnterprise, EnterpriseVO> {

    @Override
    public EnterpriseVO fromTuple(Tuple t, QEnterprise e) {
        EnterpriseVO vo=new EnterpriseVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setEnterpriseName(t.get(e.enterpriseName));
        vo.setOrganizationCode(t.get(e.organizationCode));
        vo.setIndustryAndCommerceRegistryNo(t.get(e.industryAndCommerceRegistryNo));
        vo.setRegistryAuthority(t.get(e.registryAuthority));
        vo.setLegalRepresentative(t.get(e.legalRepresentative));


        vo.setLegalRepresentativeCertificate(t.get(e.legalRepresentativeCertificate));
        vo.setLegalRepresentativeCertificateType(t.get(e.legalRepresentativeCertificateType));
        vo.setLegalRepresentativeCertificateNumber(t.get(e.legalRepresentativeCertificateNumber));
        vo.setRegisteredCapital(t.get(e.registeredCapital));


        vo.setEnterpriseType(t.get(e.enterpriseType));
        vo.setEnterpriseIntroduction(t.get(e.enterpriseIntroduction));
        vo.setWebSite(t.get(e.webSite));
        vo.setContactInformation(t.get(e.contactInformation));
        vo.setTaxInformation(t.get(e.taxInformation));

        vo.setRegTime(t.get(e.regTime));
        vo.setBusinessScope(t.get(e.businessScope));
        vo.setBusinessLicense(t.get(e.businessLicense));
        vo.setAdministrativeLicense(t.get(e.administrativeLicense));


        return vo;
    }

    @Override
    public Expression<?>[] select(QEnterprise e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.status,
                e.statusRemark,
                e.enterpriseName,
                e.organizationCode,
                e.industryAndCommerceRegistryNo,
                e.registryAuthority,
                e.legalRepresentative,

                e.legalRepresentativeCertificate,
                e.legalRepresentativeCertificateType,
                e.legalRepresentativeCertificateNumber,
                e.registeredCapital,

                e.enterpriseType,
                e.enterpriseIntroduction,
                e.webSite,
                e.contactInformation,
                e.taxInformation,

                e.regTime,
                e.businessScope,
                e.businessLicense,
                e.administrativeLicense,

        };
    }
}
