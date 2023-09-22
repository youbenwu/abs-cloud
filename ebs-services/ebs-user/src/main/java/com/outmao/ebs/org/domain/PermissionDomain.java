package com.outmao.ebs.org.domain;


import com.outmao.ebs.org.dto.GetPermissionListDTO;
import com.outmao.ebs.org.dto.PermissionDTO;
import com.outmao.ebs.org.entity.Permission;
import com.outmao.ebs.org.vo.PermissionVO;

import java.util.Collection;
import java.util.List;

public interface PermissionDomain {
	
	public Permission savePermission(PermissionDTO request);
	public void deletePermissionById(Long id);

	public Long getIdByUrlAndName(String url, String name);

	public List<PermissionVO> getPermissionVOList(GetPermissionListDTO request);

	public List<PermissionVO> getPermissionVOListByIdIn(Collection<Long> idIn);

	
}
