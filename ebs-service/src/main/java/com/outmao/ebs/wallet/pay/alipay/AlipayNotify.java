package com.outmao.ebs.wallet.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.outmao.ebs.wallet.pay.alipay.config.AlipayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝通用接口.
 * <p>
 * detailed description
 *
 * @author Mengday Zhang
 * @version 1.0
 * @since 2018/6/13
 */
@Slf4j
@RestController
@RequestMapping("/api/alipay")
public class AlipayNotify {

	@Autowired
	private AlipayNotifyListener listener;

	@Autowired
	private AlipayProperties alipayProperties;


	/**
	 * 支付异步通知
	 *
	 * 接收到异步通知并验签通过后，一定要检查通知内容，
	 * 包括通知中的app_id、out_trade_no、total_amount是否与请求中的一致，
	 * 并根据trade_status进行后续业务处理。
	 *
	 * https://docs.open.alipay.com/194/103296
	 */
	@PreAuthorize("permitAll")
	@RequestMapping("/notify")
	public String notify(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {


		// 一定要验签，防止黑客篡改参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		StringBuilder notifyBuild = new StringBuilder(
				"/****************************** 支付宝支付结果回调 ******************************/\n");
		parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n"));

		log.debug(notifyBuild.toString());

		boolean signVerified = this.rsaCheckV1(request);

		if (signVerified) {
			/**
			 * TODO 需要严格按照如下描述校验通知数据的正确性
			 *
			 * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
			 * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
			 * 同时需要校验通知中的seller_id（或者seller_email)
			 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
			 *
			 * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
			 * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
			 * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
			 */

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			// TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
			// TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);

			listener.notify(trade_status, out_trade_no, trade_no, total_amount);

			return "success";
		}
		return "failure";
	}

	/**
	 * 校验签名
	 * 
	 * @param request
	 * @return
	 */
	public boolean rsaCheckV1(HttpServletRequest request) {
		// https://docs.open.alipay.com/54/106370
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<>();
		@SuppressWarnings("rawtypes")
		Map requestParams = request.getParameterMap();
		for (@SuppressWarnings("rawtypes")
		Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		try {
			boolean verifyResult = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(),
					alipayProperties.getCharset(), alipayProperties.getSignType());

			return verifyResult;
		} catch (AlipayApiException e) {
			return false;
		}
	}


}
