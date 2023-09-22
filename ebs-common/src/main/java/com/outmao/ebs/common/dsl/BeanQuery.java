package com.outmao.ebs.common.dsl;


import com.outmao.ebs.common.exception.BusinessException;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class BeanQuery {

    @Autowired
    protected JPAQueryFactory QF;


    private OrderSpecifier<?> toOrderSpecifier(Path<?> path, Sort.Order order){
        Path<Object> sortPropertyExpression = Expressions.path(Object.class, path, order.getProperty());
        com.querydsl.core.types.Order orderExpression = order.isAscending() ? com.querydsl.core.types.Order.ASC
                : com.querydsl.core.types.Order.DESC;
        return new OrderSpecifier(orderExpression, sortPropertyExpression);
    }

    protected JPAQuery<?> setPageable(Path<?> path, JPAQuery<?> query, Pageable pageable){
        query= query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        if (pageable.getSort() != null) {
            for (Sort.Order order : pageable.getSort()) {
                query = query.orderBy(toOrderSpecifier(path,order));
            }
        }
        return query;
    }


    protected <Q extends EntityPathBase<?>,B> B queryOne(Q q, Predicate p, BeanConver<Q,B> conver){
        Tuple t=QF.select(conver.select(q)).from(q).where(p).fetchOne();
        if(t!=null)
            return conver.fromTuple(t,q);
        return null;
    }

    protected <Q extends EntityPathBase<?>,B> B queryOne(Q q, JPAQuery<Tuple> query, BeanConver<Q,B> conver){
        Tuple t=query.fetchOne();
        if(t!=null)
            return conver.fromTuple(t,q);
        return null;
    }

    protected <Q extends EntityPathBase<?>,B> Page<B> queryPage(Q q, BeanConver<Q,B> conver, Pageable pageable){
        JPAQuery<Tuple> query=QF.select(conver.select(q)).from(q);
        return queryPage(q,query,conver,pageable);
    }

    protected <Q extends EntityPathBase<?>,B> Page<B> queryPage(Q q, Predicate p, BeanConver<Q,B> conver, Pageable pageable){
        JPAQuery<Tuple> query=QF.select(conver.select(q)).from(q).where(p);
        return queryPage(q,query,conver,pageable);
    }

    protected <Q extends EntityPathBase<?>,B> Page<B> queryPage(Q q, Predicate p, BeanConver<Q,B> conver, Pageable pageable,OrderSpecifier orderBy){
        JPAQuery<Tuple> query=QF.select(conver.select(q)).from(q).where(p);
        return queryPage(q,query,conver,pageable,orderBy);
    }


    protected <Q extends EntityPathBase<?>,B> Page<B> queryPage(Q q, JPAQuery<Tuple> query, BeanConver<Q,B> conver, Pageable pageable){
        return queryPage(q,query,conver,pageable,null);
    }

    protected <Q extends EntityPathBase<?>,B> Page<B> queryPage(Q q, JPAQuery<Tuple> query, BeanConver<Q,B> conver, Pageable pageable,OrderSpecifier orderBy){
        setPageable(q,query,pageable);
        if(orderBy!=null)
        query.orderBy(orderBy);
        long total=query.fetchCount();
        List<Tuple> list=query.fetch();
        List<B> content=new ArrayList<>(list.size());
        for (Tuple t:list) {
            content.add(conver.fromTuple(t,q));
        }
        Page<B> page=new PageImpl(content,pageable,total);
        return page;
    }

    protected <T> Page<T> queryPage(Path<T> q,JPAQuery<T> query,Pageable pageable){
        setPageable(q,query,pageable);
        long total=query.fetchCount();
        List<T> list=query.fetch();
        Page<T> page=new PageImpl(list,pageable,total);
        return page;
    }

    protected <Q extends EntityPathBase<?>,B> List<B> queryList(Q q, Predicate p, BeanConver<Q,B> conver, Pageable pageable){
        JPAQuery<Tuple> query=QF.select(conver.select(q)).from(q).where(p);
        setPageable(q,query,pageable);
        return queryList(q,query,conver);
    }

    protected <Q extends EntityPathBase<?>,B> List<B> queryList(Q q, Predicate p, BeanConver<Q,B> conver){
        JPAQuery<Tuple> query=QF.select(conver.select(q)).from(q).where(p);
        return queryList(q,query,conver);
    }

    protected <Q extends EntityPathBase<?>,B> List<B> queryList(Q q, Predicate p,OrderSpecifier o, BeanConver<Q,B> conver){
        JPAQuery<Tuple> query=QF.select(conver.select(q)).from(q).where(p).orderBy(o);
        return queryList(q,query,conver);
    }

    protected <Q extends EntityPathBase<?>,B> List<B> queryList(Q q, JPAQuery<Tuple> query, BeanConver<Q,B> conver, Pageable pageable){
        setPageable(q,query,pageable);
        return queryList(q,query,conver);
    }

    protected <Q extends EntityPathBase<?>,B> List<B> queryList(Q q, JPAQuery<Tuple> query, BeanConver<Q,B> conver){
        List<Tuple> list=query.fetch();
        List<B> content=new ArrayList<>(list.size());
        for (Tuple t:list) {
            content.add(conver.fromTuple(t,q));
        }
        return content;
    }



    @Deprecated
    protected <T> Page<T> toPage(Path<?> path, JPAQuery<T> query, Pageable pageable){
        query=(JPAQuery<T>)setPageable(path,query,pageable);
        long total=query.fetchCount();
        List<T> content=query.fetch();
        Page<T> page=new PageImpl<T>(content,pageable,total);
        return page;
    }

    @Deprecated
    protected <S,T> Page<T> toPage(Path<?> path, JPAQuery<S> query, Pageable pageable, Function<S,T> function){
        query=(JPAQuery<S>)setPageable(path,query,pageable);
        long total=query.fetchCount();
        List<S> list=query.fetch();
        List<T> content=new ArrayList<>(list.size());
        for (S s:list) {
            content.add(function.apply(s));
        }
        Page<T> page=new PageImpl<T>(content,pageable,total);
        return page;
    }

    @Deprecated
    protected <T extends DslVO<Q>,Q> Page<T> toPage(JPAQuery<Tuple> query, Pageable pageable, Class<T> voClass, Q q){
        query=(JPAQuery<Tuple>)setPageable((Path<?>) q,query,pageable);
        long total=query.fetchCount();
        List<Tuple> list=query.fetch();
        List<T> content=new ArrayList<>(list.size());
        for (Tuple t:list) {
            try {
                T vo=voClass.newInstance();
                content.add((T)vo.fromTuple(t,q));
            }catch (Exception e){
                e.printStackTrace();
                throw new BusinessException("发生未知错误");
            }
        }
        Page<T> page=new PageImpl<T>(content,pageable,total);
        return page;
    }

    @Deprecated
    protected <T extends DslVO<Q>,Q>List<T> toList(JPAQuery<Tuple> query, Class<T> voClass, Q q){
        List<Tuple> list=query.fetch();
        List<T> content=new ArrayList<>(list.size());
        for (Tuple t:list) {
            try {
                T vo=voClass.newInstance();
                content.add((T)vo.fromTuple(t,q));
            }catch (Exception e){
                e.printStackTrace();
                throw new BusinessException("发生未知错误");
            }
        }
        return content;
    }


}
