package com.outmao.ebs.security.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityRole implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 成员ID
	 */
	private Long memberId;

	/**
	 * 角色ID
	 */
	private Long id;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色权限列表
	 */
	private List<SecurityRolePermission> permissions;


	@Override
	public String getAuthority() {
		return name;
	}


}
