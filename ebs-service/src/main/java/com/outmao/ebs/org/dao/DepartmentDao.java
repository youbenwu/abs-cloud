package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface DepartmentDao extends JpaRepository<Department,Long> {


    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select d from Department d where d.id=?1")
    public Department findByIdForUpdate(Long id);

    @Modifying
    @Query("update Department e set e.members=e.members+?2 where e.id=?1")
    public void membersAdd(Long id, int add);

}
