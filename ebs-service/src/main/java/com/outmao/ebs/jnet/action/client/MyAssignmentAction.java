package com.outmao.ebs.jnet.action.client;

import com.alibaba.fastjson.JSON;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.jnet.domain.assignment.AssignmentDomain;
import com.outmao.ebs.jnet.dto.assignment.*;
import com.outmao.ebs.jnet.dto.factory.ProductionCategoryParamsDTO;
import com.outmao.ebs.jnet.entity.asssignment.ZMyAssignment;
import com.outmao.ebs.jnet.entity.asssignment.ZMyAssignmentApply;
import com.outmao.ebs.jnet.enums.assignment.AssignmentUserTypeEnum;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentDetailVO;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentVO;
import com.outmao.ebs.security.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author yeyi
 * @date 2019年8月23日
 */
@Slf4j
@Api(value = "/assignment", tags = "我的外发模块接口")
@RequestMapping(value = "/api/assignment")
@RestController
public class MyAssignmentAction {
	
	final static private Logger logger = LoggerFactory.getLogger(MyAssignmentAction.class);
	
    @Autowired
    AssignmentDomain assignmentDomain;

	@PreAuthorize("permitAll")
	@ApiOperation(value = "我的外发列表-我发布的", notes = "我的外发列表-我发布的")
	@PostMapping(value = "/myPage")
	public Page<ZMyAssignmentVO> myPage(AssignmentPageSearchDTO para, Pageable pageable){
	    if(SecurityUtil.isAuthenticated()){
	    	if(null==para.getUserId()) {
	    		para.setUserId(SecurityUtil.currentUserId());
	    	}
        } else {
        	if(null==para.getUserId()) {
    			throw new BusinessException("未登录请传 userId");
        	}
        }

		return assignmentDomain.myPage(para, pageable);
	}

	@PreAuthorize("permitAll")
	@ApiOperation(value = "我的外发列表-我参与的", notes = "我的外发列表-我参与的")
	@PostMapping(value = "/joinPage")
	public Page<ZMyAssignmentVO> joinPage(Pageable pageable){
		return assignmentDomain.joinPage(SecurityUtil.currentUserId(), pageable);
	}

	@ApiOperation(value = "编辑/发布外发", notes = "编辑/发布外发")
	@PostMapping(value = "/save")
	public ZMyAssignment save(@Valid AssignmentSaveParamsDTO dto) throws Exception{
		List<ProductionCategoryParamsDTO> techonlogies = null;
		try {
			techonlogies = JSON.parseArray(dto.getTechnologyJson(), ProductionCategoryParamsDTO.class);
		} catch (Exception e) {
			log.error("technologyJson format err: ", e);
			throw new BusinessException("technologyJson format err: "+e.getMessage());
		}
		
		if(CollectionUtils.isEmpty(techonlogies)) {
			throw new BusinessException("technologyJson empty!");
		}
		
		if(null==dto.getUserType()) {
			dto.setUserType(AssignmentUserTypeEnum.NORMAL.getValue());
		}
		return assignmentDomain.saveAssignment(dto, SecurityUtil.currentUserId());
	}
	
	@ApiOperation(value = "申请外发", notes = "申请外发")
	@PostMapping(value = "/apply")
	public ZMyAssignmentApply apply(@Valid AssignmentApplyParamsDTO dto){
	    // 校验金额
        try{
            new BigDecimal(dto.getPrice());
        }catch (Exception e){
            log.error("appl price format err: ", e);
            throw new BusinessException("appl price format err: "+e.getMessage());
        }
		return assignmentDomain.saveAssignmentApply(dto, SecurityUtil.currentUser());
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "外发详情", notes = "外发详情")
	@PostMapping(value = "/detail")
	public ZMyAssignmentDetailVO detail(Long assignmentId){
		return assignmentDomain.getAssignmentDetailById(assignmentId, SecurityUtil.isAuthenticated()?SecurityUtil.currentUserId():null);
	}
	
	@ApiOperation(value = "接受外发申请", notes = "接受外发申请")
	@PostMapping(value = "/accept")
	public Boolean accept(@Valid AssignmentAcceptParamsDTO dto){
		if(!CollectionUtils.isEmpty(dto.getApplyIds())) { // 取消申请
			if(dto.getApplyIds().size()>6) {
				log.error("accept must less than 6");
	            throw new BusinessException("最多只能选择六个");
			}
		}
		
		assignmentDomain.acceptAssigmentApply(dto, SecurityUtil.currentUserId());
		return true;
	}
	
	@ApiOperation(value = "取消外发申请", notes = "取消外发申请")
	@PostMapping(value = "/cancel")
	public Boolean cancel(Long assignmentId){
		assignmentDomain.cancelAssignment(assignmentId, SecurityUtil.currentUserId());
		return true;
	}

	@PreAuthorize("permitAll")
	@ApiOperation(value = "推荐外发 推荐距离最近的前五十个有效外发中随机 resultLength 个", notes = "推荐外发 推荐距离最近的前五十个有效外发中随机 resultLength 个")
	@PostMapping(value = "/recommend")
	public List<ZMyAssignmentVO> recommend(@Valid AssignmentRecommendParamsDTO para){
		if(SecurityUtil.isAuthenticated()){
			para.setUserId(SecurityUtil.currentUserId());
		}else{
			Double latitude = this.string2DoubleSafe(para.getLatitude());
			if(null==latitude){
                para.setLatitude("1");
			}
			Double longitude = this.string2DoubleSafe(para.getLongitude());
			if(null==longitude){
				para.setLongitude("1");
			}
			// 表示没有登录，为了后边从数据库中查找不存在时正好用上
			para.setUserId(-1L);
		}
		return assignmentDomain.recommend(para);
	}

	/**
	 * 字符串转 double ，失败返回 null
	 * @author yeyi
	 * @date 2019/10/8 11:49
	 **/
	private Double string2DoubleSafe(String str){
		try{
			if(StringUtils.isBlank(str)){
				log.info("string2DoubleSaf empty");
				return null;
			}

			return Double.parseDouble(str);
		}catch (Exception e){
			log.error("string2DoubleSaf err: ", e);
		}

		return null;
	}
}
