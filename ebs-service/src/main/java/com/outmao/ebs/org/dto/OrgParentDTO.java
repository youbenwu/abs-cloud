package com.outmao.ebs.org.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * 组织机构多个父级
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrgParentDTO{

    private Long orgId;

    private Long parentId;


}
