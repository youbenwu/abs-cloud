package com.outmao.ebs.jnet.util;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * 阿里 OSS 工具类
 * https://blog.csdn.net/qq_36763348/article/details/86523579 
 * @author: yeyi
 * @date: 2019年7月13日
 */
@Slf4j
@Component
public class AlibabaOssUtil {
    static Logger logger = LoggerFactory.getLogger(AlibabaOssUtil.class);

    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    private static String endpoint;

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId;
    private static String accessKeySecret;

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    private static String bucketName;
    private static String bucketAcl;

    @Value("${oss.properties.bucket-public-read}")
    private String bucketNameTmp;
    @Value("${oss.properties.bucket-acl}")
    private String bucketAclTmp;
    @Value("${oss.properties.access-key-id}")
    private String accessKeyIdTmp;
    @Value("${oss.properties.access-key-secret}")
    private String accessKeySecretTmp;
    @Value("${oss.properties.endpoint}")
    private String endpointTmp;

    @PostConstruct
    public void init() {
        bucketName = bucketNameTmp;
        bucketAcl = bucketAclTmp;
        accessKeyId = accessKeyIdTmp;
        accessKeySecret = accessKeySecretTmp;
        endpoint = endpointTmp;
    }

    private static String getBucketName() {
        return bucketName;
    }

    private static String getBucketAcl() {
        return bucketAcl;
    }

    private static void createBucket(OSSClient ossClient) {
        // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
        if (ossClient.doesBucketExist(getBucketName())) {
            log.info("您已经创建Bucket：" + getBucketName() + "。");
        }
        else {
            log.info("您的Bucket不存在，创建Bucket：" + getBucketName() + "。");
            // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            ossClient.createBucket(getBucketName());
        }
        ossClient.setBucketAcl(getBucketName(), CannedAccessControlList.parse(getBucketAcl()));
    }
    
    private static BucketInfo getBucketInfo(OSSClient ossClient) {
     // 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
        BucketInfo info = ossClient.getBucketInfo(getBucketName());
        log.info("Bucket " + getBucketName() + "的信息如下：");
        log.info("\t数据中心：" + info.getBucket().getLocation());
        log.info("\t创建时间：" + info.getBucket().getCreationDate());
        log.info("\t用户标志：" + info.getBucket().getOwner());
        return info;
    }
    
    private static void saveFile(OSSClient ossClient, String fileKey, File file) {
        ossClient.putObject(getBucketName(), fileKey, file);
        log.info("Object：" + fileKey + "存入OSS成功。");
    }
    
    private static void saveInputStream(OSSClient ossClient, String fileKey, InputStream is) {
        ossClient.putObject(getBucketName(), fileKey, is);
        log.info("InputStream：" + fileKey + "存入OSS成功。");
    }
    
    private static void getObjList(OSSClient ossClient) {
        // 查看Bucket中的Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
        ObjectListing objectListing = ossClient.listObjects(getBucketName());
        List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
        log.info("您有以下Object：");
        for (OSSObjectSummary object : objectSummary) {
            log.info("\t" + object.getKey());
        }
    }
    
    private static void delObj(OSSClient ossClient, String key) {
        // 删除Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
        ossClient.deleteObject(getBucketName(), key);
        log.info("删除Object：" + key + "成功。");
    }
    
    /**
     * 上传流并返回 url
     * @param is 流
     * @param key 是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。
     * 使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
     * 最终文件 url 将是 bucketName + ".oss-cn-hangzhou.aliyuncs.com/" + key + "?Expires=1563097064&OSSAccessKeyId=LTAIEs0C3GuNQvD9&Signature=xxx"
     * @param expiration 文件过期日期
     * @author: yeyi
     * @date: 2019年7月13日
     */
    public static URL uploadInputStream(InputStream is, String key, Date expiration) {
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            createBucket(ossClient);
            getBucketInfo(ossClient);
            saveInputStream(ossClient, key, is);
            return ossClient.generatePresignedUrl(getBucketName(), key, expiration);
        } catch (Exception e) {
            e.printStackTrace();
           log.error("uploadInputStrea err: ", e);
           return null;
        } finally {
            ossClient.shutdown();
        }
    }
    
    public static URL uploadFile(File file, String key, Date expiration) {
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            createBucket(ossClient);
            getBucketInfo(ossClient);
            saveFile(ossClient, key, file);
            return ossClient.generatePresignedUrl(getBucketName(), key, expiration);
        } catch (Exception e) {
            e.printStackTrace();
           log.error("uploadFil err: ", e);
           return null;
        } finally {
            ossClient.shutdown();
        }
    }
    
    public static void main(String[] args) {
        String filePath = "E:\\backup\\picture\\QQ收藏图片\\男优鼓掌.gif";
        Date expiration = new Date(System.currentTimeMillis() + 1000*3600*24);
        URL url = uploadFile(new File(filePath), "男优鼓掌.gif", expiration);
        if(null!=url) {
            log.info(url.toString());
        }
        
        try {
            InputStream is = new FileInputStream(new File(filePath));
            URL urlStream = uploadInputStream(is, "testStream.gif", expiration);
            log.info(urlStream.toString());
        } catch (FileNotFoundException e) {
            log.error("imputStream err: ", e);
        }
    }
}
