package com.outmao.ebs.org.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.dao.PermissionDao;
import com.outmao.ebs.org.dao.RolePermissionDao;
import com.outmao.ebs.org.domain.PermissionDomain;
import com.outmao.ebs.org.domain.conver.PermissionVOConver;
import com.outmao.ebs.org.dto.GetPermissionListDTO;
import com.outmao.ebs.org.dto.PermissionDTO;
import com.outmao.ebs.org.entity.Permission;
import com.outmao.ebs.org.entity.QPermission;
import com.outmao.ebs.org.vo.PermissionVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PermissionDomainImpl extends BaseDomain implements PermissionDomain {

	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;

	private PermissionVOConver permissionVOConver=new PermissionVOConver();


	@Transactional
	@Override
	public Permission savePermission(PermissionDTO request) {
		Permission p=request.getId()==null?null:permissionDao.findByIdForUpdate(request.getId());
		if(p==null){
           p=permissionDao.findByUrlAndNameForUpdate(request.getUrl(),request.getName());
		}
		if(p==null){
			p=new Permission();
			p.setCreateTime(new Date());
			if(request.getParentId()!=null){
                Permission parent=permissionDao.findByIdForUpdate(request.getParentId());
				p.setParent(parent);
				p.setLevel(parent.getLevel()+1);
				if(parent.isLeaf()){
					parent.setLeaf(false);
				}
			}
		}

		BeanUtils.copyProperties(request,p,"id");
		p.setUpdateTime(new Date());

		permissionDao.save(p);
		return p;
	}



	@Transactional
	@Override
	public void deletePermissionById(Long id) {
		Permission p=permissionDao.findByIdForUpdate(id);
		if(!p.isLeaf()){
			throw new BusinessException("请先删除下级权限");
		}

		if(p.getParent()!=null){
			Permission parent=permissionDao.findByIdForUpdate(p.getParent().getId());
			if(parent.getChildren().size()==1){
				parent.setLeaf(true);
			}
		}

		//先删除角色关联
		rolePermissionDao.deleteAllByPermissionId(id);
		//删除
		permissionDao.delete(p);
	}


	@Override
	public List<PermissionVO> getPermissionVOList(GetPermissionListDTO request) {
		List<PermissionVO> list=getAll(request.getType());
		list=toLevel(list);
		return list;
	}

	@Override
	public List<PermissionVO> getPermissionVOListByIdIn(Collection<Long> idIn) {
		List<PermissionVO> list=getAllByIdIn(idIn);
		list=toLevel(list);
		return list;
	}

	private List<PermissionVO> getAllByIdIn(Collection<Long> idIn){
		QPermission e= QPermission.permission;
		List<PermissionVO> list=queryList(e,e.id.in(idIn),permissionVOConver);
		return list;
	}

	private List<PermissionVO> getAll(Integer type){
		QPermission e= QPermission.permission;
		Predicate p=null;
		if(type!=null){
			p=e.type.eq(type).and(p);
		}
		List<PermissionVO> list=queryList(e,p,permissionVOConver);
		return list;
	}

	protected List<PermissionVO> toLevel(List<PermissionVO> vos){
		Map<Long,PermissionVO> voMap=vos.stream().collect(Collectors.toMap(t->t.getId(), t->t));

		List<PermissionVO> list=new ArrayList<>();

		for(PermissionVO vo:vos){
			PermissionVO parent=vo.getParentId()==null?null:voMap.get(vo.getParentId());
			if(parent!=null){
				if(parent.getChildren()==null){
					parent.setChildren(new ArrayList<>());
				}
				parent.getChildren().add(vo);
			}else{
				list.add(vo);
			}
		}

		return list;
	}

	@Override
	public Long getIdByUrlAndName(String url, String name) {
		return permissionDao.findIdByUrlAndName(url,name);
	}

}
