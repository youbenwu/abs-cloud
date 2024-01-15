package com.outmao.ebs.common.vo;

import com.outmao.ebs.common.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Duration {

    public final static String m = "m";
    public final static String H = "H";
    public final static String d = "d";
    public final static String M = "M";
    public final static String y = "y";

    private String field=d;

    private int value=1;


    public String toString(){
        return value+field;
    }


    static Pattern pattern = Pattern.compile("(\\d+)(\\D)");

    public static Duration parse(String s){
        List<String> fs= Arrays.asList(y,M,d,H,m);
        Matcher matcher=pattern.matcher(s);
        if(matcher.find()){
            String v=matcher.group(1);
            String f=matcher.group(2);
            if(fs.contains(f)) {
                Duration d = new Duration(f, Integer.parseInt(v));
                return d;
            }
        }
        return null;
    }

    public Date getToTime(Date fromTime){

        Date toTime=fromTime;

        if(field.equals(y)){
            toTime= DateUtil.addYears(fromTime,value);
        }else if(field.equals(M)){
            toTime= DateUtil.addMonths(fromTime,value);
        }else if(field.equals(d)){
            toTime= DateUtil.addDays(fromTime,value);
        }else if(field.equals(H)){
            toTime= DateUtil.addHours(fromTime,value);
        }else if(field.equals(m)){
            toTime= DateUtil.addMinutes(fromTime,value);
        }

        return toTime;
    }


}
