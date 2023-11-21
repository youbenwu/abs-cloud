package com.outmao.ebs.common.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TimeSpan implements Serializable {

    public final static int MINUTE = 1;
    public final static int HOUR = 2;
    public final static int DAY = 3;


    @ApiModelProperty(name = "field", value = "1--分钟 2--小时 3--天")
    private Integer field;
    private Integer value;

    public int getMinutes(){
        if(field!=null&&value!=null){
            if(field==MINUTE)
                return value;
            if(field==HOUR)
                return value*60;
            if(field==DAY)
                return value*60*24;
        }
        return 0;
    }

}
