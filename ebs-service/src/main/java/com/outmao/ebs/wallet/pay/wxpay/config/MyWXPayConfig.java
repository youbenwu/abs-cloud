package com.outmao.ebs.wallet.pay.wxpay.config;


import com.outmao.ebs.wallet.pay.wxpay.sdk.IWXPayDomain;
import com.outmao.ebs.wallet.pay.wxpay.sdk.WXPayConfig;
import com.outmao.ebs.wallet.pay.wxpay.sdk.WXPayConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * 微信支付的参数配置
 *
 * @author mengday zhang
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "pay.wxpay")
public class MyWXPayConfig extends WXPayConfig {

	/** 公众账号ID */
	private String appID;

	/** 商户号 */
	private String mchID;

	/** API 密钥 */
	private String key;

	/** API 沙箱环境密钥 */
	private String sandboxKey;

	/** API证书绝对路径 */
	private String certPath;

	/** 异步通知地址 */
	private String notifyUrl;
	
	/** 退款异步通知地址 */
	private String refundNotifyUrl;

	private Boolean useSandbox;

	/**
	 * 获取商户证书内容
	 *
	 * @return 商户证书内容
	 */
	@Override
	public InputStream getCertStream() {
		File certFile = new File(certPath);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(certFile);
		} catch (FileNotFoundException e) {
		}
		return inputStream;
	}

	@Override
	public String getKey() {
		if (useSandbox) {
			return sandboxKey;
		}
		return key;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getMchID() {
		return mchID;
	}

	public void setMchID(String mchID) {
		this.mchID = mchID;
	}

	public String getSandboxKey() {
		return sandboxKey;
	}

	public void setSandboxKey(String sandboxKey) {
		this.sandboxKey = sandboxKey;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getRefundNotifyUrl() {
		return refundNotifyUrl;
	}

	public void setRefundNotifyUrl(String refundNotifyUrl) {
		this.refundNotifyUrl = refundNotifyUrl;
	}

	public Boolean getUseSandbox() {
		return useSandbox;
	}

	public void setUseSandbox(Boolean useSandbox) {
		this.useSandbox = useSandbox;
	}

	public void setKey(String key) {
		this.key = key;
	}


	@Override
	public IWXPayDomain getWXPayDomain() {
		return new IWXPayDomain() {
			
			@Override
			public void report(String domain, long elapsedTimeMillis, Exception ex) {
				if(ex!=null) {
					//ex.printStackTrace();
				}
			}
			
			@Override
			public DomainInfo getDomain(WXPayConfig config) {
				return new DomainInfo(WXPayConstants.DOMAIN_API, true);
			}
		};
	}

}
