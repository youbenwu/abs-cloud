package com.outmao.ebs.org.vo;


import lombok.Data;

@Data
public class JobMemberVO {

    private Long id;
    private Long jobId;
    private Long memberId;
    private MemberVO member;

}
