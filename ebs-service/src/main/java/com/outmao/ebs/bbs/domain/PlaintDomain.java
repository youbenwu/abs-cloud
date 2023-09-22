package com.outmao.ebs.bbs.domain;


import com.outmao.ebs.bbs.dto.plaint.GetPlaintListDTO;
import com.outmao.ebs.bbs.dto.plaint.PlaintDTO;
import com.outmao.ebs.bbs.entity.Plaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaintDomain {

    //Plaint
    public Plaint savePlaint(PlaintDTO request);
    public Plaint setPlaintStatus(Long id, int status, String statusRemark);
    public Page<Plaint> getPlaintPage(GetPlaintListDTO request, Pageable pageable);


}
