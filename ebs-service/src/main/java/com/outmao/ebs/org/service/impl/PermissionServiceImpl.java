package com.outmao.ebs.org.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.org.domain.PermissionDomain;
import com.outmao.ebs.org.dto.GetPermissionListDTO;
import com.outmao.ebs.org.dto.PermissionDTO;
import com.outmao.ebs.org.entity.Permission;
import com.outmao.ebs.org.service.PermissionService;
import com.outmao.ebs.org.vo.PermissionVO;
import com.outmao.ebs.sys.domain.SysDomain;
import com.outmao.ebs.sys.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Order(0)
@Service
public class PermissionServiceImpl extends BaseService implements PermissionService , CommandLineRunner {


    @Autowired
    private PermissionDomain permissionDomain;

    @Autowired
    private SysDomain sysDomain;

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void run(String... args) throws Exception {
        loadAnnotationPermissions();
    }

    /**
     *
     * 加载注解的权限并保存到数据库
     *
     * */
    @Transactional
    public void loadAnnotationPermissions() {

        List<AccessPermissionGroup> list=new ArrayList<>();
        Set<Class<?>> types=new HashSet<>();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            HandlerMethod method=map.get(info);
            AccessPermissionGroup group = method.getMethod().getAnnotation(AccessPermissionGroup.class);
            if(group!=null) {
                list.add(group);
            }
            types.add(method.getBeanType());
        }

        for (Class<?> type: types){
            AccessPermissionGroup group = type.getAnnotation(AccessPermissionGroup.class);
            if(group!=null) {
                list.add(group);
            }
        }

        for(AccessPermissionGroup root:list){
            PermissionDTO rootData=new PermissionDTO();
            rootData.setSort(root.sort());
            rootData.setType(root.type());
            rootData.setUrl(root.url());
            rootData.setTitle(root.title());
            rootData.setName(root.name());
            rootData.setDescription(root.description());
            rootData.setSort(root.sort());
            Permission rootPermission=savePermission(rootData);
            for (AccessPermissionParent parent:root.children()){
                PermissionDTO parentData=new PermissionDTO();
                parentData.setSort(parent.sort());
                parentData.setType(parent.type());
                parentData.setUrl(parent.url());
                parentData.setTitle(parent.title());
                parentData.setName(parent.name());
                parentData.setDescription(parent.description());
                parentData.setSort(parent.sort());
                parentData.setParentId(rootPermission.getId());
                Permission parentPermission=savePermission(parentData);
                for (AccessPermission permission:parent.children()){
                    PermissionDTO request=new PermissionDTO();
                    request.setSort(permission.sort());
                    request.setType(permission.type());
                    request.setUrl(permission.url());
                    request.setTitle(permission.title());
                    request.setName(permission.name());
                    request.setDescription(permission.description());
                    request.setSort(permission.sort());
                    request.setParentId(parentPermission.getId());
                    savePermission(request);
                }
            }
        }

    }


    @Override
    public Permission savePermission(PermissionDTO request) {
        return permissionDomain.savePermission(request);
    }

    @Override
    public void deletePermissionById(Long id) {
        permissionDomain.deletePermissionById(id);
    }

    @Override
    public List<PermissionVO> getPermissionVOList(GetPermissionListDTO request) {
        return permissionDomain.getPermissionVOList(request);
    }

    @Override
    public List<PermissionVO> getPermissionVOListBySysId(Long sysId) {
        List<SysPermission> sysPermissions=sysDomain.getSysPermissionListBySysId(sysId);
        if(sysPermissions.isEmpty())
            return new ArrayList<>(0);
        return permissionDomain.getPermissionVOListByIdIn(sysPermissions.stream().map(t->t.getPermissionId()).collect(Collectors.toList()));
    }
}
