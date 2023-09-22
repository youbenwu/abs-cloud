package com.outmao.ebs.security.access;


import com.outmao.ebs.common.configuration.security.SecurityPermissionEvaluator;
import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.io.Serializable;

@Component
public class SecurityPermissionEvaluatorImpl implements SecurityPermissionEvaluator {


	@Autowired
	private SecurityService securityUserService;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(requestAttributes!=null){
			requestAttributes.setAttribute("targetDomainObject",targetDomainObject,0);
			requestAttributes.setAttribute("permission",permission,0);
			return true;
		}
		return securityUserService.hasPermission(SecurityUtil.currentOrgId(),(String)targetDomainObject,(String)permission);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
		return securityUserService.hasPermission((Long)targetId,targetType,(String)permission);
	}


	@Override
	public void hasPermission(Long orgId, Long userId) {
		if(!SecurityUtil.isAuthenticated())
			return;
		if(SecurityUtil.isAdminApi()){
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			if(requestAttributes!=null){
				Object targetDomainObject=requestAttributes.getAttribute("targetDomainObject",0);
				Object permission=requestAttributes.getAttribute("permission",0);
				if(targetDomainObject!=null&&permission!=null){
					if(!securityUserService.hasPermission(orgId,(String)targetDomainObject,(String)permission)){
						throw new AccessDeniedException("权限不足");
					}
				}
			}
		}else{
			if(userId!=null&&!SecurityUtil.currentUserId().equals(userId)){
				throw new AccessDeniedException("权限不足");
			}
		}
	}


}
