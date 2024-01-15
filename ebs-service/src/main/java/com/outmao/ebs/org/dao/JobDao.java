package com.outmao.ebs.org.dao;



import com.outmao.ebs.org.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobDao extends JpaRepository<Job,Long> {


}
