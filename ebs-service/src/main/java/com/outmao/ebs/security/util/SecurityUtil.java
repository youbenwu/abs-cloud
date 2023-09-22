package com.outmao.ebs.security.util;


import com.outmao.ebs.common.util.RequestUtil;
import com.outmao.ebs.security.vo.SecurityMember;
import com.outmao.ebs.security.vo.SecurityRole;
import com.outmao.ebs.security.vo.SecurityRolePermission;
import com.outmao.ebs.security.vo.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class SecurityUtil {


	public static boolean isAdminApi(){
		HttpServletRequest request= RequestUtil.request();
		if(request!=null)
			return request.getRequestURI().startsWith("/api/admin/");
		return false;
	}

	public static Long currentOrgId(){
		Long orgId=RequestUtil.getHeaderLong("orgId");
		if(orgId==null){
			SecurityUser user=currentUser();
			if(user.getOrgs()!=null&&!user.getOrgs().isEmpty()){
				orgId=user.getOrgs().get(0).getOrgId();
			}
		}
		return orgId;
	}


	public static SecurityUser currentUser() {
		SecurityUser principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal;
	}


	public static Long currentUserId() {
		return SecurityUtil.currentUser().getId();
	}


	public static void logout() {
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	}


	public static boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication()!=null
				&&SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&&(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SecurityUser);
	}

	public static boolean hasPermission(Authentication authentication,Long orgId, String url, String permission){


		SecurityUser user = (SecurityUser) authentication.getPrincipal();

		// 无论如何超级管理员都过
		if (user.getUsername().equals("admin"))
			return true;

		if(orgId==null)
			return false;

		if(user.getMembers()==null||user.getMembers().isEmpty())
			return false;

		for (SecurityMember member:user.getMembers()) {
			if (member.getOrgId().equals(orgId)&&hasPermission(member.getRoles(), url, permission)) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasPermission(Long orgId, String url, String permission) {
		return hasPermission(SecurityContextHolder.getContext().getAuthentication(),orgId,url,permission);
	}


	private static boolean hasPermission(Collection<SecurityRole> authorities, String url, String permission){
		if(authorities==null||authorities.isEmpty())
			return false;

		// 遍历用户所有角色
		for (SecurityRole authority : authorities) {

			// 无论如何超级管理员都过
			if("admin".equals(authority.getAuthority()))
				return true;

			// 得到角色所有的权限
			List<SecurityRolePermission> permissions = authority.getPermissions();
			// 遍历permissions
			for (SecurityRolePermission rp : permissions) {
				if(rp.getUrl().equals(url)&&rp.getName().equals(permission)){
					return true;
				}
			}
		}

		return false;
	}




}
