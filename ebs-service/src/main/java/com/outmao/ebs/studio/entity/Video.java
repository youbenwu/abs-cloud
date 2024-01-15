package com.outmao.ebs.studio.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ebs_MovieEpisode")
public class Video  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 所属组织ID
     *
     */
    @Column(updatable = false,nullable = false)
    private Long orgId;

    @Column(updatable = false,nullable = false)
    private Long movieId;

    @Column(updatable = false,nullable = false)
    private Long episodeId;

    private String name;

    @Column(nullable = false)
    private String url;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


}
