package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;

public interface DepartmentDao extends JpaRepository<Department,Long> {


    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select d from Department d where d.id=?1")
    public Department findByIdLock(Long id);



}
