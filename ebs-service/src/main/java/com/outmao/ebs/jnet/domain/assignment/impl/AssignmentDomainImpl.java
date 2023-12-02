package com.outmao.ebs.jnet.domain.assignment.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.base.BaseDomain;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.exception.BusinessException;

import com.outmao.ebs.security.vo.SecurityUser;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.constant.AssignmentConstant;
import com.outmao.ebs.jnet.dao.assignmant.*;
import com.outmao.ebs.jnet.dao.factory.ProductionCategoryDao;
import com.outmao.ebs.jnet.dao.factory.ProductionTechnologyDao;
import com.outmao.ebs.jnet.domain.assignment.AssignmentDomain;
import com.outmao.ebs.jnet.dto.assignment.*;
import com.outmao.ebs.jnet.entity.asssignment.*;
import com.outmao.ebs.jnet.entity.factory.Factory;
import com.outmao.ebs.jnet.entity.factory.FactoryProductionTechnology;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.entity.factory.ProductionTechnology;
import com.outmao.ebs.jnet.enums.assignment.AssignmentStatusEnum;
import com.outmao.ebs.jnet.enums.assignment.AssignmentUserTypeEnum;
import com.outmao.ebs.jnet.service.config.ConfigAssignmentService;
import com.outmao.ebs.jnet.service.factory.FactoryService;
import com.outmao.ebs.jnet.util.BigDecimalUtil;
import com.outmao.ebs.jnet.util.DateUtil;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentApplyTechnonogyVO;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentApplyVO;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentDetailVO;
import com.outmao.ebs.jnet.vo.assignment.ZMyAssignmentVO;
import com.outmao.ebs.jnet.vo.factory.FactoryVO;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.user.vo.ContactUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 我的外发
 * 
 * @author yeyi
 * @date 2019年8月27日
 */
@Transactional
@Component
public class AssignmentDomainImpl extends BaseDomain implements AssignmentDomain {

	private static final Logger log = LoggerFactory.getLogger(AssignmentDomainImpl.class);
	
	@Autowired
    private ZMyAssignmentDao zmyassignmentDao;
	
    @Autowired
    private ZMyAssignmentApplyDao zmyassignmentapplyDao;
    
    @Autowired
    private MyAssignmentApplyTechnologyDao myAssignmentApplyTechnologyDao;

	@Autowired
	private MyAssignmentTechnologyDao myAssignmentTechnologyDao;
    
    @Autowired
    private ProductionTechnologyDao productionTechnologyDao;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private FactoryService factoryService;

	@Autowired
	private ProductionCategoryDao productionCategoryDao;
	
	@Autowired
    ZMyAssignmentRobotDao zMyAssignmentRobotDao;
	
	
	@Autowired
	ConfigAssignmentService configAssignmentService;
    
	@Override
	public ZMyAssignment getAssignmentById(Long id) {
		ZMyAssignment result = zmyassignmentDao.find1ByIdAndDeleted(id, false);
		if(null==result) {
			return null;
		}

		this.addAssignmentVisitsNum(Arrays.asList(id));
		return result;
	}
	
	@Override
	public List<ZMyAssignment> getAssignmentByIds(List<Long> ids) {
		List<ZMyAssignment> result = zmyassignmentDao.findAllById(ids);
		if(CollectionUtils.isEmpty(result)) {
			return result;
		}

		this.addAssignmentVisitsNum(ids);
		return result;
	}
	
	/**
	 * 更新访问次数
	 * @param assignmentIds 要增的ID列表
	 * @author yeyi
	 * @return 更新条数
	 * @date 2019年9月10日
	 */
	private int addAssignmentVisitsNum(List<Long> assignmentIds) {
		if(CollectionUtils.isEmpty(assignmentIds)) {
			return 0;
		}
		
		Random r = new Random(System.currentTimeMillis());
		String maxVisitNum = configAssignmentService.getAssignmentVisitNumAddMax();
		Long visitNum = (long)r.nextInt(Integer.parseInt(maxVisitNum))+1;
		return zmyassignmentDao.updateVisitsNumInIds(assignmentIds, visitNum);
	}
	
	@Override
	@Transactional
	public void deleteAssignmentById(Long id) {
		myAssignmentTechnologyDao.deleteByAssignmentId(id);
		zmyassignmentDao.deleteById(id);
	}

	@Override
	public ZMyAssignmentApply getAssignmentApplyById(Long id) {
		return zmyassignmentapplyDao.findById(id).orElse(null);
	}
	
	/**
	 * 保存外发申请与工艺的关系
	 * 先删再增加
	 * @author yeyi
	 * @date 2019年9月4日
	 */
	public void saveApplyTechnology(long applyId, List<Long> technologyIds) {
		// 删除旧的
		try {
			myAssignmentApplyTechnologyDao.deleteByAssignmentId(applyId);
		} catch (Exception e) {
			log.error("deleteByAssignmentI err: ", e);
		}
		
		// 找工艺名
		List<ProductionTechnology> techs = productionTechnologyDao.findAllById(technologyIds);
		if(CollectionUtils.isEmpty(techs) || techs.size()!=technologyIds.size()) {
			log.error("technologyId err: {},{}", JSON.toJSONString(technologyIds, true), JSON.toJSONString(techs, true));
			throw new RuntimeException("technologyId err"+technologyIds); // 为了回滚
		}
		
		// 转成 id 为 key 的 map
		Map<Long, ProductionTechnology> techMaps = techs.stream().collect(Collectors.toMap(ProductionTechnology::getId, a -> a,(k1,k2)->k1));
		
		// 构造要保存的 list
		final Date curDate = new Date();
		List<MyAssignmentApplyTechnology> applyTechs = new ArrayList<>(technologyIds.size());
		for(Long id: technologyIds) {
			MyAssignmentApplyTechnology applyTech = new MyAssignmentApplyTechnology();
			applyTech.setCreateTime(curDate);
			applyTech.setAssignmentApplyId(applyId);
			applyTech.setTechnologyId(id);
			applyTech.setTechnologyName(techMaps.get(id).getName());
			
			applyTechs.add(applyTech);
		}
		
		myAssignmentApplyTechnologyDao.saveAll(applyTechs);
	}

	@Override
	public ZMyAssignmentApply saveAssignmentApply(AssignmentApplyParamsDTO dto, SecurityUser user) {
		log.info("saveAssignmentAppl userId: {}", user.getId());
		// assignmentId 是否有效
		ZMyAssignment oldAssignment = this.getAssignmentById(dto.getAssignmentId());
		if(null==oldAssignment){
			log.error("saveAssignmentAppl assignmentId not exist: {}", JSON.toJSONString(dto, true));
			throw new BusinessException("assignmentId not exist: "+dto.getAssignmentId());
		}
		// 是否是自己发的
		if(oldAssignment.getUserId().equals(user.getId())){
			log.error("saveAssignmentAppl can't apply self assignment: {}", JSON.toJSONString(dto, true));
			throw new BusinessException("can't apply self assignment: "+dto.getAssignmentId());
		}
		// 校验状态
		if(AssignmentStatusEnum.ACTIVE.getValue()!=oldAssignment.getStatus()) {
			throw new BusinessException("status can't apply: "+dto.getAssignmentId());
		}
		// 校验申请人数
		if(oldAssignment.getApplicantNum()>=ZMyAssignmentVO.MAX_APPLICANT_NUM) {
			// 理论上不应该出现这种情况
			log.error("applicants fulled: {}", dto.getAssignmentId());
			throw new BusinessException("applicants fulled: "+dto.getAssignmentId());
		}
		
		ZMyAssignmentApply assignmentApply = null;
		final Date curDate = new Date();
		// 是否已申请过
		List<ZMyAssignmentApply> applies = zmyassignmentapplyDao.findByUserId(Arrays.asList(user.getId()));
		ZMyAssignmentApply applyOld = applies.stream().filter(e->e.getAssignmentId().equals(dto.getAssignmentId())).findFirst().orElse(null);
		if(null==applyOld) { // 未申请过
			assignmentApply = new ZMyAssignmentApply();
			assignmentApply.setCreateTime(curDate);
			assignmentApply.setAssignmentId(dto.getAssignmentId());
			assignmentApply.setUserId(user.getId());
		}else { 
//			assignmentApply = applyOld;// 已申请过（这种情况变成修改）
			// 改为不让申请
			throw new BusinessException("already applied");
		}
		
		// 修改申请人数量
		oldAssignment.setApplicantNum(oldAssignment.getApplicantNum()+1);
		// 保存申请人头像, 最多20 个
		String avatars = oldAssignment.getLatestApplicantAvatar();
		String[] oldArr = avatars.split(AssignmentConstant.URL_GAP);
		int length = StringUtils.isBlank(avatars) ? 1 : oldArr.length+1;
		if(length>ZMyAssignmentVO.MAX_AVATARS_NUM) {
			length = ZMyAssignmentVO.MAX_AVATARS_NUM;
		}
		String [] avatarArr = new String[length];
		for(int i=0; i<length; i++) {
			if(0==i) {
				User u=userService.getUserById(user.getId());
				avatarArr[i] = StringUtils.isBlank(u.getAvatar()) ? "" : u.getAvatar(); // 放在第一的位置
			}else {
				avatarArr[i] = oldArr[i-1];
			}
		}
		oldAssignment.setLatestApplicantAvatar(String.join(AssignmentConstant.URL_GAP, avatarArr));
		if(oldAssignment.getApplicantNum()>=ZMyAssignmentVO.MAX_APPLICANT_NUM) {
			oldAssignment.setStatus(AssignmentStatusEnum.FULLED.getValue());
		}
		
		// 保存申请
		CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true); // 空值不拷贝
        BeanUtil.copyProperties(dto, assignmentApply, false, copyOptions);
		assignmentApply.setUpdateTime(curDate);
		assignmentApply.setPrice(BigDecimalUtil.rounding4_5(new BigDecimal(dto.getPrice())));

		ZMyAssignmentApply apply = zmyassignmentapplyDao.save(assignmentApply);
		
		this.saveApplyTechnology(apply.getId(), dto.getTechnologyIds());


		//当天可申请外发减一
        factoryService.dayAssignmentApplyNumSub(user.getId(),1);
		
		return apply;
	}

	@Override
	public List<ZMyAssignmentApply> findZMyAssignmentApplyByAssignmentId(Long assignmentId) {
		QZMyAssignmentApply qa=QZMyAssignmentApply.zMyAssignmentApply;
		List<Tuple> tuples = QF.select(this.selectQZMyAssignmentApply(qa)).from(qa)
				.where(qa.assignmentId.eq(assignmentId).and(qa.isDeleted.eq(false))).fetch();
		if(CollectionUtils.isEmpty(tuples)){
			return new ArrayList<>(1);
		}
		
		List<ZMyAssignmentApply> result = new ArrayList<>(tuples.size());
		for(Tuple t: tuples) {
			result.add(new ZMyAssignmentApply().fromTuple(t, qa));
		}
		
		return result;
	}

	@Override
	public List<ZMyAssignmentApplyVO> findZMyAssignmentApplyVOByAssignmentId(Long assignmentId){
		List<ZMyAssignmentApply> applies = this.findZMyAssignmentApplyByAssignmentId(assignmentId);
		if(CollectionUtils.isEmpty(applies)) {
			return new ArrayList<>(1);
		}
		
		List<ZMyAssignmentApplyVO> result = new ArrayList<>(applies.size());
		for(ZMyAssignmentApply t: applies) {
			ZMyAssignmentApplyVO vo = new ZMyAssignmentApplyVO();
			CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true); // 空值不拷贝
			BeanUtil.copyProperties(t, vo, copyOptions);
			vo.setPrice(BigDecimalUtil.rounding4_5Cut0(t.getPrice()));
			result.add(vo);
		}
		
		// 找用户信息
		List<Long> userIds = result.stream().filter(e->e.getUserId()!=null).map(ZMyAssignmentApplyVO::getUserId).distinct().collect(Collectors.toList());
		List<ContactUserVO> users = userService.getContactUserVOListByIdIn(userIds);
		final Map<Long, ContactUserVO> userMap = users.stream().collect(Collectors.toMap(ContactUserVO::getId, a -> a,(k1,k2)->k1));
		// 找工厂信息
		List<Factory> fatories = factoryService.getFactoryListByUserIdIn(userIds);
		final Map<Long, Factory> factoryMap = fatories.stream().collect(Collectors.toMap(
				new Function<Factory, Long>() {
                    @Override
					public Long apply(Factory p) { return p.getUser().getId(); }
                }
				, a -> a,(k1,k2)->k1));
		// 工艺信息
		List<Long> applyIds = result.stream().filter(e->e.getId()!=null).map(ZMyAssignmentApplyVO::getId).distinct().collect(Collectors.toList());
		List<MyAssignmentApplyTechnology> applyTechs = myAssignmentApplyTechnologyDao.findByAssignmentApplyIds(applyIds);
		Map<Long, List<MyAssignmentApplyTechnology>> applyTechMap = applyTechs.stream().collect(Collectors.groupingBy(MyAssignmentApplyTechnology::getAssignmentApplyId));
		result.stream().forEach(e->{
			// 用户名
			ContactUserVO u = userMap.get(e.getUserId());
			if(null==u) {
				log.error("userId not exist: {}", e.getUserId());
			}else{
				if(StringUtils.isNotBlank(u.getRealName())){
					e.setUserName(u.getRealName());
				}else{
					log.error("realname empty: {}", e.getUserId());
					e.setUserName(u.getNickname());
				}
				if(StringUtils.isNotBlank(u.getAvatar())){
					e.setAvatar(u.getAvatar());
				}else{
					log.error("avatar empty: {}", e.getUserId());
				}
			}
			
			// 经纬度
			Factory f = factoryMap.get(e.getUserId());
			if(null==f) {
				log.error("userId not exist Factory: {}", e.getUserId());
			}else{
				try {
					e.setLatitude(f.getContact().getAddress().getLatitude()+"");
					e.setLongitude(f.getContact().getAddress().getLongitude()+"");					
				} catch (Exception e2) {
					log.error("getLatitud err2: {}", JSON.toJSONString(f, true));
					log.error("getLatitud err: ", e2);
				}
			}
			
			// 工艺
			List<MyAssignmentApplyTechnology> teches = applyTechMap.get(e.getId());
			if(CollectionUtils.isEmpty(teches)) {
				log.error("MyAssignmentApplyTechnolog empty: {}", e.getId());
			}else{
				e.setTechnologies(ZMyAssignmentApplyTechnonogyVO.fromMyAssignmentApplyTechnologies(teches));
			}
		});
		
		return result;
	}

	private Expression<?>[] selectQZMyAssignmentApply(QZMyAssignmentApply e){
		return new Expression<?>[]{
				e.id,e.assignmentId, e.createTime, e.isDeleted, e.price, e.selected, e.updateTime, e.userId,
		};
	}

	@Override
	public void deleteAssignmentApplyById(Long id) {
		zmyassignmentapplyDao.deleteById(id);
	}
	
	private Expression<?>[] selectQZMyAssignment(QZMyAssignment e){
        return new Expression<?>[]{
            e.id,e.address, e.applicantNum, e.area, e.categoryId, e.city,
            e.createTime,e.imgUrl,e.isDeleted,e.latestApplicantAvatar, e.latitude,
            e.longitude, e.province, e.releaseTime, e.selectedFactory, e.status, e.technologyJson,
            e.text, e.type, e.updateTime, e.userId, e.validityTime, e.userType,
        };
    }
	
	@Override
	public Page<ZMyAssignmentVO> myPage(AssignmentPageSearchDTO para, Pageable pageable) {
		QZMyAssignment e=QZMyAssignment.zMyAssignment;
		BooleanExpression where = e.isDeleted.eq(false).and(e.userId.eq(para.getUserId()));
		if(null!=para.getStatus()) {
			if(para.getStatus().equals(AssignmentStatusEnum.TO_COMPLETED.getValue())) {
				where = where.and(
						e.status.eq(AssignmentStatusEnum.EXPIRED.getValue())
						.or(e.status.eq(AssignmentStatusEnum.FULLED.getValue()))
						);
			}else {
				where = where.and(e.status.eq(para.getStatus()));
			}
		}
		Page<ZMyAssignment> page = (Page<ZMyAssignment>)toPage(QF.select(this.selectQZMyAssignment(e)).from(e)
				.where(where)
				.orderBy(e.updateTime.desc()).orderBy(e.id.desc())
				,pageable, ZMyAssignment.class, e);
		if(null==page) {
			return null;
		}
		
		Page<ZMyAssignmentVO> result = page.map(ZMyAssignmentVO::fromZMyAssignment);
		if(CollectionUtils.isEmpty(page.getContent())) {
			return result;
		}
		// 不用合并状态，保留原来
//		if(null!=para.getStatus()) {
//			if(para.getStatus().equals(AssignmentStatusEnum.TO_COMPLETED.getValue())) { // 将12状态合并成待完成
//				result.getContent().stream().forEach(ee->{
//					if(ee.getStatus().equals(AssignmentStatusEnum.EXPIRED.getValue())
//							|| ee.getStatus().equals(AssignmentStatusEnum.FULLED.getValue())) {
//						ee.setStatus(AssignmentStatusEnum.TO_COMPLETED.getValue());
//					}
//				});
//			}
//		}
		return result;
	}

	/**
	 * 修改外发与工艺关联关系
	 * @author yeyi
	 * @date 2019/10/25 17:27
	 **/
	private void saveAssignmentTechnology(AssignmentSaveParamsDTO dto, final Date curDate){
		if(null!=dto.getId()){
			// 删除旧的
			int delNum = myAssignmentTechnologyDao.deleteByAssignmentId(dto.getId());
			if(delNum<=0){
				log.error("updateAssignmentTechnolog not technology: {}", dto.getId());
			}
		}

		// 增加新的
		List<ZMyAssignmentApplyTechnonogyVO> vos = JSON.parseArray(dto.getTechnologyJson(), ZMyAssignmentApplyTechnonogyVO.class);
		List<MyAssignmentTechnology> technologies = new ArrayList<>(vos.size());
		for(ZMyAssignmentApplyTechnonogyVO vo: vos){
			MyAssignmentTechnology technology = new MyAssignmentTechnology();
			technology.setAssignmentId(dto.getId());
			technology.setCreateTime(curDate);
			technology.setTechnologyId(vo.getId());
			technology.setTechnologyName(vo.getName());
			technologies.add(technology);
		}
		List<MyAssignmentTechnology> results = myAssignmentTechnologyDao.saveAll(technologies);
		if(results.size()!=technologies.size()){
			log.error("updateAssignmentTechnolo save err: {}", dto.getTechnologyJson());
		}
	}

	@Override
	@Transactional
	public ZMyAssignment saveAssignment(AssignmentSaveParamsDTO dto, long userId) {
		log.info("saveAssignmen userId: {}", userId);
		if(null==dto){
			log.error("saveAssignment dto null");
			return null;
		}

		ZMyAssignment assignment = null;
		final Date curDate = new Date();
		if(null==dto.getId()){ // 保存
			assignment = new ZMyAssignment();
			assignment.setCreateTime(curDate);
			assignment.setReleaseTime(curDate);
		}else{ // 更新
			assignment = zmyassignmentDao.findById(dto.getId()).orElse(null);
			if(null==assignment){
				log.error("saveAssignmen error: {}", JSON.toJSONString(dto, true));
				throw new BusinessException("id not exist: "+dto.getId());
			}
			if(userId!=assignment.getUserId()) {
				log.error("saveAssignmen userId mismatch: {},{}", userId, assignment.getUserId());
				return null;
			}
		}

		// 是否机器人
		User user = userService.getUserById(userId);
		CopyOptions copyOptions = CopyOptions.create();
		if(AssignmentUserTypeEnum.ROBOT.getValue()!=user.getType()){
			copyOptions = copyOptions.setIgnoreNullValue(true);// 空值不拷贝
			// 非机器人不使用此项，防止别人从外部传入影响
			dto.setLatestApplicantAvatar(null);
			dto.setVisitsNum(null);
		}else {
			if(StringUtils.isNotBlank(dto.getLatestApplicantAvatar())) {
				assignment.setApplicantNum((long)dto.getLatestApplicantAvatar().split(",").length);	
			}
		}
		BeanUtil.copyProperties(dto, assignment, false, copyOptions);
		assignment.setUpdateTime(curDate);
		assignment.setUserId(userId);

		ZMyAssignment re = zmyassignmentDao.save(assignment);
		dto.setId(re.getId());
		this.saveAssignmentTechnology(dto, curDate);
		return re;
	}

	@Override
	public Page<ZMyAssignmentVO> joinPage(long userId, Pageable pageable) {

		QZMyAssignmentApply e=QZMyAssignmentApply.zMyAssignmentApply;
		BooleanExpression where = e.isDeleted.eq(false).and(e.userId.eq(userId));
		Page<ZMyAssignmentApply> page = (Page<ZMyAssignmentApply>)toPage(QF.select(this.selectQZMyAssignmentApply(e)).from(e)
				.where(where)
				.orderBy(e.updateTime.desc()).orderBy(e.id.desc())
				,pageable, ZMyAssignmentApply.class, e);
		if(null==page) {
			return null;
		}
		
		// 此处为外发申请列表以 assignmentApplyId 为主(唯一)
		Page<ZMyAssignmentVO> result = page.map(AssignmentDomainImpl::fromZMyAssignmentApply);
		// 补上外发数据
		this.addAssignment(result.getContent());
		return result;
	}
	
	private List<ZMyAssignmentVO> addAssignment(List<ZMyAssignmentVO> sourcePage){
		if(CollectionUtils.isEmpty(sourcePage)) {
			return sourcePage;
		}

		List<Long> applyIds = sourcePage.stream().filter(e->e.getAssignmentApplyId()!=null).map(ZMyAssignmentVO::getAssignmentApplyId).distinct().collect(Collectors.toList());
		List<Long> assingmentIds = sourcePage.stream().filter(e->e.getId()!=null).map(ZMyAssignmentVO::getId).distinct().collect(Collectors.toList());
		// 工艺数据
		List<MyAssignmentApplyTechnology> applyTechs = myAssignmentApplyTechnologyDao.findByAssignmentApplyIds(applyIds);
		Map<Long, List<MyAssignmentApplyTechnology>> applyTechMap = applyTechs.stream().collect(Collectors.groupingBy(MyAssignmentApplyTechnology::getAssignmentApplyId));
		List<ZMyAssignment> zMyAssignments = this.getAssignmentByIds(assingmentIds);
		Map<Long, ZMyAssignment> assignmentMap = zMyAssignments.stream().collect(Collectors.toMap(ZMyAssignment::getId, a -> a, (k1, k2) -> k1));
		final CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true).setIgnoreProperties(
				"id",
				"createTime",
				"updateTime",
				"isDeleted",
				"userId",
				"categoryId",
				"validityTime",
				"latestApplicantAvatar",
				"userType",
				"selectedFactory");
		sourcePage.forEach(e->{
			ZMyAssignment assignment = assignmentMap.get(e.getId());
			if(null!=assignment){
				BeanUtil.copyProperties(assignment, e, false, copyOptions);
			}else{
				log.error("assignmentId not exist: {}", e.getId());
			}

			List<MyAssignmentApplyTechnology> techs = applyTechMap.get(e.getAssignmentApplyId());
			if(!CollectionUtils.isEmpty(techs)){
				List<ZMyAssignmentApplyTechnonogyVO> vos = new ArrayList<>(techs.size());
				for(MyAssignmentApplyTechnology tech: techs){
					ZMyAssignmentApplyTechnonogyVO vo = new ZMyAssignmentApplyTechnonogyVO();
					vo.setId(tech.getTechnologyId());
					vo.setName(tech.getTechnologyName());
					vos.add(vo);
				}
				e.setTechnologies(vos);
			}else{
				log.error("assignmentId not exist applyTechMap: {}", e.getAssignmentApplyId());
				e.setTechnologies(null);
			}
		});

		return sourcePage;
	}

	static public ZMyAssignmentVO fromZMyAssignmentApply(ZMyAssignmentApply a) {
		if(null==a) {
			return null;
		}
		
		ZMyAssignmentVO result = new ZMyAssignmentVO();
		// 主要拷贝 price createTime updateTime
		BeanUtils.copyProperties(a, result, ZMyAssignmentVO.class);
		result.setId(a.getAssignmentId());
		result.setAssignmentApplyId(a.getId());
		result.setPrice(BigDecimalUtil.rounding4_5Cut0(a.getPrice()).toString());
		
		return result;
	}
	
	@Override
	public ZMyAssignmentDetailVO getAssignmentDetailById(Long id, Long userId) {
		// id 是否存在
		final ZMyAssignment assignment = this.getAssignmentById(id);
		if(null==assignment){
			log.error("getAssignmentDetailByI error: {}", id);
			throw new BusinessException("id not exist: "+id);
		}
		
		// 是否自己查看
		boolean isSelf = false;
		if(null!=userId) {
			isSelf = userId==assignment.getUserId();	
		}
		
		return this.makeAssignmentDetailVO(assignment, isSelf);
	}

	/**
	 * ZMyAssignmentVO 转成 ZMyAssignmentDetailVO
	 * @param isSelf 是否自己查看
	 * @author yeyi
	 * @date 2019年9月9日
	 */
	private ZMyAssignmentDetailVO makeAssignmentDetailVO(ZMyAssignment assignment, final boolean isSelf) {
		ZMyAssignmentDetailVO result = new ZMyAssignmentDetailVO();
		result = ZMyAssignmentVO.fromZMyAssignment(result, assignment);
		
		// 申请人列表
		List<ZMyAssignmentApplyVO> applyVos = this.findZMyAssignmentApplyVOByAssignmentId(assignment.getId());
		List<ZMyAssignmentApplyVO> selectedVos = new LinkedList<>();
		for(ZMyAssignmentApplyVO applyVo: applyVos) {
			if(applyVo.getSelected()) {
				selectedVos.add(applyVo);
			}
		}

		result.setApplicants(applyVos);
		if(!result.getApplicantNum().equals((long)selectedVos.size())){
            log.error("applicantNum err: {},{}", result.getApplicantNum(), selectedVos.size());
        }
		result.setSelectedApplicants(selectedVos);
		result.setApplicantNum((long) applyVos.size());

        // 分类名
        List<ProductionCategory> categories = productionCategoryDao.findByIdIn(Arrays.asList(assignment.getCategoryId()));
        if(!CollectionUtils.isEmpty(categories)){
            result.setCategoryName(categories.get(0).getName());
        }else{
            log.error("category not exist: {}", assignment.getCategoryId());
        }

		return result;
	}

	@Override
	public void acceptAssigmentApply(AssignmentAcceptParamsDTO dto, long userId) {
		ZMyAssignment assignment = zmyassignmentDao.find1ByIdAndDeleted(dto.getAssignmentId(), false);
		if(null==assignment) {
			throw new BusinessException("外发 id 不存在");
		}
		
		// 外发单是否属于当前用户
		if(userId!=assignment.getUserId()) {
			throw new BusinessException("外发 id 不属于你");
		}
		
		// 校验状态
		if(assignment.getStatus()== AssignmentStatusEnum.END.getValue() || assignment.getStatus()== AssignmentStatusEnum.CANCEL.getValue()) {
			throw new BusinessException("外发状态不正确");
		}
		
		// 校验外发 ID 与申请 ID 是否对应
		List<ZMyAssignmentApply> applies = this.findZMyAssignmentApplyByAssignmentId(dto.getAssignmentId());
		if(CollectionUtils.isEmpty(applies)) {
			throw new BusinessException("没有外发申请");
		}
		
		if(!CollectionUtils.isEmpty(dto.getApplyIds())) {
			Map<Long, ZMyAssignmentApply> applyMap = applies.stream().collect(Collectors.toMap(ZMyAssignmentApply::getId, a -> a, (k1, k2) -> k1));
			for(Long applyId: dto.getApplyIds()) {
				if(null==applyMap.get(applyId)) {
					throw new BusinessException("申请ID不存在: "+applyId);
				}
			}
			
			int updateNum = zmyassignmentapplyDao.updateSelectedInIds(dto.getApplyIds(), true);
			if(updateNum<=0) {
				throw new BusinessException("更新选中状态失败: "+dto.getApplyIds());
			}
		}
		
		// 更新外发状态
		int updateNum = zmyassignmentDao.updateStatusInIds(Arrays.asList(dto.getAssignmentId()), AssignmentStatusEnum.END.getValue());
		if(updateNum<=0) {
			throw new BusinessException("更新外发状态失败: "+dto.getAssignmentId());
		}
	}

	@Override
	public void cancelAssignment(long assignmentId, long userId) {
		ZMyAssignment assignment = zmyassignmentDao.find1ByIdAndDeleted(assignmentId, false);
		if(null==assignment) {
			throw new BusinessException("外发 id 不存在");
		}
		
		// 外发单是否属于当前用户
		if(userId!=assignment.getUserId()) {
			throw new BusinessException("外发 id 不属于你");
		}
		
		// 校验状态
		if(assignment.getStatus()== AssignmentStatusEnum.END.getValue() || assignment.getStatus()== AssignmentStatusEnum.CANCEL.getValue()) {
			throw new BusinessException("外发状态不正确");
		}
		
		// 更新外发状态
		int updateNum = zmyassignmentDao.updateStatusInIds(Arrays.asList(assignmentId), AssignmentStatusEnum.CANCEL.getValue());
		if(updateNum<=0) {
			throw new BusinessException("更新外发状态失败: "+assignmentId);
		}
	}

	@Override
	public void checkAssignmentExpired() {
		List<ZMyAssignment> all = zmyassignmentDao.findByStatusAndDeletedExpired(Arrays.asList(AssignmentStatusEnum.ACTIVE.getValue()), false);
		if(CollectionUtils.isEmpty(all)){
			return;
		}

		all.stream().forEach(e->{
			// 一小时的毫秒数
			final long curMill = System.currentTimeMillis();
			log.info("checkAssignmentExpire: id:{}, curHour:{}, releaseHour:{}, validatyTime:{}"
					, e.getId()
					, DateUtil.longToStr(curMill)
					, DateUtil.dateToStr(e.getReleaseTime())
					, e.getValidityTime());
			e.setStatus(AssignmentStatusEnum.EXPIRED.getValue());
			e.setUpdateTime(new Date(curMill));
			zmyassignmentDao.save(e);
		});
	}
	
	/**
	 * 从 FactoryProductionTechnology 中取出所有最顶层工艺ID
	 * @author yeyi
	 * @date 2019年10月25日
	 */
	private List<Long> getRootTechnologyIds(List<FactoryProductionTechnology> productionTechnology){
		List<Long> result = new ArrayList<>(productionTechnology.size());
		for(FactoryProductionTechnology tech: productionTechnology) {
			result.add(this.getRootProductionTechnologyId(tech.getTechnology()));
		}
		return result;
	}
	
	/**
	 * 获取最顶层工艺ID
	 * @author yeyi
	 * @date 2019年10月25日
	 */
	private Long getRootProductionTechnologyId(ProductionTechnology tech) {
		if(null==tech.getParent()) {
			return tech.getId();
		}
		
		return this.getRootProductionTechnologyId(tech.getParent());
	}

	@Override
	public List<ZMyAssignmentVO> recommend(AssignmentRecommendParamsDTO para) {
		if(null==para.getResultLength() || para.getResultLength()<1) {
			para.setResultLength(6L);
		}

		boolean isLogin = -1!=para.getUserId();
		Double latitude = 1.0;
		Double longitude = 1.0;
		List<Long> technologyIds = new ArrayList<>(1);
		if(isLogin){
			Factory factory = null;
			try {
				factory = factoryService.getFactoryByUserId(para.getUserId());
				FactoryVO factoryVO = factoryService.getFactoryVOByUserId(para.getUserId());
				if(null==factoryVO){
					log.error("recommen no factory: {}", para.getUserId());
					technologyIds.add(-1L);
				}else{
					List<FactoryProductionTechnology> productionTechnologys = factoryVO.getProductionTechnologys();
					technologyIds = this.getRootTechnologyIds(productionTechnologys);
					if(CollectionUtils.isEmpty(technologyIds)){
						log.error("getFactoryVOByUserI no technology: {}", para.getUserId());
						technologyIds.add(-1L);
					}
				}

				if(null!=factory) {
					latitude = factory.getContact().getAddress().getLatitude();
					longitude = factory.getContact().getAddress().getLongitude();
				}

			} catch (Exception e) {
				log.error("getLatitud err1: {},{}", para.getUserId(), null==factory?"":JSON.toJSONString(factory.getContact()));
				log.error("getLatitud err: ", e);
			}
		}else{
			try {
				latitude = Double.parseDouble(para.getLatitude());
				longitude = Double.parseDouble(para.getLongitude());
			} catch (Exception e) {
				log.error("getLatitud err21: {},{}", para.getLongitude(), para.getLatitude());
				log.error("getLatitud err22: ", e);
			}
		}

		try {
			List<ZMyAssignment> assignments = null;
			if(isLogin){
				assignments = zmyassignmentDao.recomnend(para.getUserId(), latitude+"",
						longitude+"", para.getResultLength(), technologyIds);	
			}else{
				assignments = zmyassignmentDao.randomList(para.getResultLength());
			}
			if(CollectionUtils.isEmpty(assignments)) {
				log.error("zmyassignmentDao.recomnen empty: {}", JSON.toJSONString(para));
				return new ArrayList<>(1);
			}
			CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true); // 空值不拷贝
			List<ZMyAssignmentVO> result = new ArrayList<>(assignments.size());
			for(ZMyAssignment a: assignments) {
				ZMyAssignmentVO re = new ZMyAssignmentVO();
				BeanUtil.copyProperties(a, re, false, copyOptions);
				// 工艺数据
				if(StringUtils.isNotBlank(a.getTechnologyJson())){
					List<ZMyAssignmentApplyTechnonogyVO> technonogyVOS = JSON.parseArray(a.getTechnologyJson(), ZMyAssignmentApplyTechnonogyVO.class);
					re.setTechnologies(technonogyVOS);
				}else{
					log.error("technologyJson empty: {}", a.getId());
				}

				ZMyAssignmentVO.makeValidityTimeAndStatus(re, a.getReleaseTime(), a.getValidityTime());
				result.add(re);
			}

			return result;
		}catch (Exception e) {
			log.error("recomnen err1: {}", JSON.toJSONString(para));
			log.error("recomnen err: ", e);
		}
		return new ArrayList<>(1);
	}

	@Override
	public List<MyAssignmentRobot> getAllAssignmentRobot() {
		return zMyAssignmentRobotDao.findByDeleted(false);
	}

	@Override
	public void randomAddVisitNum() {
		List<ZMyAssignment> assignments = zmyassignmentDao.findByStatusAndDeleted(AssignmentStatusEnum.ACTIVE.getValue(), false);
		if(CollectionUtils.isEmpty(assignments)) {
			return;
		}
		
        long startTime = System.currentTimeMillis();
        final int MAX_ADD = Integer.parseInt(configAssignmentService.getIndexFunctionUseCountAddMax());
        Random r = new Random(System.currentTimeMillis());

        assignments.stream().forEach(e->{
            final Long visitNumOld = e.getVisitsNum();
            Long random = (long)r.nextInt(MAX_ADD) + 1;
            Long visitNum = visitNumOld + random;
            e.setVisitsNum(visitNum);
            zmyassignmentDao.save(e);
        });
        log.info("randomAddVisitNum spendTime: {}", System.currentTimeMillis()-startTime);
	}

	@Override
	public int updateRobotDeleted(boolean isDeleted, List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)) {
			return 0;
		}
		
		return zMyAssignmentRobotDao.updateDeleted(isDeleted, ids);
	}
}
