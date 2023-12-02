package com.outmao.ebs.jnet.vo.warehouse;

import java.util.ArrayList;
import java.util.List;

public class WarehouseStatisVO {

    private List<String> date;

    private List<WarehouseStatisSubVO> datas;

    public WarehouseStatisVO(){
        datas=new ArrayList<>();
        date=new ArrayList<>();
    }


    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<WarehouseStatisSubVO> getDatas() {
        return datas;
    }

    public void setDatas(List<WarehouseStatisSubVO> datas) {
        this.datas = datas;
    }
}
