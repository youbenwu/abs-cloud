package com.outmao.ebs.mall.product.dao;


import com.outmao.ebs.mall.product.entity.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

public interface ProductSkuDao extends JpaRepository<ProductSku,Long> {


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select o from ProductSku o where o.id=?1")
    public ProductSku findByIdForUpdate(Long id);


    public List<ProductSku> findAllByProductId(Long productId);

    public void deleteAllByProductId(Long productId);
    public void deleteAllByProductIdIn(Collection<Long> productIdIn);

    public void deleteAllByProductIdAndIdNotIn(Long productId, Collection<Long> idNotIn);


    public List<ProductSku> findAllByIdIn(Collection<Long> idIn);

    public ProductSku findByProductIdAndSkuNo(Long productId,String skuNo);

}
