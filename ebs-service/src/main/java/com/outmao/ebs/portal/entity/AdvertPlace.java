package com.outmao.ebs.portal.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * 广告投放场所
 *
 */

@Data
@Entity
@Table(name = "portal_AdvertPlace", uniqueConstraints = { @UniqueConstraint(columnNames = { "advertId", "placeId" }) })
public class AdvertPlace implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     * ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long advertId;

    private Long placeId;


}
