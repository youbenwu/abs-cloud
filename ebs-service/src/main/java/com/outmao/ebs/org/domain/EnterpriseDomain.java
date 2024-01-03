package com.outmao.ebs.org.domain;


import com.outmao.ebs.org.dto.EnterpriseDTO;
import com.outmao.ebs.data.dto.GetEnterpriseListDTO;
import com.outmao.ebs.org.entity.enterprise.Enterprise;
import com.outmao.ebs.org.vo.EnterpriseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EnterpriseDomain {


    public Enterprise saveEnterprise(EnterpriseDTO request);
    public void deleteEnterpriseById(Long id);
    public Enterprise setEnterpriseStatus(Long id, int status, String statusRemark);
    public Enterprise getEnterpriseById(Long id);
    public List<Enterprise> getEnterpriseListByUserId(Long userId);
    public Page<Enterprise> getEnterprisePage(GetEnterpriseListDTO request, Pageable pageable);


    public EnterpriseVO getEnterpriseVOById(Long id);
    public List<EnterpriseVO> getEnterpriseVOListByUserId(Long userId);
    public Page<EnterpriseVO> getEnterpriseVOPage(GetEnterpriseListDTO request, Pageable pageable);

}
