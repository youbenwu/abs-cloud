package com.outmao.ebs.jnet.dao.order;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.order.Order;
import com.outmao.ebs.jnet.entity.order.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("ZTaskDao")
public interface TaskDao extends JpaRepository<Task,Long> {


    public Task findByOrderAndUser(Order order, User user);

}
