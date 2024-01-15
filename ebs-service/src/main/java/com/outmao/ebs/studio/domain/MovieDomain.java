package com.outmao.ebs.studio.domain;

import com.outmao.ebs.studio.dto.*;
import com.outmao.ebs.studio.entity.*;
import com.outmao.ebs.studio.vo.MovieEpisodeVO;
import com.outmao.ebs.studio.vo.MovieVO;
import com.outmao.ebs.studio.vo.VideoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface MovieDomain {

    public Movie saveMovie(MovieDTO request);

    public void deleteMovieById(Long id);

    public void deleteAllByOrgId(Long orgId);

    public Movie getMovieById(Long id);

    public MovieVO getMovieVOById(Long id);

    public Page<MovieVO> getMovieVOPage(GetMovieListDTO request, Pageable pageable);

    public MovieEpisode saveMovieEpisode(MovieEpisodeDTO request);

    public void deleteMovieEpisodeById(Long id);

    public MovieEpisode getMovieEpisodeById(Long id);

    public MovieEpisodeVO getMovieEpisodeVOById(Long id);

    public List<MovieEpisodeVO> getMovieEpisodeVOListByMovieId(Long movieId);

    public List<MovieEpisodeVO> getMovieEpisodeVOListByMovieIdIn(Collection<Long> movieIdIn);


    public Video saveVideo(VideoDTO request);

    public void deleteVideoById(Long id);


    public List<VideoVO> getVideoVOListByMovieIdIn(Collection<Long> movieIdIn);

    public List<VideoVO> getVideoVOListByEpisodeIdIn(Collection<Long> episodeIdIn);

    public List<VideoVO> getVideoVOList(GetVideoListDTO request);

    public Page<VideoVO> getVideoVOPage(GetVideoListDTO request,Pageable pageable);

    public UserMovie saveUserMovie(Long userId,Long movieId);

    public UserMovieEpisode saveUserMovieEpisode(Long userId,Long episodeId);

    public UserMovieEpisode getUserMovieEpisode(Long userId,Long episodeId);


}
