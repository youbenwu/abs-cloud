package com.outmao.ebs.common.vo;

import com.outmao.ebs.common.vo.IItem;

import java.util.Collection;
import java.util.List;

public interface DataItemGetter<T extends IItem> {

    List<T> getDataItemListByIdIn(Collection<Long> idIn);


}
