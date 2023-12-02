package com.outmao.ebs.jnet.entity.warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 *
 * 物料单修改记录
 *
 * */
@Entity
@Table(name = "z_MaterialBillRecord")
public class MaterialBillRecord  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     *
     * ID
     *
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "billId")
    private MaterialBill bill;

    @Column(length = 2000)
    private String materials;

    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaterialBill getBill() {
        return bill;
    }

    public void setBill(MaterialBill bill) {
        this.bill = bill;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
