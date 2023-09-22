package com.outmao.ebs.bbs.common.data;

import java.util.Collection;
import java.util.List;

public interface GetSubjectItemList<T extends SubjectItem> {

    List<T> getSubjectItemList(Collection<Long> idIn);

}
