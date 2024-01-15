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
import java.util.Map;
import java.util.stream.Collectors;

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
        Department department= request.getId()==null?null:departmentDao.findByIdLock(request.getId());
        if(department==null){
            department=new Department();
            department.setCreateTime(new Date());
            department.setOrg(orgDao.getOne(request.getOrgId()));
            if(request.getParentId()!=null){
                Department parent=departmentDao.findByIdLock(request.getParentId());
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

        Department department=departmentDao.findByIdLock(request.getId());

        if(!department.isLeaf()){
            throw new BusinessException("请先删除下级部门");
        }
        departmentMemberDao.deleteAllByDepartmentId(request.getId());
        departmentDao.delete(department);

    }


    @Override
    public List<DepartmentVO> getDepartmentVOList(GetDepartmentListDTO request) {
        QDepartment e= QDepartment.department;
        List<DepartmentVO> list= queryList(e,e.org.id.eq(request.getOrgId()),e.sort.asc(),new DepartmentVOConver());
        return toLevel(list);
    }

    private List<DepartmentVO> toLevel(List<DepartmentVO> all){
        Map<Long,DepartmentVO> map=all.stream().collect(Collectors.toMap(t->t.getId(), t->t));
        List<DepartmentVO> list=new ArrayList<>();
        for(DepartmentVO vo:all){
            if(vo.getParentId()!=null){
                DepartmentVO parent=map.get(vo.getParentId());
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




    @Transactional
    @Override
    public DepartmentMember saveDepartmentMember(DepartmentMemberDTO request) {
        DepartmentMember m=departmentMemberDao.findByDepartmentIdAndMemberId(request.getDepartmentId(),request.getMemberId());

        if(m==null){
            m=new DepartmentMember();
            m.setDepartment(departmentDao.getOne(request.getDepartmentId()));
            m.setMember(memberDao.getOne(request.getMemberId()));
            m.setCreateTime(new Date());
            departmentMemberDao.save(m);
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
                m.setDepartment(departmentDao.getOne(request.getDepartmentId()));
                m.setMember(memberDao.getOne(memberId));
                m.setCreateTime(new Date());
                list.add(m);
            }
        });

        departmentMemberDao.saveAll(list);

        return list;
    }

    @Transactional
    @Override
    public void deleteDepartmentMember(DeleteDepartmentMemberDTO request) {
        departmentMemberDao.deleteById(request.getId());
    }

    @Transactional
    @Override
    public void deleteDepartmentMemberByMemberId(Long memberId) {
        departmentMemberDao.deleteAllByMemberId(memberId);
    }

    @Override
    public Page<DepartmentMemberVO> getDepartmentMemberVOPage(GetDepartmentMemberListDTO request, Pageable pageable) {

        QDepartmentMember e=QDepartmentMember.departmentMember;

        Predicate p=e.department.id.eq(request.getDepartmentId());

        Page<DepartmentMemberVO> page=queryPage(e,p,departmentMemberVOConver,pageable);

        return page;
    }


}
