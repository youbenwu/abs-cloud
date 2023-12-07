//package com.outmao.ebs.data.service.impl;
//
//
//import com.outmao.ebs.common.util.HttpUtil;
//import com.outmao.ebs.common.util.JsonUtil;
//import com.outmao.ebs.common.util.StringUtil;
//import com.outmao.ebs.data.domain.AreaDomain;
//import com.outmao.ebs.data.dto.AreaDTO;
//import com.outmao.ebs.data.entity.Area;
//import com.outmao.ebs.data.vo.AreaVO;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
////@Component
//public class AreaSpider {
//
//    public static void main(String[] args){
//        try {
//            AreaSpider spider=new AreaSpider();
//            spider.spider();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//
//    @Autowired
//    private AreaDomain areaService;
//
//    @Autowired
//    private LocationChsJs locationChsJs;
//
//
//    private HttpUtil httpUtil=new HttpUtil();
//    private String yearHref = "";
//    private int index;
//
//    @Async
//    public void spider() throws Exception {
//
//
//        List<String> zs=Arrays.asList(new String[]{"北京市","天津市","上海市","重庆市"});
//
//        Area cn=areaService.saveArea(new AreaDTO(null, null,0,false, "中国", null, null,null));
//
//        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/";
//        String charset = "gb2312";
//        Document rootDoc = httpUtil.get(url, charset);
//
//
//        Element firstElement = rootDoc.getElementsByClass("center_list_contlist").get(0);
//        // http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html
//        yearHref = firstElement.select("a").get(0).attr("href"); // 最近一个年份的省份链接
//        Document doc = httpUtil.get(yearHref, charset);
//        // 遍历所有的省
//        Elements provinceElements = doc.getElementsByClass("provincetr");
//        for (Element element : provinceElements) {
//            Elements aEles = element.select("a");
//            for (Element aEle : aEles) {
//                String name = aEle.text();
//                // 11.html
//                String provincesHref = aEle.attr("href");
//                String code = provincesHref.substring(0, provincesHref.indexOf("."));
//                index = yearHref.lastIndexOf("/") + 1;
//                // http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/11.html
//                provincesHref = yearHref.substring(0, index) + provincesHref;
//
//
//                System.out.println(name+"/"+code);
//
//                Area area=areaService.saveArea(new AreaDTO(null, cn.getId(),zs.contains(name)?2:1,false, name, null, null,code));
//
//                getCites(provincesHref, charset,area.getId());
//
//
////                if ("北京市".equals(name) || "天津市".equals(name) || "河北省".equals(name)) {
////                    System.out.println("未执行市:" + name);
////                } else {
////                    System.out.println("开始时间:" + LocalDateTime.now());
////                    System.out.println("省名称:" + name);
////                }
//            }
//        }
//        loadLocationChs();
//    }
//
//
//    private void getCites(String url, String charset,Long parentId) throws Exception {
//
//        Document rootDoc = null;
//        int i = 0;
//        while (rootDoc == null) {
//            try {
//                i++;
//                if (i >= 3) {
//                    System.out.println("循环次数:" + i);
//                }
//                rootDoc = httpUtil.get(url, charset);
//            } catch (Exception e) {
//                rootDoc = null;
//                System.out.println("请求网页链接报错");
//            }
//        }
//        i = 0;
//        if (rootDoc != null) {
//            Elements cityElements = rootDoc.getElementsByClass("citytr");
//            for (Element cityElement : cityElements) {
//                Element aEle = cityElement.select("a").get(1); // 第二个是市的名字
//                String name = aEle.text();
//                // 11/1101.html
//                String cityHref = aEle.attr("href");
//                int start = cityHref.lastIndexOf("/") + 1;
//                String code = cityHref.substring(start, cityHref.indexOf("."));
//                cityHref = yearHref.substring(0, index) + cityHref;
//
//                System.out.println(name+"/"+code);
//
//                Area area=areaService.saveArea(new AreaDTO(null, parentId,2,false, name, null, null,code));
//
//                getDistrict(cityHref, charset,area.getId());
//
//            }
//        }
//
//    }
//
//
//
//    // 区县
//    private void getDistrict(String url, String charset,Long parentId) throws Exception {
//
//        Document rootDoc = null;
//        int i = 0;
//        while (rootDoc == null) {
//            try {
//                i++;
//                if (i >= 3) {
//                    System.out.println("循环次数:" + i);
//                }
//                rootDoc = httpUtil.get(url, charset);
//            } catch (Exception e) {
//                rootDoc = null;
//                System.out.println("请求网页链接报错");
//            }
//        }
//        i = 0;
//        if (rootDoc != null) {
//            Elements cityElements = rootDoc.getElementsByClass("countytr");
//            for (Element cityElement : cityElements) {
//                try {
//                    Element aEle = cityElement.select("a").get(1);
//                    String name = aEle.text();
//                    String cityHref = aEle.attr("href");
//                    int start = cityHref.lastIndexOf("/") + 1;
//                    String code = cityHref.substring(start, cityHref.indexOf("."));
//
//                    int index = url.lastIndexOf("/") + 1;
//                    cityHref = url.substring(0, index) + cityHref;
//
//
//                    System.out.println(name+"/"+code);
//
//                    Area area=areaService.saveArea(new AreaDTO(null, parentId,3,false, name, null, null,code));
//
//                    getStreet(cityHref, charset,area.getId());
//
//
//                } catch (Exception e) {
//                    System.out.println("市辖区");
//                    Element aEle = cityElement.select("td").get(0);
//                    String code = aEle.text();
//
//                    Element aEle2 = cityElement.select("td").get(1);
//                    String name = aEle2.text();
//
//                    //System.out.println("执行完毕");
//
//                    System.out.println(name+"/"+code);
//
//                    areaService.saveArea(new AreaDTO(null, parentId,3,false, name, null, null,code));
//
//
//                }
//
//            }
//        }
//
//    }
//
//    // 街道
//    private List<AreaVO> getStreet(String url, String charset,Long parentId) throws Exception {
//        List<AreaVO> streets=new ArrayList<>();
//        Document rootDoc = null;
//        int i = 0;
//        while (rootDoc == null) {
//            try {
//                i++;
//                if (i >= 3) {
//                    System.out.println("循环次数:" + i);
//                }
//                rootDoc = httpUtil.get(url, charset);
//            } catch (Exception e) {
//                rootDoc = null;
//                System.out.println("请求网页链接报错");
//            }
//        }
//        i = 0;
//        if (rootDoc != null) {
//            Elements cityElements = rootDoc.getElementsByClass("towntr");
//            for (Element cityElement : cityElements) {
//                Element aEle = cityElement.select("a").get(1); // 第二个是市的名字
//                String name = aEle.text();
//                String cityHref = aEle.attr("href");
//                int start = cityHref.lastIndexOf("/") + 1;
//                String code = cityHref.substring(start, cityHref.indexOf("."));
//                int index = url.lastIndexOf("/") + 1;
//                cityHref = url.substring(0, index) + cityHref;
//                AreaVO street=new AreaVO();
//                street.setName(name);
//                street.setCode(code);
//                streets.add(street);
//
//                Area area=areaService.saveArea(new AreaDTO(null, parentId,4,false, name, null, null,code));
//
//                //street.setChildren(getCommunity(cityHref, charset,area.getId()));
//
//                System.out.println(name+"/"+code);
//            }
//        }
//        return streets;
//    }
//
//    // 社区
//    private List<AreaVO> getCommunity(String url, String charset,Long parentId) throws Exception {
//        List<AreaVO> communitys=new ArrayList<>();
//        Document rootDoc = null;
//        int i = 0;
//        while (rootDoc == null) {
//            try {
//                i++;
//                if (i >= 3) {
//                    System.out.println("循环次数:" + i);
//                }
//                rootDoc = httpUtil.get(url, charset);
//            } catch (Exception e) {
//                rootDoc = null;
//                System.out.println("请求网页链接报错");
//            }
//        }
//        i = 0;
//        if (rootDoc != null) {
//            Elements cityElements = rootDoc.getElementsByClass("villagetr");
//            for (Element cityElement : cityElements) {
//                Element aEle = cityElement.select("td").get(0);
//                String code = aEle.text();
//
//                Element aEle2 = cityElement.select("td").get(1);
//                String cl_code = aEle2.text();
//
//                Element aEle3 = cityElement.select("td").get(2);
//                String name = aEle3.text();
//
//                AreaVO community=new AreaVO();
//                community.setName(name);
//                community.setCode(code);
//                communitys.add(community);
//
//                areaService.saveArea(new AreaDTO(null, parentId,5,false, name, null, null,code));
//
//                System.out.println(name+"/"+code);
//
//            }
//        }
//        return communitys;
//    }
//
//
//    /**
//     *
//     * 从这里获取国外地区数据 国家-省-市
//     *
//     * http://4.url.cn/zc/chs/js/10062/location_chs.js
//     *
//     * */
//    private void loadLocationChs(){
//
//        String content=locationChsJs.get();
//        content=content.substring("initLocation(".length(),content.length()-");".length()-1);
//        content=content.replace(",0:{n:\"\",0:{n:\"\",0:{n:\"\"}}}","");
//        content= StringUtil.unicodeToString(content);
//        content=StringUtil.converJson(content);
//        //System.out.println(content);
//
//        Map data= JsonUtil.fromJson(content,Map.class);
//
//        System.out.println(data);
//
//        for(Object t1:data.values()){
//            Map c=(Map)t1;
//            Area cArea=null;
//            for(Object t2:c.values()){
//                if(cArea!=null&&cArea.getName().equals("中国")){
//                    continue;
//                }
//                if(t2 instanceof String){
//                    cArea = areaService.saveArea(new AreaDTO(null,null,0,true,t2.toString(),null,null,null));
//                }else{
//                    Map p=(Map)t2;
//                    Area pArea=null;
//                    for(Object t3:p.values()){
//                        if(t3 instanceof String){
//                            if(t3.toString().length()>0) {
//                                pArea = areaService.saveArea(new AreaDTO(null, cArea.getId(),1,true, t3.toString(), null, null,null));
//                            }else{
//                                pArea=new Area();
//                            }
//                        }else{
//                            Map s=(Map)t3;
//                            String city=s.get("n").toString();
//                            if(city.length()>0) {
//                                areaService.saveArea(new AreaDTO(null, pArea.getId()!=null?pArea.getId():cArea.getId(),2,true, city, null, null,null));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//
//
//
//}
