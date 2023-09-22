package com.outmao.ebs.org.dto;


import lombok.Data;

import java.util.List;

@Data
public class JobMemberListDTO {

    private Long jobId;

    private List<Long> members;

}
