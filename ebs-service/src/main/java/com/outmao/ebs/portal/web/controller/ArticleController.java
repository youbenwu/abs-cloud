package com.outmao.ebs.portal.web.controller;



import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.portal.service.ArticleService;
import com.outmao.ebs.portal.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public String article(Long id,String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(id==null&& StringUtils.isEmpty(code)){
			throw new BusinessException("参数错误");
		}
		ArticleVO article=id!=null?articleService.getArticleVOById(id):articleService.getArticleVOByCode(code);
		if(article==null){
			throw new BusinessException("内容不存在");
		}
		request.setAttribute("data",article);
		return "article2";
	}



}
