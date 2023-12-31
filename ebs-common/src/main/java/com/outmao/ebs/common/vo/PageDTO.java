package com.outmao.ebs.common.vo;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Data
public class PageDTO extends BaseDTO{

    private Integer page;

    private Integer size;

    private String sort;

    public Pageable getPageable(Pageable pageable){
        if(page!=null){
            if(sort!=null&&sort.length()>0){
                String[] strs=sort.split(",");
                Sort s=Sort.by(Sort.Direction.valueOf(strs[1].toUpperCase()),strs[0]);
                pageable=PageRequest.of(page,size==null?10:size,s);
            }else{
                pageable= PageRequest.of(page,size==null?10:size);
            }
        }
        return pageable;
    }

}