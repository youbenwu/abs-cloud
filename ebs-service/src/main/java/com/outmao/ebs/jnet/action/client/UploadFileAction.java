package com.outmao.ebs.jnet.action.client;

import com.aliyun.oss.common.utils.DateUtil;
import com.google.common.collect.Lists;
import com.outmao.ebs.jnet.util.AlibabaOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 上传文件
 * 
 * @author yeyi
 * @date 2019年7月18日
 */
@Slf4j
@Api(value = "/upload", tags = "上传文件模块接口")
@RequestMapping(value = "/api/upload")
@RestController
public class UploadFileAction {
	
	final static private Logger logger = LoggerFactory.getLogger(UploadFileAction.class);

    /**
     * 批量上传文件（图片）
     * 如果文件名 key 重复则会覆盖之前上传的内容(包括过期时间)，因此如果不希望覆盖必须上传者自己管理 key 不重复
     * key 使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
     * @param millisecond 过期时间戳，毫秒，最小不能小于1小时，非必传
     * @param year 过期时间，年，最小不能小于1年（没填写  millisecond 才生效），非必传
     * 若没填写任何过期参数(millisecond, year)，默认100年(实际代码跑出来50年，还不知道为什么)
     * @return 图片 url list
     * @author yeyi
     * @date 2019年7月18日
     */
	@ApiOperation(value = "批量上传文件（图片）\r\n" + 
			"如果文件名 key 重复则会覆盖之前上传的内容(包括过期时间)，因此如果不希望覆盖必须上传者自己管理 key 不重复\r\n" + 
			"key 使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\\”字符开头。\r\n" + 
			"@param millisecond 过期时间戳，毫秒，最小不能小于1小时，非必传\r\n" + 
			"@param year 过期时间，年，最小不能小于1年（没填写  millisecond 才生效），非必传\r\n" + 
			"若没填写任何过期参数(millisecond, year)，默认100年(实际代码跑出来50年，还不知道为什么)\r\n" + 
			"@return 图片 url list", notes = "批量上传文件（图片）")
    @RequestMapping(value = "/files")
    public List<String> files(HttpServletRequest request, Long millisecond, Integer year){
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        
        // millisecond 为主，没有再看 year
        final long minMillis = System.currentTimeMillis()+1000*3600; // 最小不能小于一小时
        if(null==millisecond) {
        	if(null==year || year<1) {
        		millisecond = 1000*3600*24*365L*100;
            } else {
                millisecond = 1000*3600*24*365L*year;
            }
        } else {
        	if(millisecond<minMillis) {
        		millisecond = minMillis;
        	}
        }
        Date expiration = new Date(millisecond);
        log.info("uploadFil expiration: {}", DateUtil.formatIso8601Date(expiration));
        
        List<String> result = Lists.newLinkedList();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileKey = entity.getKey();
            if(StringUtils.isEmpty(fileKey) || fileKey.length()>1023) {
            	log.error("uploadFil key err: {}", fileKey);
            	continue;
            }
            if('\\'==fileKey.charAt(0) || '/'==fileKey.charAt(0)) {
            	log.error("uploadFil key can't start with slash: {}", fileKey);
            	continue;
            }
            
            try {
                URL url = AlibabaOssUtil.uploadInputStream(mf.getInputStream(), fileKey, expiration);
                if(null==url || StringUtils.isEmpty(url.toString())) {
                	log.error("uploadInputStrea empty: {},{}", fileKey, mf.getOriginalFilename());
                	continue;
                }
                result.add(url.toString());
            } catch (Exception e) {
                log.error("uploadInputStrea err: ", e);
            }
        }
        
        return result;
    }
}
