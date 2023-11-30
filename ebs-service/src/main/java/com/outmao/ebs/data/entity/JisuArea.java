package com.outmao.ebs.data.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


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
@Entity
@Table(name = "area")
public class JisuArea  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long parentid;

    private String zipcode;

    private String name;

    private String depth;

    private String areacode;



}
