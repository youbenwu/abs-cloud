package com.outmao.ebs.portal.common.constant;

import lombok.Getter;

@Getter
public enum DataStatsChannel {

    TicketOrderAmountStats("ticket-order-amount-stats", "门票订单金额统计"),
    TicketOrderCountStats("ticket-order-count-stats", "门票订单数量统计"),
    UserStats("user-stats", "用户相关统计"),
    HotelDeviceStats("hotel-device-stats", "酒店设备统计");

    private String channel;
    private String desc;

    DataStatsChannel(String channel, String desc) {
        this.channel = channel;
        this.desc = desc;
    }

}
