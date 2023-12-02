package com.outmao.ebs.jnet.vo.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseStatisSubVO {

    private Map<String,Object> user;

    private List<Double> materialNum;

    public WarehouseStatisSubVO(){
        materialNum=new ArrayList<>();
        user=new HashMap<>();
    }

    public void setUser(String name,Long id){
        user.put("name",name);
        user.put("id",id);
    }

    public Map<String, Object> getUser() {
        return user;
    }

    public void setUser(Map<String, Object> user) {
        this.user = user;
    }

    public List<Double> getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(List<Double> materialNum) {
        this.materialNum = materialNum;
    }
}
