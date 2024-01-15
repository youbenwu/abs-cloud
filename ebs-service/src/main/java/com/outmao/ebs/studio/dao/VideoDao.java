package com.outmao.ebs.studio.dao;

import com.outmao.ebs.studio.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoDao extends JpaRepository<Video,Long> {

    public void deleteAllByMovieId(Long movieId);

    public void deleteAllByEpisodeId(Long episodeId);

    public void deleteAllByOrgId(Long orgId);

}
