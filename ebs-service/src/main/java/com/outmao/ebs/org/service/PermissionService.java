package com.outmao.ebs.org.service;

import com.outmao.ebs.org.dto.GetPermissionListDTO;
import com.outmao.ebs.org.dto.PermissionDTO;
import com.outmao.ebs.org.entity.Permission;
import com.outmao.ebs.org.vo.PermissionVO;

import java.util.List;

public interface PermissionService {

    public Permission savePermission(PermissionDTO request);
    public void deletePermissionById(Long id);
    public List<PermissionVO> getPermissionVOList(GetPermissionListDTO request);

    public List<PermissionVO> getPermissionVOListBySysId(Long sysId);


}
