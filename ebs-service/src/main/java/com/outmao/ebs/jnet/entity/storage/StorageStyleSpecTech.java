package com.outmao.ebs.jnet.entity.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//工艺
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity(name = "ZStorageStyleSpecTech")
@Table(name = "z_StorageStyleSpecTech",indexes= {@Index(columnList = "specId,name",unique=true)})
public class StorageStyleSpecTech   implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     *
     * ID
     *
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "storageId")
    private Storage storage;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "styleId")
    private StorageStyle style;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "specId")
    private StorageStyleSpec spec;

    //排序
    private int sort;

    //工艺名称
    private String name;

    //当前数量
    private int num;

    //总数量
    private int total;

    //返工数量
    private int rework;

    /*
     *
     * 时间
     *
     * */
    private Date createTime;

    /*
     *
     * 时间
     *
     * */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public StorageStyle getStyle() {
        return style;
    }

    public void setStyle(StorageStyle style) {
        this.style = style;
    }

    public StorageStyleSpec getSpec() {
        return spec;
    }

    public void setSpec(StorageStyleSpec spec) {
        this.spec = spec;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRework() {
        return rework;
    }

    public void setRework(int rework) {
        this.rework = rework;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
