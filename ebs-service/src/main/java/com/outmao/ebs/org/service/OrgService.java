package com.outmao.ebs.org.service;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.entity.OrgParent;
import com.outmao.ebs.org.entity.OrgType;
import com.outmao.ebs.org.vo.CacheOrgVO;
import com.outmao.ebs.org.vo.OrgTypeVO;
import com.outmao.ebs.org.vo.OrgVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface OrgService {


    /**
     *
     * 注册组织信息
     *
     * */
    public Org registerOrg(RegisterOrgDTO request);

    /**
     *
     * 修改组织信息
     *
     * */
    public Org saveOrg(OrgDTO request);

    /**
     *
     * 设置组织状态
     *
     * */
    public Org setOrgStatus(SetOrgStatusDTO request);

    /**
     *
     * 删除组织
     *
     * */
    public void deleteOrgById(Long id);

    /**
     *
     * 增加父组织
     *
     * */
    public OrgParent saveOrgParent(OrgParentDTO request);

    /**
     *
     * 获取组织信息
     *
     * */
    public Org getOrgById(Long id);

    /**
     *
     * 获取组织信息
     *
     * */
    public Org getOrgByTargetId(Long targetId);

    /**
     *
     * 获取组织信息
     *
     * */
    public OrgVO getOrgVOById(Long id);

    /**
     *
     * 获取组织信息列表
     *
     * */
    public Page<OrgVO> getOrgVOPage(GetOrgListDTO request, Pageable pageable);

    /**
     *
     * 获取组织信息列表
     *
     * */
    public List<OrgVO> getOrgVOListByIdIn(Collection<Long> idIn);


    /**
     *
     * 获取组织
     *
     * */
    public CacheOrgVO getCacheOrgVOById(Long id);

    /**
     *
     * 获取根组织
     *
     * */
    public CacheOrgVO getCacheOrgVO();


    public OrgType saveOrgType(OrgTypeDTO request);

    public void deleteOrgTypeById(Long id);

    public void deleteOrgTypeByOrgId(Long orgId);

    public OrgType getOrgTypeByTargetId(Long targetId);

    public List<OrgTypeVO> getOrgTypeVOListByOrgIdIn(Collection<Long> orgIdIn);


}
