package com.outmao.ebs.common.util;


public class TreeDataUtil {


    public static <T extends TreeData> void setParent(T child, T parent){
        if(parent==null){
            removeChild(child.getParent(),child);
        }else{
            bindChild(parent,child);
        }
    }

    private static <T extends TreeData> void bindChild(T parent, T child){

        if(child.getParent()!=null){
           if(child.getParent().getId().equals(parent.getId())){
               return;
           }
           if(child.getParent().getChildren().size()==1){
               child.getParent().setLeaf(true);
           }
        }

        child.setParent(parent);
        updateLevel(child,parent.getLevel()+1);

        if(parent.isLeaf()){
            parent.setLeaf(false);
        }

    }

    private static <T extends TreeData> void removeChild(T parent, T child){
        if(parent==null)
            return;
        if(parent.getChildren().size()==1){
            parent.setLeaf(true);
        }
        child.setParent(null);
        updateLevel(child,0);
    }

    private static <T extends TreeData> void updateLevel(T data, int level){
        data.setLevel(level);
        if(!data.isLeaf()){
            for (Object d:data.getChildren()) {
                updateLevel((TreeData) d,level+1);
            }
        }
    }


}
