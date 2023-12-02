package com.outmao.ebs.jnet.entity.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.jnet.entity.material.ProductionTemplate;

import javax.persistence.*;
import java.io.Serializable;


/*
 * 主仓款型
 * */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity(name = "ZStorageStyle")
@Table(name = "z_StorageStyle",indexes= {@Index(columnList = "storageId,templateId,name",unique=true)})
public class StorageStyle extends StorageBase implements Serializable {

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

    /*
     *
     * 主仓
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "storageId")
    private Storage storage;

    /*
     *
     * 生产样板
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "templateId")
    private ProductionTemplate template;

    /*
     *
     * 款型名称
     *
     * */
    private String name;


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

    public ProductionTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ProductionTemplate template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
