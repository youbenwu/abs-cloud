package com.outmao.ebs.common.services.sms.sender;


import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.juhe.JuheClient;
import com.outmao.ebs.common.services.juhe.util.JuheUtil;
import com.outmao.ebs.common.services.juhe.vo.JuheResult;
import com.outmao.ebs.common.services.sms.configuration.JuheSmsProperties;
import org.springframework.beans.factory.annotation.Autowired;


public class JuheSmsSender implements SmsSender {

	@Autowired
	private JuheClient juheApi;

	@Autowired
	private JuheSmsProperties juheSmsProperties;


	private String getTemplateCode(String template){

		if(juheSmsProperties.getTemplates()==null){
			throw new BusinessException("Juhe短信模板没配置");
		}

		String templateCode= juheSmsProperties.getTemplates().get(template);

		if(templateCode==null){
			throw new BusinessException("短信模板没配置");
		}

		return templateCode;

	}

	@Override
	public void send(String phone, String template, String params) {

		//tpl_value:#code#=431515
		String tpl_value= JuheUtil.getParamStringFromMapJson(params);

		JuheResult r = juheApi.smsSend(phone,getTemplateCode(template),tpl_value);

		if (r.getError_code() != 0) {
			throw new BusinessException(r.getReason());
		}

	}


}
