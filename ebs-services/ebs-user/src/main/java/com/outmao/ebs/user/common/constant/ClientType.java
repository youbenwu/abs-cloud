package com.outmao.ebs.user.common.constant;


/**
 *
 * 认证方式
 *
 */
public enum ClientType {

    PC_ADMIN("PC_ADMIN","电脑后台端"),
    PC_CLIENT("PC_CLIENT","电脑客户端"),
    MP_CLIENT("MP_CLIENT","手机客户端"),
    MP_APP("MP_APP","手机APP端");

    private String name;
    private String description;

    ClientType(String name, String description){
        this.name=name;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
