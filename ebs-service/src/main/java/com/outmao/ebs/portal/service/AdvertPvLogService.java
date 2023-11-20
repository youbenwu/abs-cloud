package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.SaveAdvertPvLogListDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;

import java.util.List;

public interface AdvertPvLogService {

    public AdvertPvLog saveAdvertPvLog(AdvertPvLog request);

    public List<AdvertPvLog> saveAdvertPvLogList(SaveAdvertPvLogListDTO request);

    public List<AdvertPvLog> saveAdvertPvLogListAsync(SaveAdvertPvLogListDTO request);



}
