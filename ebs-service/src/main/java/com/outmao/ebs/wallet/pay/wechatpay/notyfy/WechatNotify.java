package com.outmao.ebs.wallet.pay.wechatpay.notyfy;


import com.alibaba.fastjson.JSON;
import com.outmao.ebs.common.util.RequestUtil;
import com.outmao.ebs.wallet.pay.wechatpay.common.VerdureRequestWrapper;
import com.outmao.ebs.wallet.pay.wechatpay.config.WechatPayConfiguration;
import com.outmao.ebs.wallet.pay.wechatpay.vo.WechatNotifyResult;
import com.wechat.pay.java.core.exception.ValidationException;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping("/wechatpay")
public class WechatNotify {


	@Autowired
	private WechatPayConfiguration configuration;

    @Autowired
    private WechatPayNotifyListener listener;


	@PostMapping("/notify")
	public ResponseEntity nation(HttpServletRequest request) throws IOException {

		VerdureRequestWrapper requestWrapper = new VerdureRequestWrapper(request);
		log.info("/****************************** 接收微信支付通知 ******************************/");
		log.info(requestWrapper.getRequestParams());
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String element = headerNames.nextElement();
			log.info(element+":"+request.getHeader(element));
		}


		String signature = RequestUtil.getHeader(request, "Wechatpay-Signature");
		String timestamp = RequestUtil.getHeader(request, "Wechatpay-Timestamp");
		String signType = RequestUtil.getHeader(request, "Wechatpay-Signature-Type");
		String serial = RequestUtil.getHeader(request, "Wechatpay-Serial");
		String nonce = RequestUtil.getHeader(request, "Wechatpay-Nonce");


		WechatNotifyResult notifyResult = JSON.parseObject(requestWrapper.getRequestParams(), WechatNotifyResult.class);

		// 构造 RequestParam
		// 获取HTTP请求头中的 Wechatpay-Signature 、 Wechatpay-Nonce 、 Wechatpay-Timestamp 、 Wechatpay-Serial 、 Request-ID 、Wechatpay-Signature-Type 对应的值，构建 RequestParam 。
		RequestParam requestParam = new RequestParam.Builder()
				.serialNumber(serial)
				.nonce(nonce)
				.signature(signature)
				.timestamp(timestamp)
				// 若未设置signType，默认值为 WECHATPAY2-SHA256-RSA2048
				.signType(signType)
				.body(requestWrapper.getRequestParams())
				.build();

		// 初始化 NotificationParser
		NotificationParser parser=new NotificationParser((NotificationConfig) configuration.payConfig());


		try {
			if(notifyResult.getEvent_type().startsWith("TRANSACTION.")){
				// 验签、解密并转换成 Transaction
				Transaction transaction = parser.parse(requestParam, Transaction.class);
				log.info("/****************************** Transaction ******************************/");
				log.info(transaction.toString());
				// 业务处理
				listener.notify(transaction);
			}else if(notifyResult.getEvent_type().startsWith("REFUND.")){
				// 验签、解密并转换成 Transaction
				RefundNotification transaction = parser.parse(requestParam, RefundNotification.class);
				log.info("/****************************** RefundNotification ******************************/");
				log.info(transaction.toString());
				// 业务处理
				listener.notifyRefund(transaction);
			}
		} catch (ValidationException e) {
			// 签名验证失败，返回 401 UNAUTHORIZED 状态码
			log.error("签名验证失败", e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}catch (Exception e){
			log.error("处理失败", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		// 处理成功，返回 200 OK 状态码
		return ResponseEntity.status(HttpStatus.OK).build();

	}




}
