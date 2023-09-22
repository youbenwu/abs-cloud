package com.outmao.ebs.common.util;

import java.util.List;

public interface TreeData<T extends TreeData,I> {

    public I getId();

    public T getParent();

    public void setParent(T parent);

    public int getLevel();

    public void setLevel(int level);

    public boolean isLeaf();

    public void setLeaf(boolean leaf);

    public List<T> getChildren();

    public void setChildren(List<T> children);

}
