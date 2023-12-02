package com.outmao.ebs.jnet.domain.order.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.jnet.dao.order.OrderDao;
import com.outmao.ebs.jnet.dao.order.TaskDao;
import com.outmao.ebs.jnet.domain.order.ZOrderDomain;
import com.outmao.ebs.jnet.dto.order.OrderParamsDTO;
import com.outmao.ebs.jnet.entity.order.Order;
import com.outmao.ebs.jnet.entity.order.QOrder;
import com.outmao.ebs.jnet.entity.order.QTask;
import com.outmao.ebs.jnet.entity.order.Task;
import com.outmao.ebs.jnet.vo.order.OrderVO;
import com.outmao.ebs.jnet.vo.order.TaskVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
@Component("ZOrderDomain")
public class ZOrderDomainImpl extends BaseDomain implements ZOrderDomain {

    @Autowired
    OrderDao orderDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    UserDao userDao;



    @Override
    public Order saveOrder(OrderParamsDTO params) {

        Order order=params.getId()==null?null:orderDao.getOne(params.getId());

        if(order==null){
            order=new Order();
            order.setUser(userDao.getOne(params.getUserId()));
            order.setCreateTime(new Date());
        }

        if(params.getProducerId()!=null){
            order.setProducer(userDao.getOne(params.getProducerId()));
        }

        BeanUtils.copyProperties(params,order);

        order.setUpdateTime(new Date());
        order=orderDao.save(order);

        //下单人和生产者是同一个人，直接进入生产中
        if(order.getProducer()!=null&&order.getProducer().equals(order.getUser())){
            if(order.getStatus()==0){
                updateOrderStatus(order.getId(),2,0);
            }
        }

        //给管理员创建一个任务
        updateTaskByOrder(order);

        return order;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }

    @Override
    public Order updateOrderStatus(Long orderId, int status, int subStatus) {
        synchronized (this){
            Order order=orderDao.getOne(orderId);
            if(status==0){
                //预下单
                if(order.getStatus()!=0){
                    throw new BusinessException("不是预下单，不能修改子状态");
                }
                // 订单子状态，status=0：0：待管家接受 1：管家已拒绝 2：待管家确定参考单价 3：待管家确定单价
                // 4:待客户确认单价 5:客户已拒绝单价 6：待客户编辑色组下单
                switch (subStatus){
                    case 1:
                    {
                        //管家已拒绝
                        if(order.getSubStatus()!=0){
                            throw new BusinessException("不是'待管家接受'状态，不能设置'管家已拒绝'状态");
                        }
                        break;
                    }
                    case 2:
                    {
                        //待管家确定参考单价
                        if(order.getSubStatus()!=0){
                            throw new BusinessException("不是'待管家接受'状态，不能设置'待管家确定参考单价'状态");
                        }
                        break;
                    }case 3:
                    {
                        //待管家确定单价
                        if(order.getSubStatus()!=2){
                            throw new BusinessException("不是'待管家确定参考单价'状态，不能设置'待管家确定单价'状态");
                        }
                        break;
                    }
                    case 4:
                    {
                        //待客户确认单价
                        if(order.getSubStatus()!=5&&order.getSubStatus()!=3){
                            throw new BusinessException("不是'待管家确定单价'状态，不能设置'待客户确认单价'状态");
                        }
                        break;
                    }
                    case 5:
                    {
                        //客户已拒绝单价
                        if(order.getSubStatus()!=4){
                            throw new BusinessException("不是'待客户确认单价'状态，不能设置'客户已拒绝单价'状态");
                        }
                        break;
                    }
                    case 6:
                    {
                        //待客户编辑色组下单
                        if(order.getSubStatus()!=4){
                            throw new BusinessException("不是'待客户确认单价'状态，不能设置'待客户编辑色组下单'状态");
                        }
                        break;
                    }
                    default:{
                        throw new BusinessException("不支持的订单状态");
                    }
                }
            }else if(status==1){
                //// 订单状态，0：预下单 1：预付款 2：生产中 3：待结算 4：已完成 5：待退单 6：待清算 7：已退单 8：已撤单 9：已删除
                //预付款
                if(order.getStatus()!=0){
                    throw new BusinessException("不是'预下单'状态，不能设置'预付款'状态");
                }
                if(order.getSubStatus()!=6){
                    throw new BusinessException("不是'预下单'状态没完成");
                }
            }else if(status==2){
                //生产中
                if(!order.getProducer().equals(order.getUser())&&status!=1)
                {
                    throw new BusinessException("不是'预付款'状态，不能设置'生产中'状态");
                }
            }
            order.setStatus(status);
            order.setSubStatus(subStatus);
            order=orderDao.save(order);
            return order;
        }
    }



    @Override
    public Task updateTaskByOrder(Order order) {
        if(order.getProducer()==null)
            return null;


        Task task=taskDao.findByOrderAndUser(order,order.getProducer());

        if(task==null){
            task=new Task();
            task.setOrder(order);
            task.setUser(order.getProducer());
            //管理员
            task.setType(0);

            //0:待接受 1:已拒绝 2:生产中 3:待结算 4:已完成 5:已关闭
            if(order.getStatus()==0){
                if(order.getSubStatus()==0){
                    //
                    task.setStatus(0);
                }else  if(order.getSubStatus()==1){
                    //
                    task.setStatus(1);
                }
            }else if(order.getStatus()==2){
                task.setStatus(2);
            }

            task.setCreateTime(new Date());
            task.setUpdateTime(new Date());
            task=taskDao.save(task);
        }

        return task;


    }

    @Override
    public List<OrderVO> getOrderVOListByIdIn(Collection<Long> ids) {
        QOrder e=QOrder.order;
        JPAQuery<Tuple> query=QF.select(OrderVO.select(e)).from(e)
                .where(e.id.in(ids));
        return toList(query, OrderVO.class,e);
    }

    @Override
    public Page<TaskVO> getTaskVOPage(Integer type, Long userId, Pageable pageable) {
        QTask e=QTask.task;
        Predicate p=null;
        if(type!=null){
            //0:待接受 1:已拒绝 2:生产中 3:待结算 4:已完成 5:已关闭
            if(type==0){
              p=e.status.in(0,2,3);
            }else{
                p=e.status.in(1,4,5);
            }
        }
        p=e.user.id.eq(userId).and(p);

        JPAQuery<Tuple> query=QF.select(TaskVO.select(e)).from(e)
                .where(p);
        Page<TaskVO> page=toPage(query,pageable, TaskVO.class,e);

        if(page.getContent().size()>0){
          List<Long> ids= page.getContent().stream().map(new Function<TaskVO, Long>() {
              @Override
              public Long apply(TaskVO taskVO) {
                  return taskVO.getOrderId();
              }
          }).collect(Collectors.toList());
          List<OrderVO> orderVOS=getOrderVOListByIdIn(ids);
          Map<Long, OrderVO> map=orderVOS.stream().collect(Collectors.toMap(OrderVO::getId, a -> a,(k1, k2)->k1));
          for (TaskVO t : page.getContent()){
              t.setOrder(map.get(t.getOrderId()));
          }
        }

        return page;
    }



}
