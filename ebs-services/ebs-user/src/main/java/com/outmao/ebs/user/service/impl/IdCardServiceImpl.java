package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.IdCardDomain;
import com.outmao.ebs.user.dto.GetIdCardListDTO;
import com.outmao.ebs.user.dto.IdCardDTO;
import com.outmao.ebs.user.dto.SetIdCardStatusDTO;
import com.outmao.ebs.user.entity.IdCard;
import com.outmao.ebs.user.service.IdCardService;
import com.outmao.ebs.user.vo.IdCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class IdCardServiceImpl extends BaseService implements IdCardService {

    @Autowired
    private IdCardDomain idCardDomain;

    @Override
    public IdCard saveIdCard(IdCardDTO request) {
        return idCardDomain.saveIdCard(request);
    }

    @Override
    public IdCard setIdCardStatus(SetIdCardStatusDTO request) {
        return idCardDomain.setIdCardStatus(request);
    }

    @Override
    public IdCard getIdCardByUserId(Long userId) {
        return idCardDomain.getIdCardByUserId(userId);
    }

    @Override
    public IdCardVO getIdCardVOByUserId(Long userId) {
        return idCardDomain.getIdCardVOByUserId(userId);
    }

    @Override
    public Page<IdCardVO> getIdCardVOPage(GetIdCardListDTO request, Pageable pageable) {
        return idCardDomain.getIdCardVOPage(request,pageable);
    }
}
