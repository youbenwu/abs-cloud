package com.outmao.ebs.jnet.dao.warehouse;

import com.outmao.ebs.jnet.entity.warehouse.WarehouseSnapshoot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface WarehouseSnapshootDao extends JpaRepository<WarehouseSnapshoot,Long> {


    public WarehouseSnapshoot findByTypeAndWarehouseIdAndCreateTimeAfter(int type, Long warehouseId, Date time);

    public WarehouseSnapshoot findBySubWarehouseIdAndCreateTimeAfter(Long subWarehouseId, Date time);

    public Page<WarehouseSnapshoot> findAllByWarehouseIdAndType(Long warehouseId, int type, Pageable pageable);

    public Page<WarehouseSnapshoot> findAllBySubWarehouseId(Long subWarehouseId, Pageable pageable);

    @Query("select s from WarehouseSnapshoot s where s.type=?1 and s.warehouseId=?2 and s.createTime<?3")
    public List<WarehouseSnapshoot> findAllByTypeAndWarehouseIdAndCreateTimeBefore(int type, Long warehouseId, Date time, Pageable pageable);

    @Query("select s from WarehouseSnapshoot s where s.subWarehouseId=?1 and s.createTime<?2")
    public List<WarehouseSnapshoot> findAllBySubWarehouseIdAndCreateTimeBefore(Long subWarehouseId, Date time, Pageable pageable);

    @Query("select s from WarehouseSnapshoot s where s.warehouseId=?1 and s.type=?2 and s.createTime>?3")
    public List<WarehouseSnapshoot> findAllByWarehouseIdAndTypeAndCreateTimeAfter(Long warehouseId, int type, Date time);

    @Query("select s from WarehouseSnapshoot s where  s.subWarehouseId=?1 and s.createTime>?2")
    public List<WarehouseSnapshoot> findAllBySubWarehouseIdAndTypeAndCreateTimeAfter(Long subWarehouseId, Date time);

}
