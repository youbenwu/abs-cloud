package com.outmao.ebs.clients.shpanhe.vo;


import lombok.Data;

@Data
public class OrderDetail {

    private String orderNo;//	string	门票订单号
    private String customerOrderNo;//	string	分销商订单号
    private float orderAmount;//	float	订单总金额
    private int orderStatus;//	int	订单状态（待支付 = 1 ,出票中 = 2,已出票 = 3 ,出票失败 = 4 ,退订中 = 5 ,退订失败 = 6 ,已退订 = 7 , 已取消 = 99）
    private String orderStatusName;//	string	订单状态名称
    private int payStatus;//	int	支付状态（0未支付 1已支付）
    private String payTime;//	string	支付时间(支付成功才有值)
    private int paymentType;//	int	支付方式(0未支付 1虚拟钱包支付 2支付宝 3微信 暂时只支持虚拟钱包支付)
    private String transactionNo;//	string	交易单号
    private String payLimitTime;//	string	支付时限
    private String scenicName;//	string	景点名称
    private String scenicID;//	string	景点ID
    private String productID;//	string	产品ID
    private String productName;//	string	产品名称
    private String bookNotice;//	string	预订说明(同产品详情接口)
    private String refundChangeRule;//	string	退改说明(同产品详情接口)
    private String costDescription;//	string	费用说明(同产品详情接口)
    private String useDescription;//	string	使用说明(同产品详情接口)
    private String otherDescription;//	string	其他说明(同产品详情接口)
    private String drawAddress;//	string	取票地址
    private String travelDate;//	string	出游日期
    private int quantity;//	int	购买数量
    private String voucherCode;//	string	凭证码(多个英文逗号分隔)
    private String voucherUrl;//	string	凭证地址链接(如二维码链接、电子票链接，多个英文逗号分隔)
    private String supplierVoucherDetails;//	string	供应商凭证（所有入园凭证拼接在一起的，可以直接作为短信凭证，如：二维码：https://api.panhe.cn/?uOKSADBKc/94D7.jpg ，凭证码：769871060 。）
    private String confirmOrderID;//	string	确认单号(凭供应商订单号入园时，此值为供应商订单号)
    private String unFinishedReason;//	string	订单出票失败或退订失败原因
    private String refundSuccessTime;//	string	出票失败或退订成功后 ，退款成功时间(退款成功才有值)
    private String refundTransactionNo;//	string	出票失败或退订成功后，退款交易单号(退款成功才有值)
    private String refundAmount;//	float	出票失败或退订成功后，退款金额
    private String refundTransactionMethods;//	int	出票失败或退订成功后，退款交易方式(退款成功才有值 0未退款 1虚拟钱包 2支付宝 3微信 暂时只支持虚拟钱包支付)
    private TravellerInfo contact;//	TravellerInfo	取票人（同创建订单接口请求参数中取票人信息）
    private TravellerInfo tourists;//	TravellerInfo[]	游客信息列表（同创建订单接口请求参数中游客信息）


}
