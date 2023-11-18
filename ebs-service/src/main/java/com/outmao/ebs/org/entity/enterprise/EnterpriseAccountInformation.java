package com.outmao.ebs.org.entity.enterprise;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ebs_EnterpriseAccountInformation")
public class EnterpriseAccountInformation implements Serializable {

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
     * 帐户名称
     * 企业银行帐户的户名
     *
     * */
    @ApiModelProperty(name = "accountName", value = "帐户名称-企业银行帐户的户名")
    private String accountName;


    /**
     *
     * 银行名称
     * 商务过程中相关企业的开户银行名称
     *
     * */
    @ApiModelProperty(name = "bankName", value = "银行名称-商务过程中相关企业的开户银行名称")
    private String bankName;

    /**
     *
     * 银行帐号
     * 商务过程中相关企业的开户银行帐号
     *
     * */
    @ApiModelProperty(name = "accountNumber", value = "银行帐号-商务过程中相关企业的开户银行帐号")
    private String accountNumber;


    public int hashCode() {
        return (this.getId() == null) ? 0 : this.getId().hashCode();
    }

    public boolean equals(Object object) {
        if (object instanceof EnterpriseAccountInformation) {
            final EnterpriseAccountInformation obj = (EnterpriseAccountInformation) object;
            return (this.getId() != null) ? this.getId().equals(obj.getId()) : (obj.getId() == null);
        }
        return false;
    }



}
