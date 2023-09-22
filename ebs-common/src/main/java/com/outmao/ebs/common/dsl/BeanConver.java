package com.outmao.ebs.common.dsl;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.EntityPathBase;


public interface BeanConver<Q extends EntityPathBase<?>,B> {

    public B fromTuple(Tuple t, Q q);

    public Expression<?>[] select(Q q);


}
