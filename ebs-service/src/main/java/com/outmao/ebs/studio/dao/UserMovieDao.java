package com.outmao.ebs.studio.dao;

import com.outmao.ebs.studio.entity.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserMovieDao extends JpaRepository<UserMovie,Long> {

    public void deleteAllByMovieId(Long movieId);

    public void deleteAllByOrgId(Long orgId);

    public boolean existsByUserIdAndMovieId(Long userId,Long movieId);

    @Query("select e.movieId from UserMovie e where e.userId=?1")
    public List<Long> findAllMovieIdByUserId(Long userId);

}
