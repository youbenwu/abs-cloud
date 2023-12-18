package com.outmao.ebs.data.dto;

import lombok.Data;

@Data
public class GetPhotoListDTO  {

    private Long userId;

    private Long targetId;

    private String targetType;



}
