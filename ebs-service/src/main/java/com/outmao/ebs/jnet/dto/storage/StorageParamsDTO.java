package com.outmao.ebs.jnet.dto.storage;

public class StorageParamsDTO {

    private Long id;

    private Long orderId;

    private Long userId;

    private Long managerId;

    private String name;


    public StorageParamsDTO(){

    }

    public StorageParamsDTO(Long id,Long orderId,Long userId,Long managerId,String name){
       this.id=id;
       this.orderId=orderId;
       this.userId=userId;
       this.managerId=managerId;
       this.name=name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
