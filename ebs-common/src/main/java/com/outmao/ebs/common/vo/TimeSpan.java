package com.outmao.ebs.common.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Embeddable
public class TimeSpan implements Serializable {

    public final static int MINUTE = 1;
    public final static int HOUR = 2;
    public final static int DAY = 3;
    public final static int MONTH = 4;
    public final static int YEAR = 5;


    @ApiModelProperty(name = "field", value = "1--分钟 2--小时 3--天 4--月 5--年")
    private Integer field=5;
    private Integer value=1;


    @JsonIgnore
    @Transient
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


    public Between<Date> getDateBetween(Date date){
        if(field==null)
            field=5;
        if(value==null)
            value=1;
        Between<Date> between=new Between<>();
        Date startTime= DateUtil.format_yyyy_MM_dd(date);
        Date endTime=null;
        if(field==YEAR){
            endTime=DateUtil.addYears(startTime,value);
        }else if(field==MONTH){
            endTime=DateUtil.addMonths(startTime,value);
        }else if(field==DAY){
            endTime=DateUtil.addDays(startTime,value);
        }
        between.setFrom(startTime);
        between.setTo(endTime);
        return between;
    }

}
