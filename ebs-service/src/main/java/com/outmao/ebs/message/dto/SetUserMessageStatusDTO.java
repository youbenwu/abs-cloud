package com.outmao.ebs.message.dto;


import lombok.Data;

import java.util.List;

@Data
public class SetUserMessageStatusDTO {

    private List<Long> ids;

    private int status;


}
