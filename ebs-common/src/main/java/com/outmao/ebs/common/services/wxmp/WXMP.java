package com.outmao.ebs.common.services.wxmp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.Https;
import com.outmao.ebs.common.util.JsonUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Component
public class WXMP {

	//static String appid="wx29322f845f38d3df";
	//static String secret="35b270602da98650de4b8a18ca8f4fad";

	@Value("${wx.appid}")
	private String appid;

	@Value("${wx.secret}")
	private String secret;

	/*
	 * 开发者服务器使用登录凭证 code 获取 session_key 和 openid
	 */
	public WXMPSessionResult getSession(String code) throws Exception{

		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
				+ appid + "&secret=" + secret + "&js_code=" + code
				+ "&grant_type=authorization_code";

		System.out.println(url);

		String r= Https.httpRequest(url, "GET", null);

		WXMPSessionResult result= JsonUtil.fromJson(r, WXMPSessionResult.class);

		return result;

	}

	public WXPhoneResult getWxPhone(String code) throws Exception{

		WXMPTokenResult tokenResult=getToken();
		if(tokenResult.getErrcode()!=0){
			System.out.println(tokenResult.getErrmsg());
			return null;
		}

		String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="
				+ tokenResult.getAccess_token() + "&code=" + code ;

		System.out.println(url);

		WXCode wxCode=new WXCode();
		wxCode.setCode(code);

		String json=JSON.toJSONString(wxCode);

		System.out.println(json);

		String r= Https.httpRequest(url, "POST", json);

		WXPhoneResult result= JsonUtil.fromJson(r, WXPhoneResult.class);

		return result;

	}

	public WXMPTokenResult getToken()throws Exception{
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		String r= Https.httpRequest(url, "GET", null);
		WXMPTokenResult result= JsonUtil.fromJson(r, WXMPTokenResult.class);
		return result;
	}


	public WXMPResult sendMessage(String openId,String templateId,String data)throws Exception{
		WXMPTokenResult tokenResult=getToken();
		if(tokenResult.getErrcode()!=0){
			System.out.println(tokenResult.getErrmsg());
			return null;
		}
		String url="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+tokenResult.getAccess_token()
				+"&touser="+openId+"&template_id="+templateId+"&data="+ URLEncoder.encode(data,"UTF-8");

		WXMsg msg=new WXMsg();
		msg.setTouser(openId);
		msg.setTemplate_id(templateId);
		msg.setData(JSON.parse(data));
		//msg.setPage();
		//msg.setMiniprogram_state("trial");

		String json=JSON.toJSONString(msg);

		System.out.println(json);

		String r= Https.httpRequest(url, "POST", json);

		WXMPResult result=JsonUtil.fromJson(r, WXMPResult.class);

		System.out.println(result.getErrcode()+","+result.getErrmsg());

		return result;
	}


	/*
	 * 获取微信小程序二维码
	 *
	 */
	public  boolean getWxacode(String path,String scene,String savePath)throws Exception{
		WXMPTokenResult tokenResult=getToken();
		if(tokenResult.getErrcode()!=0){
			throw new BusinessException(tokenResult.getErrmsg());
		}
		String url="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+tokenResult.getAccess_token();
		Map<String, String> params=new HashMap<String, String>();
		params.put("page", path);
		params.put("scene", scene);
		String json= JsonUtil.toJson(params);
		boolean r=Https.saveFile(url,"POST",json, savePath);
		return r;
	}


	public  Object getPhoneNumber(String encryptedData, String session_key, String iv) {
		// 被加密的数据
		byte[] dataByte = Base64.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decode(session_key);
		// 偏移量
		byte[] ivByte = Base64.decode(iv);
		try {
			// 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}
