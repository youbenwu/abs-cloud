package com.outmao.ebs.common.services.wxmp;

import lombok.Data;

@Data
public class WXMsg {

    private String touser;

    private String template_id;

    private String page;

    private String miniprogram_state;

    private String lang="zh_CN";

    private Object data;

}
