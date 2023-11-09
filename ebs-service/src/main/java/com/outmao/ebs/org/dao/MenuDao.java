package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

public interface MenuDao extends JpaRepository<Menu,Long> {

    public Menu findByPath(String path);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select m from Menu m where m.id=?1")
    public Menu findByIdForUpdate(Long id);

    @Query("select m.id from Menu m where m.path in ?1")
    public List<Long> findAllIdByPathIn(Collection<String> pathIn);

    @Modifying
    @Query("update Menu c set c.sort=?2  where c.id=?1")
    public void setSort(Long id, int sort);


}
