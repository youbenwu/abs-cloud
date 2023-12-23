package com.outmao.ebs.thirdpartys.rongcloud.service.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.thirdpartys.rongcloud.dao.RcGroupDao;
import com.outmao.ebs.thirdpartys.rongcloud.dto.RcGroupDTO;
import com.outmao.ebs.thirdpartys.rongcloud.dto.RcRegisterUserDTO;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcGroup;
import com.outmao.ebs.thirdpartys.rongcloud.service.RongcloudService;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Slf4j
@Service
public class RongcloudServiceImpl implements RongcloudService {

    @Autowired
    private RongCloud rongCloud;

    @Autowired
    private RcGroupDao rcGroupDao;


    @Override
    public Token registerUser(RcRegisterUserDTO request) {
        User user = rongCloud.user;
        try {
            TokenResult result=user.register(request);
            if(result.code==200){
                Token token=new Token();
                BeanUtils.copyProperties(result,token);

                //加入群组
                RcGroupDTO groupDTO=new RcGroupDTO();
                groupDTO.setId(request.getGroupId());
                groupDTO.setName(request.getGroupName());
                GroupMember member=new GroupMember();
                member.setId(request.getId());
                groupDTO.setMembers(new GroupMember[]{member});
                saveGroup(groupDTO);


                return token;
            }else{
                log.error("注册融云用户出错",result.errorMessage);
                throw new BusinessException("注册融云用户出错");
            }
        }catch (Exception e){
            log.error("注册融云用户出错",e);
            throw new BusinessException("注册融云用户出错");
        }

    }


    @Transactional
    @Override
    public RcGroup saveGroup(RcGroupDTO request) {

        RcGroup group=rcGroupDao.findById(request.getId()).orElse(null);

        if(group==null){
            group=new RcGroup();
            group.setCreateTime(new Date());
            try{
                Result result= rongCloud.group.create(request);
                if(result.getCode()!=200){
                    log.error("融云创建群组出错:{}",result.getErrorMessage());
                    throw new BusinessException("融云创建群组出错");
                }
            }catch (Exception e){
                log.error("融云创建群组出错",e);
                throw new BusinessException("融云创建群组出错");
            }
        }

        BeanUtils.copyProperties(request,group);

        group.setUpdateTime(new Date());

        rcGroupDao.save(group);

        return group;
    }





}
