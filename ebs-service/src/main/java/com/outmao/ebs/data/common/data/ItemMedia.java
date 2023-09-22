package com.outmao.ebs.data.common.data;



import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class ItemMedia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 文件ID
     */
    private Long mediaId;

    /**
     * 文件URL
     */
    @Column(nullable = false)
    private String url;

    /**
     * 名称
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 0图片，1视频
     */
    private int type;

    /**
     * 状态 0显示，1隐藏
     */
    private int status;

    /**
     * 排序
     */
    private int sort;

    /**
     * 图片创建时间
     */
    private Date createTime;


}
