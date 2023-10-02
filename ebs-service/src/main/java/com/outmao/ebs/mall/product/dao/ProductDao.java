package com.outmao.ebs.mall.product.dao;


import com.outmao.ebs.mall.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
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


    public List<Product> findAllByIdIn(Collection<Long> idIn);


}
