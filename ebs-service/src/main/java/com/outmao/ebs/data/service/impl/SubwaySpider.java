package com.outmao.ebs.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.outmao.ebs.common.services.amap.AmapClient;
import com.outmao.ebs.common.services.amap.vo.AddressComponent;
import com.outmao.ebs.common.services.amap.vo.RegeocodeResult;
import com.outmao.ebs.common.util.HttpUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.data.dto.SubwayDTO;
import com.outmao.ebs.data.entity.Subway;
import com.outmao.ebs.data.service.SubwayService;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class SubwaySpider {

    public static void main(String[] args){
        try {
            SubwaySpider spider=new SubwaySpider();
            spider.spider();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Autowired
    private AmapClient amapClient;

    @Autowired
    private SubwayService subwayService;


    private HttpUtil httpUtil=new HttpUtil();


    @Async
    public void spider() throws Exception {

        String url = "http://map.amap.com/subway/index.html";
        String charset = "gb2312";
        Document rootDoc = httpUtil.get(url, charset);

        Elements elements=rootDoc.select("a");

        elements.forEach(t->{
            String cityId=t.attr("id");
            String cityName=t.attr("cityname");
            if(StringUtil.isNotEmpty(cityName)){
                try {
                    getCitySubway(cityId,cityName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }


    private void getCitySubway(String cityId,String cityName)throws Exception{
        Date now=new Date();
        String url = "http://map.amap.com/service/subway?_"+now.getTime()+"&srhdata="+cityId+"_drw_"+cityName+".json";
        System.out.println();
        String json = httpUtil.get(url);

        //System.out.println(json);

        JSONObject object= JSON.parseObject(json);

        String name=object.getString("s");

        System.out.println(name);

        Subway subway=subwayService.saveSubway(new SubwayDTO(null,null,name,null,null));

        JSONArray lines=object.getJSONArray("l");

        //保存城市

        lines.forEach(t->{
            getSubwayLine((JSONObject) t,subway.getId());
        });

    }


    public void getSubwayLine(JSONObject line,Long parentId){
        String name=line.getString("ln");
        System.out.println(name);

        //保存线路

        Subway subway=subwayService.saveSubway(new SubwayDTO(null,parentId,name,null,null));


        JSONArray nodes=line.getJSONArray("st");

        nodes.forEach(t->{
            getSubwayItem((JSONObject)t,subway.getId());
        });

    }


    public void getSubwayItem(JSONObject node,Long parentId){
        String name=node.getString("n");
        String sl=node.getString("sl");
        String sp=node.getString("sp");
        String[] latLon=sl.split(",");
        System.out.println(name+"/"+sp+"/"+sl);
        SubwayDTO data=new SubwayDTO();
        data.setParentId(parentId);
        data.setName(name);
        data.setLatitude(Double.parseDouble(latLon[1]));
        data.setLongitude(Double.parseDouble(latLon[0]));
        RegeocodeResult r=amapClient.regeocode(sl);

        if(r.isSuccess()){
            AddressComponent addr=r.getRegeocode().getAddressComponent();
            BeanUtils.copyProperties(addr,data);
        }
        subwayService.saveSubway(data);
    }


}
