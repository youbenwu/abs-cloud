package com.outmao.ebs.common.services.oss;




import com.outmao.ebs.common.services.oss.configuration.OSSProperties;
import com.outmao.ebs.common.services.oss.vo.OssSession;
import com.outmao.ebs.common.services.oss.vo.OssToken;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface OssService {

    public OSSProperties getProperties();

    public String putObject(String key, InputStream input);
    public String putObject(String key, String file) throws FileNotFoundException;

    //获取oss临时会话
    public OssSession getOssSession();

    public OssToken generateToken();


}
