package com.outmao.ebs.mall.merchant.entity;


import com.outmao.ebs.common.vo.Contact;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * 联系方式
 *
 */
@Data
@Entity
@Table(name = "ebs_MerchantContact")
public class MerchantContact extends Contact implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 自动编号
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}
