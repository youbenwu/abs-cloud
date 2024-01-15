package com.outmao.ebs.studio.domain.aspect;


import com.outmao.ebs.studio.dao.UserMovieDao;
import com.outmao.ebs.studio.dao.UserMovieEpisodeDao;
import com.outmao.ebs.studio.vo.MovieVO;
import com.outmao.ebs.security.util.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Aspect
@Component
public class MovieDomainFilterAspect {


	@Autowired
	private UserMovieDao userMovieDao;

	@Autowired
	private UserMovieEpisodeDao userMovieEpisodeDao;


	@Pointcut("execution(public * com.outmao.ebs.studio.domain.MovieDomain.getMovieVOById(..))")
	public void getMovieVOById() {
	}

	@AfterReturning(returning = "movie", pointcut = "getMovieVOById()")
	public void afterGetMovieVOById(JoinPoint jp, MovieVO movie) {
		if(!SecurityUtil.isAuthenticated())
			return;

		if(movie==null)
			return;

		if(movie.getEpisodes()==null||movie.getEpisodes().isEmpty())
			return;

		Long userId=SecurityUtil.currentUserId();

		boolean m=userMovieDao.existsByUserIdAndMovieId(userId,movie.getId());

		if(m){
			movie.getEpisodes().forEach(t->{
				t.setPlayable(true);
			});
			return;
		}

		List<Long> eids=userMovieEpisodeDao.findAllEpisodeIdByUserIdAndMovieId(userId,movie.getId());

		movie.getEpisodes().forEach(t->{
			t.setPlayable(t.getFeeType()==0||eids.contains(t.getId()));
		});

	}


}
