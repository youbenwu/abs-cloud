package com.outmao.ebs.mall.product.dao;


import com.outmao.ebs.common.vo.TimeSpan;
import com.outmao.ebs.mall.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

public interface ProductDao extends JpaRepository<Product,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select p from Product p where p.id=?1")
    public Product findByIdForUpdate(Long id);

    @Query("select s.shopId from Product s where s.id=?1")
    public Long findShopIdById(Long id);

    @Modifying
    @Query("update Product a set a.sales=a.sales+?2 where a.id=?1")
    public void salesAdd(Long id,int q);


    public List<Product> findAllByIdIn(Collection<Long> idIn);

    @Query("select p.expectDeliveryTimeSpan from Product p where p.expectDeliveryTimeSpan is not null and p.id in ?1")
    public List<TimeSpan> findAllExpectDeliveryTimeSpanByIdIn(Collection<Long> idIn);


}
