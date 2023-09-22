package com.outmao.ebs.common.dsl;

import com.querydsl.core.Tuple;

@Deprecated
public interface DslVO<Q> {

    public DslVO<Q> fromTuple(Tuple t, Q e);

}
