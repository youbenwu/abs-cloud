package com.outmao.ebs.jnet.vo.index;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.springframework.data.domain.Page;

/**
 * 首页
 * @author yeyi
 * @date 2019/9/23 14:49
 **/
@ApiModel(value = "IndexVO", description = "首页")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class IndexVO {
	private Page<FunctionVO> functions;
	private Page<BannerVO> banners;
	public Page<FunctionVO> getFunctions() {
		return functions;
	}
	public void setFunctions(Page<FunctionVO> functions) {
		this.functions = functions;
	}
	public Page<BannerVO> getBanners() {
		return banners;
	}
	public void setBanners(Page<BannerVO> banners) {
		this.banners = banners;
	}
}
