package com.outmao.ebs.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 *
 * 用户授权
 *
 */
@Data
@Entity
@Table(name = "ebs_UserOauthSession", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "userId", "clientType" }) })
public class UserOauthSession implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 自动ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 授权类型
     *
     */
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "oauthId")
    private UserOauth oauth;

    /**
     *
     * 授权用户
     *
     */
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 前端类型
     *
     */
    private String clientType;

    /**
     *
     * 前端标识
     *
     */
    private String client;

    /**
     *
     * token
     *
     */
    @Column(unique = true)
    private String token;

    /**
     *
     * 第三方会话KEY
     *
     */
    private String sessionKey;

    /**
     *
     * 过期时间
     *
     */
    private LocalDateTime expireTime;

    /**
     *
     * 授权时间
     *
     */
    private LocalDateTime oauthTime;


    public int hashCode() {
        return (this.id == null) ? 0 : this.id.hashCode();
    }

    public boolean equals(Object object) {
        if (object instanceof UserOauthSession) {
            final UserOauthSession obj = (UserOauthSession) object;
            return Objects.equals(this.id,obj.id);
        }
        return false;
    }


}
