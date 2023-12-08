package com.outmao.ebs.wallet.pay.wxpay.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.wallet.pay.wxpay.WXPayService;
import com.outmao.ebs.wallet.pay.wxpay.config.MyWXPayConfig;
import com.outmao.ebs.wallet.pay.wxpay.config.WXPayClient;
import com.outmao.ebs.wallet.pay.wxpay.sdk.WXPayConstants;
import com.outmao.ebs.wallet.pay.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class WXPayServiceImpl implements WXPayService {

	@Autowired
    private WXPayClient wxPayClient;

	@Autowired
	private MyWXPayConfig wxPayConfig;

	@Override
	public Map<String, String> unifiedOrder(String outTradeNo, String body, double totalAmount, String clientIp) {
		try {
			if(body.length()>100) {
				body=body.substring(0, 100);
			}
			if(clientIp==null) {
				clientIp="1.0.0.0";
			}
			Map<String, String> requestData = new HashMap<>();
			requestData.put("out_trade_no", outTradeNo);
			requestData.put("trade_type", "APP");
			requestData.put("body", body);
			// 订单总金额，单位为分
			requestData.put("total_fee", ((int) (totalAmount * 100)) + "");
			// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
			requestData.put("spbill_create_ip", clientIp);
			// 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
			//requestData.put("notify_url", wxConfig.getNotifyUrl());
			// 自定义参数, 可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
			// requestData.put("device_info", "");
			// 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
			// requestData.put("attach", "");
			// 场景信息
			// reqData.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\":
			// \"http://3sbqi7.natappfree.cc\",\"wap_name\": \"腾讯充值\"}}");

			Map<String, String> responseData = wxPayClient.unifiedOrder(requestData);

			String returnCode = responseData.get("return_code");
			String resultCode = responseData.get("result_code");

//			System.out.println("-----------------return_code------------------"+clientIp);
//			System.out.println("-----------------resultCode------------------"+resultCode);
//
			if (WXPayConstants.SUCCESS.equals(returnCode) && WXPayConstants.SUCCESS.equals(resultCode)) {
				// 预支付交易会话标识
				String prepay_id = responseData.get("prepay_id");
				String nonce_str = responseData.get("nonce_str");
				// 生成APP调起参数
				Map<String, String> data = new HashMap<>();
				
				data.put("appid", wxPayConfig.getAppID());
				data.put("partnerid", wxPayConfig.getMchID());
				data.put("prepayid", prepay_id);
				data.put("package", "Sign=WXPay");
				data.put("noncestr", nonce_str);
				data.put("timestamp", System.currentTimeMillis() / 1000 + "");
				data.put("sign", WXPayUtil.generateSignature(data, wxPayConfig.getKey(), wxPayClient.getSignType()));

				return data;
			} else {
				throw new BusinessException(responseData.get("return_msg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Map<String, String> refund(String outTradeNo, double totalAmount) {
		try {
			String total_fee = ((int) (totalAmount * 100)) + "";
			Map<String, String> requestData = new HashMap<String, String>();
			requestData.put("out_trade_no", outTradeNo);
			requestData.put("out_refund_no", UUID.randomUUID().toString());
			requestData.put("total_fee", total_fee);
			requestData.put("refund_fee", total_fee);
			requestData.put("notify_url",wxPayConfig.getRefundNotifyUrl() );
			Map<String, String> responseData = wxPayClient.refund(requestData);
			String returnCode = responseData.get("return_code");
			String resultCode = responseData.get("result_code");
			if (WXPayConstants.SUCCESS.equals(returnCode) && WXPayConstants.SUCCESS.equals(resultCode)) {
                return requestData;
			} else {
				throw new BusinessException(responseData.get("return_msg"));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Map<String, String> closeOrder(String outTradeNo) {
		try {

			Map<String, String> requestData = new HashMap<String, String>();
			requestData.put("out_trade_no", outTradeNo);

			Map<String, String> responseData = wxPayClient.closeOrder(requestData);
			String returnCode = responseData.get("return_code");
			String resultCode = responseData.get("result_code");
			if (WXPayConstants.SUCCESS.equals(returnCode) && WXPayConstants.SUCCESS.equals(resultCode)) {
                return requestData;
			} else {
				throw new BusinessException(responseData.get("return_msg"));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/*
	 * 
	 * SUCCESS—支付成功 
	 * REFUND—转入退款 
	 * NOTPAY—未支付 
	 * CLOSED—已关闭 
	 * REVOKED—已撤销（刷卡支付）
	 * USERPAYING--用户支付中 
	 * PAYERROR--支付失败(其他原因，如银行返回失败)
	 * 
	 */
	@Override
	public Map<String, String> orderQuery(String outTradeNo) {
		try {

			Map<String, String> requestData = new HashMap<String, String>();
			requestData.put("out_trade_no", outTradeNo);

			Map<String, String> responseData = wxPayClient.orderQuery(requestData);
			String returnCode = responseData.get("return_code");
			String resultCode = responseData.get("result_code");
			if (WXPayConstants.SUCCESS.equals(returnCode) && WXPayConstants.SUCCESS.equals(resultCode)) {
				//String trade_state = responseData.getSubStatus("trade_state");
				return responseData;
			} else {
				throw new BusinessException(responseData.get("return_msg"));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/*
	 * 
	 * 退款状态：
SUCCESS—退款成功
REFUNDCLOSE—退款关闭。
PROCESSING—退款处理中
CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
	 * */
	@Override
	public Map<String, String> refundQuery(String outTradeNo) {
		try {

			Map<String, String> requestData = new HashMap<String, String>();
			requestData.put("out_trade_no", outTradeNo);

			Map<String, String> responseData = wxPayClient.refundQuery(requestData);
			String returnCode = responseData.get("return_code");
			String resultCode = responseData.get("result_code");
			if (WXPayConstants.SUCCESS.equals(returnCode) && WXPayConstants.SUCCESS.equals(resultCode)) {
				//String refund_status = responseData.getSubStatus("refund_status_0");
				return responseData;
			} else {
				throw new BusinessException(responseData.get("return_msg"));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	// 
	
	public String getPublicKey() throws Exception {
		/*
			 String urlSuffix="";
			 Map<String, String> reqData = new HashMap<String, String>();
			 wxPay.requestWithCert(urlSuffix, reqData, connectTimeoutMs, readTimeoutMs);
			 
		 SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		 String nonce_str = StringUtils1.getStrRandom(28);//获取随机字符串
		 parameters.put("mch_id", WChatInfo.MCH_ID);
		 parameters.put("nonce_str", nonce_str);
		 parameters.put("sign_type", "MD5");
		 // 创建签名
		 String sign = SignUtils.creatSign(WChatInfo.CHARSET, parameters);
		 System.out.println(sign);


		 TreeMap<String, String> tmap = new TreeMap<String, String>();
		 tmap.put("mch_id", WChatInfo.MCH_ID);
		 tmap.put("nonce_str", nonce_str);
		 tmap.put("sign_type", "MD5");
		 tmap.put("sign", sign);
		 String xml = XMLUtils.getRequestXml(tmap);//将请求参数转换为请求报文
		         //带证书请求
		 String xml1 = HttpClientCustomSSL.httpClientResultGetPublicKey(xml);//发送http的post请求获取公钥报文
		 String publicKey = XMLUtils.Progress_resultParseXml(xml1);//解析腾迅返回的公钥xml并获取公钥元素
		 return publicKey;
*/
		return null;

		 }
		 
	

}
