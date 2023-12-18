package com.outmao.ebs.message.vo;



import lombok.Data;
import java.io.Serializable;


@Data
public class MessageTemplateTagVO implements Serializable {


    private Long id;
    private Long typeId;
    private String name;
    private String value;



}
