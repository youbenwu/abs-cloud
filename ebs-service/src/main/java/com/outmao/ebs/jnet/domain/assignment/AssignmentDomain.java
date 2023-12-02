package com.outmao.ebs.jnet.domain.assignment;

import com.outmao.ebs.security.vo.SecurityUser;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.dto.assignment.*;
import com.outmao.ebs.jnet.entity.asssignment.MyAssignmentRobot;
import com.outmao.ebs.jnet.entity.asssignment.ZMyAssignment;
import com.outmao.ebs.jnet.entity.asssignment.ZMyAssignmentApply;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentApplyVO;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentDetailVO;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 我的外发
 * 
 * @author yeyi
 * @date 2019年8月27日
 */
public interface AssignmentDomain {

    ZMyAssignment getAssignmentById(Long id);
    ZMyAssignmentDetailVO getAssignmentDetailById(Long id, Long userId);
    List<ZMyAssignment> getAssignmentByIds(List<Long> ids);
    void deleteAssignmentById(Long id);
    Page<ZMyAssignmentVO> myPage(AssignmentPageSearchDTO para, Pageable pageable);
    Page<ZMyAssignmentVO> joinPage(long userId, Pageable pageable);

    /**
     * @return 错误返回 null
     */
    ZMyAssignment saveAssignment(AssignmentSaveParamsDTO dto, long userId);
    
    /**
     * @return 错误返回 null
     */
    ZMyAssignmentApply saveAssignmentApply(AssignmentApplyParamsDTO dto, SecurityUser user);
    
    ZMyAssignmentApply getAssignmentApplyById(Long id);
    void deleteAssignmentApplyById(Long id);
    List<ZMyAssignmentApplyVO> findZMyAssignmentApplyVOByAssignmentId(Long assignmentId);
    List<ZMyAssignmentApply> findZMyAssignmentApplyByAssignmentId(Long assignmentId);
    
    /**
     * 接受外发申请
     * @param applyIds
     * @param userId
     * @author yeyi
     * @date 2019年9月11日
     */
    void acceptAssigmentApply(AssignmentAcceptParamsDTO dto, long userId);
    
    void cancelAssignment(long assignmentId, long userId);

    /**
     * 扫描过期外发，并修改状态为已过期
     * @author yeyi
     * @date 2019/9/24 14:42
     **/
    void checkAssignmentExpired();
    
	/**
	 * 推荐距离最近的前五十个有效外发中随机 num 个
	 * @author yeyi
	 * @date 2019年9月28日
	 */
    List<ZMyAssignmentVO> recommend(AssignmentRecommendParamsDTO para);
    
    /**
     * 取所有机器人要外发的源数据
     * @return
     * @author yeyi
     * @date 2019年11月3日
     */
    List<MyAssignmentRobot> getAllAssignmentRobot();
    
    /**
     * 设置外发机器人的删除属性
     * @return 影响数据库条数
     * @author yeyi
     * @date 2019年11月10日
     */
    int updateRobotDeleted(boolean isDeleted, List<Long> ids);
    
    /**
     * 随机增加访问次数
     * 
     * @author yeyi
     * @date 2019年11月5日
     */
    void randomAddVisitNum();
}
