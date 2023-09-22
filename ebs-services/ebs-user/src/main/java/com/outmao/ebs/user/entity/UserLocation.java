package com.outmao.ebs.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.Address;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//记录用户位置变化
@Data
@Entity
@Table(name = "ebs_UserLocation")
public class UserLocation extends Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	// 创建时间
	private Date createTime;

}
