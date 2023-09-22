package com.outmao.ebs.message.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "m_MessageTemplateTag", uniqueConstraints = { @UniqueConstraint(columnNames = { "typeId", "value" }) })
public class MessageTemplateTag implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "typeId")
    private MessageType type;
    private String name;
    private String value;

}
