package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.Plaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaintDao extends JpaRepository<Plaint,Long> {

    public Page<Plaint> findAllByStatusIn(int[] statusIn, Pageable pageable);


}
