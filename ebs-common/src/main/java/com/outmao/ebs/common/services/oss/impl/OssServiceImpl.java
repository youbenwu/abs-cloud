package com.outmao.ebs.common.services.oss.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.outmao.ebs.common.services.oss.OssService;
import com.outmao.ebs.common.services.oss.configuration.OSSProperties;
import com.outmao.ebs.common.services.oss.vo.OssSession;
import com.outmao.ebs.common.services.oss.vo.OssToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class OssServiceImpl implements OssService {


    @Autowired
    public OSSProperties properties;

    @Autowired
    public OSSClient ossClient;


    @Override
    public String putObject(String key, InputStream input) {

        // 上传文件流。
        ossClient.putObject(properties.getBucketPublicRead(), key, input);
        return properties.getBucketPublicReadUrl() + "/" + key;
    }

    @Override
    public String putObject(String key, String file) throws FileNotFoundException {
        // 上传文件流。
        return this.putObject(key,new FileInputStream(file));
    }

    @Override
    public OSSProperties getProperties() {
        return properties;
    }


    @Override
    public OssSession getOssSession(){

        if(properties.getRamAccessKeyId()==null){
            OssSession role=new OssSession();
            role.setAccessKeyId(properties.getAccessKeyId());
            role.setAccessKeySecret(properties.getAccessKeySecret());
            role.setBaseUrl(properties.getBucketPublicReadUrl());
            role.setUploadUrl(properties.getUploadUrl());
            return role;
        }

        String endpoint = "sts.aliyuncs.com";
        String accessKeyId = properties.getRamAccessKeyId();
        String accessKeySecret = properties.getRamAccessKeySecret();
        String roleArn =properties.getRoleArn() ;//"<role-arn>";
        String roleSessionName = "session-name";
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:*\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"*\" \n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        try {
            //构造default profile（参数留空，无需添加Region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            //用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setSysEndpoint(endpoint);
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // Optional
            final AssumeRoleResponse response = client.getAcsResponse(request);
            System.out.println("Expiration: " + response.getCredentials().getExpiration());
            System.out.println("SubAccessGroup Key Id: " + response.getCredentials().getAccessKeyId());
            System.out.println("SubAccessGroup Key Secret: " + response.getCredentials().getAccessKeySecret());
            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
            System.out.println("RequestId: " + response.getRequestId());
            OssSession role=new OssSession();
            role.setExpiration(response.getCredentials().getExpiration());
            role.setAccessKeyId(response.getCredentials().getAccessKeyId());
            role.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            role.setSecurityToken(response.getCredentials().getSecurityToken());
            role.setRequestId(response.getRequestId());
            role.setBaseUrl(properties.getBucketPublicReadUrl());
            role.setUploadUrl(properties.getUploadUrl());
            return role;
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }
        return null;
    }




    public OssToken generateToken(){
        // 授权访问oss的ack
        String accessId =properties.getAccessKeyId();
        String accessKey = properties.getAccessKeySecret();
        // 所属地域
        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
        // bucket的名称
        String bucket = properties.getBucketPublicRead();
        // 格式为https://bucketname.endpoint，例如https://bucket-name.oss-cn-chengdu.aliyuncs.com
        String host = "https://" + bucket + "." + endpoint;
        // 要上传的目录，比如：train
        String dir = "upload/";

        // 设置token的过期时间，这里设置的1分钟
        long expireEndTime = System.currentTimeMillis() + 600*1000;
        Date expiration = new Date(expireEndTime);

        // 设置生成token的权限
        PolicyConditions policyConditions = new PolicyConditions();
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        // 创建oss请求对象

        OSS client = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        // 发送请求获取token
        String postPolicy = client.generatePostPolicy(expiration, policyConditions);
        // 解析请求响应
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        // 政策信息
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        // 签名信息
        String postSignature = client.calculatePostSignature(postPolicy);

        System.out.println("accessKeyId："+accessId);
        System.out.println("encodedPolicy："+encodedPolicy);
        System.out.println("postSignature："+postSignature);
        System.out.println("dir："+dir);
        System.out.println("host："+host);
        System.out.println("expire："+(expireEndTime / 1000));

        OssToken token=new OssToken();
        token.setAccessKeyId(accessId);
        token.setEncodedPolicy(encodedPolicy);
        token.setPostSignature(postSignature);
        token.setDir(dir);
        token.setHost(host);
        token.setExpire(expireEndTime);

        return token;


    }


}
