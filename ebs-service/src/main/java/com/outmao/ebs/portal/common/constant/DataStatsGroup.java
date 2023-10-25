package com.outmao.ebs.portal.common.constant;

import lombok.Getter;

@Getter
public enum DataStatsGroup {

    TicketOrderAmountStats("ticket-order-amount-stats", "门票订单金额统计"),
    TicketOrderCountStats("ticket-order-count-stats", "门票订单数量统计"),
    UserStats("user-stats", "用户相关统计"),
    HotelDeviceStats("hotel-device-stats", "酒店设备统计");

    private String group;
    private String desc;

    DataStatsGroup(String group, String desc) {
        this.group = group;
        this.desc = desc;
    }

}
