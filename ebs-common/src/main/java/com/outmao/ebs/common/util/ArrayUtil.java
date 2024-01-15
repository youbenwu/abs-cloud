package com.outmao.ebs.common.util;


import com.outmao.ebs.common.exception.BusinessException;
import org.springframework.util.StringUtils;

import java.util.*;

public class ArrayUtil {


    public  static <T>T[] merge(T[][] ts){
        int length=0;
        for(T[] t:ts){
            length+=t.length;
        }
        T[] arr= Arrays.copyOf(ts[0],length);
        int index=ts[0].length;
        for(int i=1;i<ts.length;i++){
            T[] t=ts[i];
            System.arraycopy(t, 0, arr, index, t.length);
            index+=t.length;
        }
        return arr;
    }

    public  static <T>T[] merge(T[] t1,T[] t2){
        T[] arr= Arrays.copyOf(t1,t1.length+t2.length);
        System.arraycopy(t2, 0, arr, t1.length, t2.length);
        return arr;
    }

    public  static <T>T[] merge(T[] t1,T[] t2,T[] t3){
        T[] arr= Arrays.copyOf(t1,t1.length+t2.length+t3.length);
        System.arraycopy(t2, 0, arr, t1.length, t2.length);
        System.arraycopy(t3, 0, arr, t1.length+t2.length, t3.length);
        return arr;
    }

    public  static String merge(String str1,String str2){
        Set<String> set=new HashSet<>();
        if(!StringUtils.isEmpty(str1)){
            String[] arr1=str1.split(",");
            for (String s:arr1) {
                set.add(s);
            }
        }
        if(!StringUtils.isEmpty(str2)){
            String[] arr2=str2.split(",");
            for (String s:arr2) {
                set.add(s);
            }
        }
        String str="";
        Iterator<String> iterator=set.iterator();
        while (iterator.hasNext()){
            str+=","+iterator.next();
        }
        if(str.length()>0){
            str=str.substring(1);
            return str;
        }
        return null;
    }

    public  static String[] listToArray(List list){
        String[] ts=new String[list.size()];
        for(int i=0;i<ts.length;i++){
            ts[i]=list.get(i).toString();
        }
        return ts;
    }

    public  static int[] stringToIntArray(String str){
        if(str==null||str.isEmpty())
            return null;
        String[] list=str.split(",");
        int[] ts=new int[list.length];
        for(int i=0;i<ts.length;i++){
            ts[i]=Integer.parseInt(list[i].toString());
        }
        return ts;
    }

    public  static Integer[] stringToIntegerArray(String str){
        if(str==null||str.isEmpty())
            return null;
        String[] list=str.split(",");
        Integer[] ts=new Integer[list.length];
        for(int i=0;i<ts.length;i++){
            ts[i]=Integer.parseInt(list[i].toString());
        }
        return ts;
    }


    public static List<String> arrayToList(String[] arr){
        List<String> list=new ArrayList<>();
        for (String str:arr) {
            list.add(str);
        }
        return list;
    }

    public static double[] stringToDoubleArray(String str){
        if(str==null||str.length()==0){
            return null;
        }
        String[] s=str.split(",");
        double[] arr=new double[s.length];
        for(int i=0;i<arr.length;i++){
            arr[i]=Double.parseDouble(s[i]);
        }
        return arr;
    }

    public static List<String> listAddArray(List<String> list,String[] arr){
        if(arr==null||arr.length==0)
            return list;
        if(list==null){
            list=new ArrayList<>();
        }
        for(String s:arr){
            if(!list.contains(s)){
                list.add(s);
            }
        }
        return list;
    }

    public static List<String> stringToList(String str){
        if(str==null||str.length()==0){
            return null;
        }
        String[] s=str.split(",");
        List<String> list=new ArrayList<>(s.length);
        for(int i=0;i<s.length;i++){
            list.add(s[i]);
        }
        return list;
    }

    public static List<Double> stringToDoubleList(String str){
        if(str==null||str.length()==0){
            return null;
        }
        String[] s=str.split(",");
        List<Double> list=new ArrayList<>(s.length);
        for(int i=0;i<s.length;i++){
            list.add(Double.parseDouble(s[i]));
        }
        return list;
    }

    public static List<Long> stringToLongList(String str){
        if(str==null||str.length()==0){
            return null;
        }
        String[] s=str.split(",");
        List<Long> list=new ArrayList<>(s.length);
        for(int i=0;i<s.length;i++){
            list.add(Long.parseLong(s[i]));
        }
        return list;
    }

    public  static  String listToString(List list){
        if(list==null||list.isEmpty())
            return null;
        String str="";
        for(int i=0;i<list.size();i++){
            str+=","+list.get(i);
        }
        if(str.length()>0){
            str=str.substring(1);
            return str;
        }
        return null;
    }



    public static int[] random(int total,int count,int min,int max){
        int[] arr=new int[count];
        int t=total-min*count;
        if(t<0){
            throw new BusinessException("数据出错");
        }
        int m=max-min;
        if(m<=0){
            throw new BusinessException("数据出错");
        }
        for(int i=0;i<count;i++){
            if(t>0) {
                int one = new Random().nextInt(Math.min(m,t));
                t -= one;
                arr[i] = min + one;
            }else{
                arr[i] = min;
            }
        }
        if(t>0){
            int i=new Random().nextInt(count);
            arr[i]=arr[i]+t;
        }
        return arr;
    }

    public static int[] splitInteger(int sum,int count,boolean flag) {
        //随机抽取n-1个小于sum的数
        List<Integer> list = new ArrayList();
        //将0和sum加入到里list中
        list.add(0);
        //判断生成的正整数集合中是否允许为0，true元素可以为0  false元素不可以为0
        if (!flag) {
            sum = sum - count;
        }
        list.add(sum);
        int temp = 0;
        for (int i = 0; i < count - 1 ; i++) {
            temp = (int) (Math.random() * sum);
            list.add(temp);
        }
        Collections.sort(list);
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = list.get(i + 1) - list.get(i);
            if (!flag) {
                nums[i] += 1;
            }
        }
        return nums;
    }



}
