package com.outmao.ebs.mall.product.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品规格组合算法
 */
public class SpecAlgorithm<T>{

    /**
     * 商品规格组合 T 泛型不限对象类型，可以是基本数据类型，也可以是String 或自定义类
     *
     * @param lists 设置的规格（传入的参数数量应对的是多少级的规格,T为声明的规格对象）
     * @return 返回的组合集合  数量公式 AxBxC...=N;
     */
    public  List<List<T>> specesPlan(List<T>...lists){

        List<List<T>> arrays = new ArrayList<>();
        if(lists.length == 0 ){
            return arrays;
        }
        for(T object :lists[0]){
            List<T> newObj = new ArrayList<>();
            List<List<T>> arrayList = new ArrayList<>();
            for(int i = 1 ; i < lists.length ; i ++){
                arrayList.add(lists[i]);
            }
            addPlan(arrays,newObj,object,arrayList);
        }
        return arrays;
    }

    /**
     *
     * @param arrs      要返回的集合
     * @param newObjs  规划表
     * @param object    上一级的对象
     * @param lists     下级的集合
     */
    private void addPlan(List<List<T>> arrs, List<T> newObjs, T object, List<List<T>> lists){
        if(lists.size() == 0 || lists.get(0).size() == 0){
            newObjs.add(object);
            arrs.add(newObjs);
        }else {
            newObjs.add(object);
            for(T obj :lists.get(0)){
                List<List<T>> arrays = new ArrayList<>();
                for(int i = 1 ; i < lists.size() ; i ++){
                    arrays.add(lists.get(i));
                }
                ArrayList<T> objects = new ArrayList<>();
                objects.addAll(newObjs);
                addPlan(arrs, objects,obj,arrays);
            }
        }
    }

}
