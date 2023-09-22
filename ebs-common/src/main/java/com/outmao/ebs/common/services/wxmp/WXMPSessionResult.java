package com.outmao.ebs.common.services.wxmp;

//开发者服务器使用登录凭证 code 获取 session_key 和 openid
public class WXMPSessionResult extends WXMPResult {
	
	private String openid;
	private String session_key;
	private String unionid;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	

}
