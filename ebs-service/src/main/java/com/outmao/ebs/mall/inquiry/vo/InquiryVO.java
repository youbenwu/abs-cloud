package com.outmao.ebs.mall.inquiry.vo;

import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import lombok.Data;

import java.util.Date;


@Data
public class InquiryVO  implements SimpleUserSetter {


    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 商家ID
     *
     */
    private Long merchantId;

    /**
     *
     * 状态 0--未回访 1--已回访
     *
     */
    private int status;

    /**
     *
     * 状态备注
     *
     */
    private String statusRemark;

    /**
     *
     * ID
     *
     */
    private Long userId;


    private SimpleUserVO user;

    /**
     *
     * 商品ID
     *
     */
    private Long productId;

    /**
     *
     * 商品名称
     *
     */
    private String productTitle;

    /**
     *
     * 商品图片
     *
     */
    private String productImage;


    /**
     *
     * 姓名
     *
     */
    private String name;

    /**
     *
     * 手机号码
     *
     */
    private String phone;

    /**
     *
     * 意向购买数量
     *
     */
    private Integer quantity;

    /**
     *
     * 用户备注
     *
     */
    private String remark;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     */
    private Date updateTime;

}
