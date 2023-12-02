package com.outmao.ebs.jnet.vo.storage;

import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.storage.QStorageStyleSpec;
import com.outmao.ebs.jnet.entity.storage.StorageBase;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class StorageStyleSpecVO extends StorageBase implements DslVO<QStorageStyleSpec> {

    private Long id;

    /*
     * 主仓
     * */

    private Long storageId;

    /*
     * 主仓款型
     * */

    private Long styleId;

    /*
     * 规格名称
     * */
    private String name;


    public static Expression<?>[] select(QStorageStyleSpec e){
        return new Expression<?>[]{
                e.id,e.storage.id,e.style.id,e.name,
                e.addNum,e.num,e.progress,e.sendNum,e.stockedNum,e.stockNum,e.unSendNum,
                e.createTime,e.updateTime
        };

    }



    @Override
    public StorageStyleSpecVO fromTuple(Tuple t, QStorageStyleSpec e) {
        id=t.get(e.id);
        storageId=t.get(e.storage.id);
        styleId=t.get(e.style.id);
        name=t.get(e.name);
        setAddNum(t.get(e.addNum));
        setProgress(t.get(e.progress));
        setSendNum(t.get(e.sendNum));
        setStockedNum(t.get(e.stockedNum));
        setStockNum(t.get(e.stockNum));
        setUnSendNum(t.get(e.unSendNum));
        setCreateTime(t.get(e.createTime));
        setUpdateTime(t.get(e.updateTime));

        return this;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
