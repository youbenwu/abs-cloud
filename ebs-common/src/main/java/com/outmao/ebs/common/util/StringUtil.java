package com.outmao.ebs.common.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    public static boolean isEmpty(String str){
        return (str==null||str.isEmpty());
    }

    public static boolean isNotEmpty(String str){
        return (str!=null&&str.length()>0);
    }

    public static String toPinyin(String str){
        try {
            return Pinyin4jUtil.toPinYinLowercase(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        return str;
    }


    public static String converJson(String str){
        if(str==null)
            return null;
        return  str.replaceAll("(\\w+):","\"$1\":");
    }


    public static double format2f(Double v){
        if(v==null)
            return 0;
        return Double.parseDouble(String.format("%.2f",v));
    }


}
