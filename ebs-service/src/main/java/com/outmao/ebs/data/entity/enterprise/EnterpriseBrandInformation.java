package com.outmao.ebs.data.entity.enterprise;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ebs_EnterpriseBrandInformation")
public class EnterpriseBrandInformation  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "enterpriseId")
    private Enterprise enterprise;

    /**
     *
     * 注册商标
     * 在工商管理部门注册的企业所提供的产品/服务的商标
     * 二进制文件
     *
     * */
    private String registryTradeMark;


    public int hashCode() {
        return (this.getId() == null) ? 0 : this.getId().hashCode();
    }

    public boolean equals(Object object) {
        if (object instanceof EnterpriseBrandInformation) {
            final EnterpriseBrandInformation obj = (EnterpriseBrandInformation) object;
            return (this.getId() != null) ? this.getId().equals(obj.getId()) : (obj.getId() == null);
        }
        return false;
    }


}
