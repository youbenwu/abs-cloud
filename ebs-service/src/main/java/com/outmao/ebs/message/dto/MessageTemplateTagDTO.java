package com.outmao.ebs.message.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageTemplateTagDTO {

    private Long id;
    private Long typeId;
    private String name;
    private String value;

}
