package com.outmao.ebs.clients.shpanhe.vo;


import lombok.Data;

/**
 *
 * customerOrderNo	string	否	分销商订单号（采购自己平台的本地订单号）
 * orderAmount	float	是	订单总金额
 * travelDate	string	是	出游日期（格式:yyyy-MM-dd）
 * scenicID	string	是	景点ID
 * productID	string	是	产品ID
 * quantity	int	是	预订产品份数
 * contact	TravellerInfo	是	取票人信息(参见门票详情中：contactInfoType 字段规则输入)
 * tourists	TravellerInfo[]	是	游客信息列表（参见门票详情中：touristInfoType 字段规则输入）
 * name	string	是	取票人/游客 姓名
 * mobile	string	是	取票人/游客 手机号
 * cardType	int	否	取票人/游客 证件类型(身份证 = 1,护照 = 2, 回乡证 = 3 , 台胞证 = 4 ,港澳通行证 = 5,台湾通行证 = 6,军人证 = 7 , 外国人永久居留身份证 = 8 ,港澳台居民居住证 = 9 , 户口簿= 10 , 出生证明= 11, 学生证 = 12)
 * cardNo	string	否	取票人/游客 证件号
 * callBackUrl	string	是	订单回调地址（用于接收订单后期状态变更异步回调），回调详见:订单状态推送通知说明
 *
 * **/

@Data
public class CreateOrderRequest {


    private String customerOrderNo;//	string	否	分销商订单号（采购自己平台的本地订单号）
    private double orderAmount;//	float	是	订单总金额
    private String travelDate;//	string	是	出游日期（格式:yyyy-MM-dd）
    private String scenicID;//	string	是	景点ID
    private String productID;//	string	是	产品ID
    private int quantity;//	int	是	预订产品份数
    private TravellerInfo contact	;//	是	取票人信息(参见门票详情中：contactInfoType 字段规则输入)
    private TravellerInfo tourists	;//TravellerInfo[]	是	游客信息列表（参见门票详情中：touristInfoType 字段规则输入）
    private String name;//	string	是	取票人/游客 姓名
    private String mobile;//	string	是	取票人/游客 手机号
    private int cardType;//	int	否	取票人/游客 证件类型(身份证 = 1,护照 = 2, 回乡证 = 3 , 台胞证 = 4 ,港澳通行证 = 5,台湾通行证 = 6,军人证 = 7 , 外国人永久居留身份证 = 8 ,港澳台居民居住证 = 9 , 户口簿= 10 , 出生证明= 11, 学生证 = 12)
    private String cardNo;//	string	否	取票人/游客 证件号
    private String callBackUrl;//	string	是	订单回调地址（用于接收订单后期状态变更异步回调），回调详见:订单状态推送通知说明


}
