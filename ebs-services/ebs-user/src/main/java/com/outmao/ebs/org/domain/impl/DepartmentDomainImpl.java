package com.outmao.ebs.org.domain.impl;




import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.dao.DepartmentDao;
import com.outmao.ebs.org.dao.DepartmentMemberDao;
import com.outmao.ebs.org.dao.MemberDao;
import com.outmao.ebs.org.dao.OrgDao;
import com.outmao.ebs.org.domain.DepartmentDomain;
import com.outmao.ebs.org.domain.conver.DepartmentMemberVOConver;
import com.outmao.ebs.org.domain.conver.DepartmentVOConver;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Department;
import com.outmao.ebs.org.entity.DepartmentMember;
import com.outmao.ebs.org.entity.QDepartment;
import com.outmao.ebs.org.entity.QDepartmentMember;
import com.outmao.ebs.org.vo.DepartmentMemberVO;
import com.outmao.ebs.org.vo.DepartmentVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DepartmentDomainImpl extends BaseDomain implements DepartmentDomain {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private DepartmentMemberDao departmentMemberDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private MemberDao memberDao;

    private DepartmentMemberVOConver departmentMemberVOConver=new DepartmentMemberVOConver();



    @Transactional
    @Override
    public Department saveDepartment(DepartmentDTO request) {
        Department department= request.getId()==null?null:departmentDao.findByIdForUpdate(request.getId());
        if(department==null){
            department=new Department();
            department.setCreateTime(new Date());
            department.setOrg(orgDao.getOne(request.getOrgId()));
            if(request.getParentId()!=null){
                Department parent=departmentDao.findByIdForUpdate(request.getParentId());
                department.setParent(parent);
                department.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()) {
                    parent.setLeaf(false);
                }
            }
        }
        BeanUtils.copyProperties(request,department);
        department.setUpdateTime(new Date());
        departmentDao.save(department);

        return department;
    }

    @Transactional
    @Override
    public void deleteDepartment(DeleteDepartmentDTO request) {

        Department department=departmentDao.findByIdForUpdate(request.getId());

        if(!department.isLeaf()){
            throw new BusinessException("请先删除下级部门");
        }
        departmentMemberDao.deleteAllByDepartmentId(request.getId());
        departmentDao.delete(department);

    }


    @Override
    public List<DepartmentVO> getDepartmentVOList(GetDepartmentListDTO request) {
        QDepartment e= QDepartment.department;
        List<DepartmentVO> list= queryList(e,e.org.id.eq(request.getOrgId()).and(e.level.eq(0)),e.sort.asc(),new DepartmentVOConver());
        loadDepartmentChildren(list);
        return list;
    }

    private void loadDepartmentChildren(List<DepartmentVO> list){
        if(list.isEmpty())
            return;
        QDepartment e=QDepartment.department;
        DepartmentVOConver conver=new DepartmentVOConver();
        for (DepartmentVO d : list){
            if(!d.isLeaf()){
                List<DepartmentVO> subs=queryList(e,e.parent.id.eq(d.getId()),e.sort.asc(),conver);
                d.setChildren(subs);
                loadDepartmentChildren(subs);
            }
        }
    }


    @Transactional
    @Override
    public DepartmentMember saveDepartmentMember(DepartmentMemberDTO request) {
        DepartmentMember m=departmentMemberDao.findByDepartmentIdAndMemberId(request.getDepartmentId(),request.getMemberId());

        if(m==null){
            m=new DepartmentMember();
            m.setMember(memberDao.getOne(request.getMemberId()));
            m.setCreateTime(new Date());
            departmentMemberDao.save(m);
            departmentDao.membersAdd(request.getDepartmentId(),1);

        }


        return m;
    }

    @Transactional
    @Override
    public List<DepartmentMember> saveDepartmentMemberList(DepartmentMemberListDTO request) {

        List<DepartmentMember> list=new ArrayList<>(request.getMembers().size());

        List<Long> memberIds=departmentMemberDao.findAllMemberIdByDepartmentId(request.getDepartmentId());

        request.getMembers().forEach(memberId->{
            if(!memberIds.contains(memberId)) {
                DepartmentMember m = new DepartmentMember();
                m.setMember(memberDao.getOne(memberId));
                m.setCreateTime(new Date());
                list.add(m);
            }
        });

        departmentMemberDao.saveAll(list);
        departmentDao.membersAdd(request.getDepartmentId(),list.size());

        return list;
    }

    @Transactional
    @Override
    public void deleteDepartmentMember(DeleteDepartmentMemberDTO request) {
        DepartmentMember m=departmentMemberDao.findById(request.getId()).orElse(null);
        if(m==null){
            throw new BusinessException("部门成员不存在");
        }
        departmentMemberDao.delete(m);
        departmentDao.membersAdd(m.getDepartment().getId(),-1);

    }

    @Override
    public Page<DepartmentMemberVO> getDepartmentMemberVOPage(GetDepartmentMemberListDTO request, Pageable pageable) {

        QDepartmentMember e=QDepartmentMember.departmentMember;

        Predicate p=e.department.id.eq(request.getDepartmentId());

        Page<DepartmentMemberVO> page=queryPage(e,p,departmentMemberVOConver,pageable);

        return page;
    }


}
