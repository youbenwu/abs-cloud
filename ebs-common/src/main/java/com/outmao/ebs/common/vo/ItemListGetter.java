package com.outmao.ebs.common.vo;


import java.util.Collection;
import java.util.List;

public interface ItemListGetter<T extends IItem> {

    List<T> getItemListByIdIn(Collection<Long> idIn);


}
