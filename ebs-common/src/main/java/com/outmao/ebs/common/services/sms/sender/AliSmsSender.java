package com.outmao.ebs.common.services.sms.sender;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.sms.configuration.AliSmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AliSmsSender implements SmsSender {


    @Autowired
    private AliSmsProperties aliSmsProperties;


    private String getTemplateCode(String template){

        if(aliSmsProperties.getTemplates()==null){
            throw new BusinessException("阿里云短信模板没配置");
        }

        String templateCode= aliSmsProperties.getTemplates().get(template);

        if(templateCode==null){
            throw new BusinessException("短信模板没配置");
        }

        return templateCode;

    }

    @Override
    public void send(String phone, String template, String params) {

        //可自助调整超时时间
        System.setProperty("sun.net.web.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.web.defaultReadTimeout", "10000");

        try {
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsProperties.getAccessKeyId(), aliSmsProperties.getAccessKeySecret());
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", aliSmsProperties.getProduct(), aliSmsProperties.getDomain());
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(aliSmsProperties.getSignName());
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(getTemplateCode(template));
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(params);

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("ebs");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            if(!sendSmsResponse.getCode().equals("OK")) {
                throw new BusinessException(sendSmsResponse.getMessage());
            }

        }catch (ClientException e){
            throw new BusinessException(e.getMessage());
        }


    }



}
