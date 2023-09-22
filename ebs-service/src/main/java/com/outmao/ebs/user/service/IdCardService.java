package com.outmao.ebs.user.service;

import com.outmao.ebs.user.dto.GetIdCardListDTO;
import com.outmao.ebs.user.dto.IdCardDTO;
import com.outmao.ebs.user.dto.SetIdCardStatusDTO;
import com.outmao.ebs.user.entity.IdCard;
import com.outmao.ebs.user.vo.IdCardVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IdCardService {

    public IdCard saveIdCard(IdCardDTO request);

    public IdCard setIdCardStatus(SetIdCardStatusDTO request);

    public IdCard getIdCardByUserId(Long userId);

    public IdCardVO getIdCardVOByUserId(Long userId);

    public Page<IdCardVO> getIdCardVOPage(GetIdCardListDTO request, Pageable pageable);


}
