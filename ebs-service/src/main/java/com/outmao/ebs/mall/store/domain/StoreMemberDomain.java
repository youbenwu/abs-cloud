package com.outmao.ebs.mall.store.domain;

import com.outmao.ebs.mall.store.dto.GetStoreMemberListDTO;
import com.outmao.ebs.mall.store.dto.StoreMemberDTO;
import com.outmao.ebs.mall.store.entity.StoreMember;
import com.outmao.ebs.mall.store.vo.StoreMemberVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreMemberDomain {


    public StoreMember saveStoreMember(StoreMemberDTO request);

    public void deleteStoreMemberById(Long id);

    public StoreMember getStoreMember(Long storeId, Long userId);

    public Page<StoreMemberVO> getStoreMemberVOPage(GetStoreMemberListDTO request, Pageable pageable);

    

}
