package com.outmao.ebs.org.service;


import com.outmao.ebs.org.dto.GetOrgListDTO;
import com.outmao.ebs.org.dto.OrgDTO;
import com.outmao.ebs.org.dto.RegisterOrgDTO;
import com.outmao.ebs.org.dto.SetOrgStatusDTO;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.security.vo.SecurityOrg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

public interface OrgService {


    /*
     *
     * 注册组织信息
     *
     * */
    public Org registerOrg(RegisterOrgDTO request);
    /*
     *
     * 修改组织信息
     *
     * */
    public Org saveOrg(OrgDTO request);

    /*
     *
     * 设置组织状态
     *
     * */
    public Org setOrgStatus(SetOrgStatusDTO request);


    /*
     *
     * 删除组织
     *
     * */
    public void deleteOrgById(Long id);

    /*
     *
     * 获取组织信息
     *
     * */
    public Org getOrgByTargetId(Long targetId);

    /*
     *
     * 获取组织信息
     *
     * */
    public Org getOrgById(Long id);

    /*
     *
     * 获取系统组织信息
     *
     * */
    public Org getOrg();

    public List<Org> getOrgListByIdIn(Collection<Long> idIn);


    public Long getOrgIdByTargetId(Long targetId);


    /*
     *
     * 获取组织信息
     *
     * */
    public OrgVO getOrgVOById(Long id);

    public List<OrgVO> getOrgVOListByIdIn(Collection<Long> idIn);

    /*
     *
     * 获取组织信息列表
     *
     * */
    public Page<OrgVO> getOrgVOPage(GetOrgListDTO request, Pageable pageable);


    public List<SecurityOrg> getSecurityOrgList(Collection<Long> orgIdIn, Long sysId);


}
