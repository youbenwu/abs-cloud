package com.outmao.ebs.jnet.action.client;

import com.outmao.ebs.jnet.domain.index.IndexDomain;
import com.outmao.ebs.jnet.vo.index.IndexVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yeyi
 * @date 2019年9月23日
 */
@Api(value = "/index", tags = "首页接口")
@RequestMapping(value = "/api/index")
@RestController
public class IndexAction {
	
    @Autowired
	private IndexDomain indexDomain;

	@PreAuthorize("permitAll")
	@ApiOperation(value = "首页功能模块及banner", notes = "首页功能模块及banner")
	@PostMapping(value = "/functionList")
	public IndexVO functionList(){
		return indexDomain.getIndex();
	}

}
