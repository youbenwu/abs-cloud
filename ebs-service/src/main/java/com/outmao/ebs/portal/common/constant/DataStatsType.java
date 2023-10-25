package com.outmao.ebs.portal.common.constant;

import lombok.Getter;

@Getter
public enum DataStatsType {

    TicketOrderAmountTotal(11, "门票订单总金额",DataStatsGroup.TicketOrderAmountStats.getGroup(),"元"),
    TicketOrderAmount7Day(12, "门票订单最近7天金额",DataStatsGroup.TicketOrderAmountStats.getGroup(),"元"),
    TicketOrderAmountMonth(13, "门票订单当月金额",DataStatsGroup.TicketOrderAmountStats.getGroup(),"元"),
    TicketOrderCountTotal(21, "门票订单总数量",DataStatsGroup.TicketOrderCountStats.getGroup(),""),
    TicketOrderCount7Dat(22, "门票订单最近7天数量",DataStatsGroup.TicketOrderCountStats.getGroup(),""),
    TicketOrderCountMonth(23, "门票订单当月数量",DataStatsGroup.TicketOrderCountStats.getGroup(),""),
    TicketOrderCountDay(24,"门票订单当天数量",DataStatsGroup.TicketOrderCountStats.getGroup(), ""),
    UserActiveDay(31, "日活跃用户",DataStatsGroup.UserStats.getGroup(),""),
    UserActiveMonth(32, "月活跃用户",DataStatsGroup.UserStats.getGroup(),""),
    UserAddDay(41, "日新增用户",DataStatsGroup.UserStats.getGroup(),""),
    UserAddMonth(42,"月新增用户",DataStatsGroup.UserStats.getGroup(), ""),
    HotelDeviceCount(51,"总投放设备数量",DataStatsGroup.HotelDeviceStats.getGroup(), ""),
    HotelDeviceSafeIndex(52, "安全指数",DataStatsGroup.HotelDeviceStats.getGroup(),""),
    HotelDeviceNormalCount(53, "设备正常运行总数",DataStatsGroup.HotelDeviceStats.getGroup(),""),
    HotelDeviceFaultCount(54, "设备故障总数",DataStatsGroup.HotelDeviceStats.getGroup(),""),
    HotelDeviceWarnCount(55, "待处理预警总数",DataStatsGroup.HotelDeviceStats.getGroup(),""),
    HotelDeviceWarnHandleRate(56, "预警处理率",DataStatsGroup.HotelDeviceStats.getGroup(),"");

    private int type;
    private String group;
    private String desc;

    private String suffix;

    DataStatsType(int type, String desc,String group,String suffix) {
        this.type = type;
        this.group=group;
        this.desc = desc;
        this.suffix=suffix;
    }

}
