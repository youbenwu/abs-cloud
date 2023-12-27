package com.outmao.ebs.portal.common.constant;

import lombok.Getter;

@Getter
public enum DataStatsType {

    TicketOrderAmountTotal(11, "门票订单总金额", DataStatsChannel.TicketOrderAmountStats.getChannel(),"元"),
    TicketOrderAmount7Day(12, "门票订单最近7天金额", DataStatsChannel.TicketOrderAmountStats.getChannel(),"元"),
    TicketOrderAmountMonth(13, "门票订单当月金额", DataStatsChannel.TicketOrderAmountStats.getChannel(),"元"),
    TicketOrderCountTotal(21, "门票订单总数量", DataStatsChannel.TicketOrderCountStats.getChannel(),""),
    TicketOrderCount7Dat(22, "门票订单最近7天数量", DataStatsChannel.TicketOrderCountStats.getChannel(),""),
    TicketOrderCountMonth(23, "门票订单当月数量", DataStatsChannel.TicketOrderCountStats.getChannel(),""),
    TicketOrderCountDay(24,"门票订单当天数量", DataStatsChannel.TicketOrderCountStats.getChannel(), ""),
    UserActiveDay(31, "日活跃用户", DataStatsChannel.UserStats.getChannel(),""),
    UserActiveMonth(32, "月活跃用户", DataStatsChannel.UserStats.getChannel(),""),
    UserAddDay(41, "日新增用户", DataStatsChannel.UserStats.getChannel(),""),
    UserAddMonth(42,"月新增用户", DataStatsChannel.UserStats.getChannel(), ""),
    HotelDeviceCount(51,"总投放设备数量", DataStatsChannel.HotelDeviceStats.getChannel(), ""),
    HotelDeviceSafeIndex(52, "安全指数", DataStatsChannel.HotelDeviceStats.getChannel(),""),
    HotelDeviceNormalCount(53, "设备使用率", DataStatsChannel.HotelDeviceStats.getChannel(),""),
    HotelDeviceFaultCount(54, "设备坏点率", DataStatsChannel.HotelDeviceStats.getChannel(),""),
    HotelDeviceWarnCount(55, "待处理预警总数", DataStatsChannel.HotelDeviceStats.getChannel(),""),
    HotelDeviceWarnHandleRate(56, "预警处理率", DataStatsChannel.HotelDeviceStats.getChannel(),""),


    HotelDeviceIncomeV(70, "CPM曝光收益", DataStatsChannel.HotelDeviceIncomeStats.getChannel(),""),
    HotelDeviceIncomePV(71, "CPS转化收益", DataStatsChannel.HotelDeviceIncomeStats.getChannel(),""),
    HotelDeviceIncomeCPA(72, "CPA分佣收益", DataStatsChannel.HotelDeviceIncomeStats.getChannel(),""),
    HotelDeviceIncomeAPP(73, "系统应用订阅收益", DataStatsChannel.HotelDeviceIncomeStats.getChannel(),""),

    HotelDeviceIncomeLY(74, "系统旅游订阅收益", DataStatsChannel.HotelDeviceIncomeStats.getChannel(),""),
    HotelDeviceIncomeYSDB(75, "影视点播收益", DataStatsChannel.HotelDeviceIncomeStats.getChannel(),""),
    HotelDeviceIncomeKFFW(76, "客房服务收益", DataStatsChannel.HotelDeviceIncomeStats.getChannel(),""),
    HotelDeviceIncomeBDSH(77, "本地生活收益", DataStatsChannel.HotelDeviceIncomeStats.getChannel(),"");




    private int type;
    private String channel;
    private String desc;

    private String suffix;

    DataStatsType(int type, String desc,String channel,String suffix) {
        this.type = type;
        this.channel=channel;
        this.desc = desc;
        this.suffix=suffix;
    }

}
