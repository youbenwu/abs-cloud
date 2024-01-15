package com.outmao.ebs.org.vo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 成员类型
 *
 */
@Data
public class MemberTypeVO {

    private Long id;

    private Long memberId;

    private int type;

    private String name;

    private Date createTime;

}
