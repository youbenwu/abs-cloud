package com.outmao.ebs.org.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.Contact;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * 联系方式
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_OrgContact")
public class OrgContact extends Contact implements Serializable {

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



    @Override
    public String toString(){
        return super.toString();
    }



}
