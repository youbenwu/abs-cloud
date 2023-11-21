package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.AdvertPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface AdvertPlaceDao extends JpaRepository<AdvertPlace,Long> {

    public List<AdvertPlace> findByAdvertId(Long advertId);

    public AdvertPlace findByAdvertIdAndPlaceId(Long advertId,Long placeId);

    public void deleteAllByAdvertIdAndIdNotIn(Long advertId, Collection<Long> idNotIn);

    @Query("select p.advertId from AdvertPlace p where p.placeId=?1")
    public List<Long> findAllAdvertIdByPlaceId(Long placeId);


}
