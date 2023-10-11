package com.outmao.ebs.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Item implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String type;
    private String title;
    private String subtitle;
    private String url;
    private String content;
    private String image;
    private String video;

    public Item(Long id,String type,String title){
        this.id=id;
        this.type=type;
        this.title=title;
    }

}
