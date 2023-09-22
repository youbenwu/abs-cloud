package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.Recommend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface RecommendDao extends JpaRepository<Recommend,Long>, QuerydslPredicateExecutor<Recommend> {

    public Recommend findByChannelIdAndTypeAndItemId(Long channelId, int type, Long itemId);

    @Query("select e.item.id from  Recommend e  where e.type=?1 and e.item.type=?2")
    public List<Long> findItemIdAllByTypeAndItemType(int type, String itemType);


}
