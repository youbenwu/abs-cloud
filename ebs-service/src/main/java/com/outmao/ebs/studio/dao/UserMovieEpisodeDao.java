package com.outmao.ebs.studio.dao;

import com.outmao.ebs.studio.entity.UserMovieEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserMovieEpisodeDao extends JpaRepository<UserMovieEpisode,Long> {

    public void deleteAllByEpisodeId(Long episodeId);

    public void deleteAllByMovieId(Long movieId);

    public void deleteAllByOrgId(Long orgId);

    public UserMovieEpisode findByUserIdAndEpisodeId(Long userId,Long episodeId);

    public boolean existsByUserIdAndEpisodeId(Long userId,Long episodeId);

    public List<UserMovieEpisode> findAllByUserIdAndMovieId(Long userId,Long movieId);

    @Query("select e.episodeId from UserMovieEpisode e where e.userId=?1 and e.movieId=?2")
    public List<Long> findAllEpisodeIdByUserIdAndMovieId(Long userId, Long movieId);

    @Query("select e.movieId from UserMovieEpisode e where e.userId=?1")
    public List<Long> findAllMovieIdByUserId(Long userId);

}
