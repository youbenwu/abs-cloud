package com.outmao.ebs.user.domain;

import com.outmao.ebs.user.dto.GetUserCertificationListDTO;
import com.outmao.ebs.user.dto.SetUserCertificationStatusDTO;
import com.outmao.ebs.user.dto.UserCertificationDTO;
import com.outmao.ebs.user.entity.UserCertification;
import com.outmao.ebs.user.vo.UserCertificationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCertificationDomain {


    public UserCertification saveUserCertification(UserCertificationDTO request);

    public void deleteUserCertificationById(Long id);

    public UserCertification setUserCertificationStatus(SetUserCertificationStatusDTO request);

    public UserCertification getUserCertificationById(Long id);

    public UserCertificationVO getUserCertificationVOByUserId(Long userId);

    public Page<UserCertificationVO> getUserCertificationVOPage(GetUserCertificationListDTO request, Pageable pageable);


}
