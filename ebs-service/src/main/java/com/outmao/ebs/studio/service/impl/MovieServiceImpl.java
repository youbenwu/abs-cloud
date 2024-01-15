package com.outmao.ebs.studio.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.studio.domain.MovieDomain;
import com.outmao.ebs.studio.dto.*;
import com.outmao.ebs.studio.entity.*;
import com.outmao.ebs.studio.service.MovieService;
import com.outmao.ebs.studio.vo.MovieEpisodeVO;
import com.outmao.ebs.studio.vo.MovieVO;
import com.outmao.ebs.studio.vo.VideoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;


@Slf4j
@Service
public class MovieServiceImpl extends BaseService implements MovieService {

    @Autowired
    private MovieDomain movieDomain;


    @Override
    public Movie saveMovie(MovieDTO request) {
        Movie movie=movieDomain.saveMovie(request);
        return movie;
    }

    @Override
    public void deleteMovieById(Long id) {
        movieDomain.deleteMovieById(id);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieDomain.getMovieById(id);
    }

    @Override
    public MovieVO getMovieVOById(Long id) {
        return movieDomain.getMovieVOById(id);
    }

    @Override
    public Page<MovieVO> getMovieVOPage(GetMovieListDTO request, Pageable pageable) {
        return movieDomain.getMovieVOPage(request,pageable);
    }


    @Override
    public MovieEpisode saveMovieEpisode(MovieEpisodeDTO request) {
        return movieDomain.saveMovieEpisode(request);
    }

    @Override
    public void deleteMovieEpisodeById(Long id) {
        movieDomain.deleteMovieEpisodeById(id);
    }

    @Override
    public MovieEpisode getMovieEpisodeById(Long id) {
        return movieDomain.getMovieEpisodeById(id);
    }

    @Override
    public MovieEpisodeVO getMovieEpisodeVOById(Long id) {
        return movieDomain.getMovieEpisodeVOById(id);
    }

    @Override
    public List<MovieEpisodeVO> getMovieEpisodeVOListByMovieId(Long movieId) {
        return movieDomain.getMovieEpisodeVOListByMovieId(movieId);
    }

    @Override
    public List<MovieEpisodeVO> getMovieEpisodeVOListByMovieIdIn(Collection<Long> movieIdIn) {
        return movieDomain.getMovieEpisodeVOListByMovieIdIn(movieIdIn);
    }

    @Override
    public Video saveVideo(VideoDTO request) {
        return movieDomain.saveVideo(request);
    }

    @Override
    public void deleteVideoById(Long id) {
        movieDomain.deleteVideoById(id);
    }

    @Override
    public List<VideoVO> getVideoVOListByMovieIdIn(Collection<Long> movieIdIn)
    {
        return movieDomain.getVideoVOListByMovieIdIn(movieIdIn);
    }

    @Override
    public List<VideoVO> getVideoVOListByEpisodeIdIn(Collection<Long> episodeIdIn) {
        return movieDomain.getVideoVOListByEpisodeIdIn(episodeIdIn);
    }

    @Override
    public List<VideoVO> getVideoVOList(GetVideoListDTO request) {

        return movieDomain.getVideoVOList(request);
    }

    @Override
    public Page<VideoVO> getVideoVOPage(GetVideoListDTO request, Pageable pageable) {

        return movieDomain.getVideoVOPage(request,pageable);
    }

    @Override
    public UserMovie saveUserMovie(Long userId, Long movieId) {
        return movieDomain.saveUserMovie(userId,movieId);
    }

    @Override
    public UserMovieEpisode saveUserMovieEpisode(Long userId, Long episodeId) {
        return movieDomain.saveUserMovieEpisode(userId,episodeId);
    }

    @Override
    public UserMovieEpisode getUserMovieEpisode(Long userId, Long episodeId) {
        return movieDomain.getUserMovieEpisode(userId,episodeId);
    }
}
