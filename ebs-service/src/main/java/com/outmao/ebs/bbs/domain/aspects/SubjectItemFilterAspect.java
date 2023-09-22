package com.outmao.ebs.bbs.domain.aspects;


import com.outmao.ebs.bbs.common.annotation.SubjectItemFilter;
import com.outmao.ebs.bbs.common.data.SubjectItem;
import com.outmao.ebs.bbs.dao.SubjectCollectionDao;
import com.outmao.ebs.bbs.dao.SubjectVoteDao;
import com.outmao.ebs.bbs.entity.QSubject;
import com.outmao.ebs.bbs.entity.SubjectVote;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.vo.SecurityUser;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Aspect
@Component
public class SubjectItemFilterAspect {


	@Autowired
	private SubjectCollectionDao subjectCollectionDao;

	@Autowired
	private SubjectVoteDao subjectVoteDao;

	@Autowired
	protected JPAQueryFactory QF;

	private void filter(List<? extends SubjectItem> list, boolean fav, boolean vote, boolean stats){

		if(list==null||list.isEmpty())
			return;

		List<Long> ids=list.stream().map(d->d.getSubjectId()).collect(Collectors.toList());

		if(stats) {
			//统计数据
			QSubject e = QSubject.subject;
			List<Tuple> sbjs = QF.select(e.id, e.stats).from(e).where(e.id.in(ids)).fetch();
			for (Tuple t : sbjs) {
				SubjectItem item = list.get(ids.indexOf(t.get(e.id)));
				item.setStats(t.get(e.stats));
			}
		}

		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();

			if(fav) {
				// 收藏数据
				Set<Long> likes = subjectCollectionDao.findAllSubjectIdByUserIdAndSubjectIdIn(user.getId(), ids);
				for (SubjectItem vo : list) {
					if (likes.contains(vo.getSubjectId())) {
						vo.setFav(true);
					}
				}
			}

			if(vote) {
				// 评价数据
				List<SubjectVote> replys = subjectVoteDao.findAllByUserIdAndSubjectIdIn(user.getId(), ids);
				for (SubjectVote reply : replys) {
					SubjectItem vo = list.get(ids.indexOf(reply.getSubject().getId()));
					if (reply.getVote() == 0) {
						vo.setLike(true);
					} else if (reply.getVote() == 1) {
						vo.setDislike(true);
					}
				}
			}

		}

	}


	@Pointcut("@annotation(com.outmao.ebs.bbs.common.annotation.SubjectItemFilter)")
	public void SubjectItemFilter() {

	}


	@AfterReturning(returning = "obj",value = "SubjectItemFilter() && @annotation(filter)")
	public void afterSubjectItemFilter(JoinPoint jp, Object obj, SubjectItemFilter filter) {
       if(obj==null)
       	return;
		if(obj instanceof Page){
			Page page=(Page) obj;
			filter(page.getContent(),filter.fav(),filter.vote(),filter.stats());
		}else if(obj instanceof List){
			List list=(List) obj;
			filter(list,filter.fav(),filter.vote(),filter.stats());
		}else{
			List list=new ArrayList();
			list.add(obj);
			filter(list,filter.fav(),filter.vote(),filter.stats());
		}
	}


}
