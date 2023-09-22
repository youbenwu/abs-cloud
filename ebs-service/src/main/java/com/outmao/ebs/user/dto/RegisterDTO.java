package com.outmao.ebs.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO{

    private String oauth;
    private String principal;
    private String credentials;
    private int type;
    private Map<String,Object> args;

    public RegisterDTO(String oauth,String principal,String credentials){
        this.oauth=oauth;
        this.principal=principal;
        this.credentials=credentials;
        type=0;
        args=new HashMap<>();
    }

}
