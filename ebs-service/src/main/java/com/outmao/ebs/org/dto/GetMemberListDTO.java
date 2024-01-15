package com.outmao.ebs.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetMemberListDTO {

    private Long orgId;

    private String keyword;

    private List<Long> users;

    private List<Integer> types;

}
