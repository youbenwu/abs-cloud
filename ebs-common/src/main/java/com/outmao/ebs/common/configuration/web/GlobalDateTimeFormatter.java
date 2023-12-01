//package com.outmao.ebs.common.configuration.web;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.format.datetime.DateFormatter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class GlobalDateTimeFormatter implements WebMvcConfigurer {
//
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        List<String> dateFormats= Arrays.asList("yyyy-MM","yyyy-MM-dd","yyyy-MM-dd HH:mm:ss");
//        for (String dateFormat:dateFormats){
//            registry.addFormatter(new DateFormatter(dateFormat));
//        }
//    }
//
//
//}
