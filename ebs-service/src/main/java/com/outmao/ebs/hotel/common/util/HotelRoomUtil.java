package com.outmao.ebs.hotel.common.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelRoomUtil {

    static Pattern pattern = Pattern.compile("(\\d+)至(\\d+)");


    public static List<String> getRoomNo(String str){
        Matcher matcher=pattern.matcher(str);
        if(matcher.find()){
            String fromS=matcher.group(1);
            String toS=matcher.group(2);
            try {
                int from=Integer.parseInt(fromS);
                int to=Integer.parseInt(toS);
                if(to>from){
                    List<String> list=new ArrayList<>(to-from+1);
                    for(int i=from;i<=to;i++){
                       list.add(i+"");
                    }
                    return list;
                }
            }catch (Exception e){

            }
        }
        return Arrays.asList(str);
    }


    public static void main(String[] args) {
        getRoomNo("5001至5099");
    }

}
