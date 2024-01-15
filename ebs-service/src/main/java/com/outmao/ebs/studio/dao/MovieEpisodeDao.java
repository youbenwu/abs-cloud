package com.outmao.ebs.studio.dao;

import com.outmao.ebs.studio.entity.MovieEpisode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieEpisodeDao extends JpaRepository<MovieEpisode,Long> {

    public void deleteAllByMovieId(Long movieId);

    public boolean existsByMovieIdAndIndex(Long movieId,int index);

    public void deleteAllByOrgId(Long orgId);

}
