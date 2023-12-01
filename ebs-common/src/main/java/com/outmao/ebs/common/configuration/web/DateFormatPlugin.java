package com.outmao.ebs.common.configuration.web;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 支持多种时间格式转换，使用方法
 * <code>
 * spring:
 *   jackson:
 *     date-format: com.yc.kfpt.webcommons.plugin.DateFormatPlugin
 * </code>
 * @author 敖癸
 * @formatter:on
 * @since 2022/11/4
 */
public class DateFormatPlugin extends SimpleDateFormat {

    private static final List<DateFormat> FORMATS = new ArrayList<>(5);
    private static final String YYYY_MM = "^\\d{4}-\\d{1,2}$";
    private static final String YYYY_MM_DD = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    private static final String YYYY_MM_DD_HH_MM = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$";
    private static final String YYYY_MM_DD_HH_MM_SS = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";
    private static final String YYYY_MM_DD_HH_MM_SS_SSS = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{3}$";

    static {
        FORMATS.add(new SimpleDateFormat("yyyy-MM"));
        FORMATS.add(new SimpleDateFormat("yyyy-MM-dd"));
        FORMATS.add(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        FORMATS.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        FORMATS.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return FORMATS.get(3).format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches(YYYY_MM)) {
            return FORMATS.get(0).parse(source, pos);
        } else if (source.matches(YYYY_MM_DD)) {
            return FORMATS.get(1).parse(source, pos);
        } else if (source.matches(YYYY_MM_DD_HH_MM)) {
            return FORMATS.get(2).parse(source, pos);
        } else if (source.matches(YYYY_MM_DD_HH_MM_SS)) {
            return FORMATS.get(3).parse(source, pos);
        } else if (source.matches(YYYY_MM_DD_HH_MM_SS_SSS)) {
            return FORMATS.get(4).parse(source, pos);
        } else {
            throw new IllegalArgumentException("Invalid datetime value " + source);
        }
    }

}

