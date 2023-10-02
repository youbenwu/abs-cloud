package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.vo.IItem;
import com.outmao.ebs.common.vo.IItem;
import com.outmao.ebs.mall.store.common.data.SimpleStoreSetter;
import com.outmao.ebs.mall.store.vo.SimpleStoreVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@ApiModel(value = "SimpleMerchantBrokerVO", description = "商家员工信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SimpleMerchantBrokerVO implements IItem, SimpleStoreSetter {


    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "storeId", value = "门店ID")
    private Long storeId;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     *
     * 手机号
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;


    @ApiModelProperty(name = "merchantName", value = "商家名称")
    private String merchantName;

    @ApiModelProperty(name = "storeName", value = "门店名称")
    private String storeName;


    @Override
    public void setStore(SimpleStoreVO store) {
        storeName=store.getTitle();
    }
}
