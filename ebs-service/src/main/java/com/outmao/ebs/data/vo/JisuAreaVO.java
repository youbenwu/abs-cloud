package com.outmao.ebs.data.vo;

import lombok.Data;

import java.util.List;


/**
 *
 * zipcode	string	邮编
 * parentid	int	上级ID
 * name	string	名称
 * id	int	ID
 * depth	string	区域等级(深度) 冗余字段，用来查找
 * areacode	string	区号
 *
 * */

@Data
public class JisuAreaVO {


    private Long id;

    private Long parentid;

    private List<JisuAreaVO> children;

    private String zipcode;

    private String name;

    private String depth;

    private String areacode;



}
