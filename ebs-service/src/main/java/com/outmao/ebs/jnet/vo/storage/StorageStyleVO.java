package com.outmao.ebs.jnet.vo.storage;

import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.material.ProductionTemplate;
import com.outmao.ebs.jnet.entity.storage.QStorageStyle;
import com.outmao.ebs.jnet.entity.storage.StorageBase;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModelProperty;

public class StorageStyleVO extends StorageBase implements DslVO<QStorageStyle> {


    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /*
     *
     * 主仓
     *
     * */
    @ApiModelProperty(name = "storageId", value = "主仓")
    private Long storageId;

    /*
     *
     * 生产样板
     *
     * */
    @ApiModelProperty(name = "templateId", value = "生产样板")
    private Long templateId;

    /*
     *
     * 款型名称
     *
     * */
    @ApiModelProperty(name = "name", value = "款型名称")
    private String name;

    @ApiModelProperty(name = "template", value = "样板")
    private ProductionTemplate template;

    public static Expression<?>[] select(QStorageStyle e){
        return new Expression<?>[]{
                e.id,e.storage.id,e.template,e.name,
                e.addNum,e.num,e.progress,e.sendNum,e.stockedNum,e.stockNum,e.unSendNum,
                e.createTime,e.updateTime
        };

    }



    @Override
    public StorageStyleVO fromTuple(Tuple t, QStorageStyle e) {
        id=t.get(e.id);
        storageId=t.get(e.storage.id);
        template=t.get(e.template);
        templateId=template.getId();
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

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductionTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ProductionTemplate template) {
        this.template = template;
    }
}
