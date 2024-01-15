package com.outmao.ebs.org.dto;

import lombok.Data;

import java.util.Date;

/**
 *
 * 成员类型
 *
 */
@Data
public class MemberTypeDTO {

    private Long memberId;

    private int type;

    private String name;

}
