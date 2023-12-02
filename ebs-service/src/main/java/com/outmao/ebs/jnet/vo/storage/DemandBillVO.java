package com.outmao.ebs.jnet.vo.storage;


import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.entity.storage.QDemandBill;
import com.outmao.ebs.user.domain.conver.ContactUserVOConver;
import com.outmao.ebs.user.vo.ContactUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

import java.util.Date;

public class DemandBillVO implements DslVO<QDemandBill> {

    private Long id;

    private ContactUserVO user;

    private Long storageId;

    //0--未确认 1--已确认
    private int status;

    private String styles;

    private Date createTime;

    public static Expression<?>[] select(QDemandBill e){
        return ArrayUtil.merge(
                new ContactUserVOConver().select(e.user),
                new Expression<?>[]{
                        e.id,e.storage.id,e.status,e.styles,e.createTime
                }
        );

    }

    @Override
    public DemandBillVO fromTuple(Tuple t, QDemandBill e) {
        user=new ContactUserVOConver().fromTuple(t,e.user);
        id=t.get(e.id);
        storageId=t.get(e.storage.id);
        status=t.get(e.status);
        styles=t.get(e.styles);
        createTime=t.get(e.createTime);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactUserVO getUser() {
        return user;
    }

    public void setUser(ContactUserVO user) {
        this.user = user;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
