package com.outmao.ebs.studio.web.api;


import com.outmao.ebs.studio.dto.GetMovieListDTO;
import com.outmao.ebs.studio.service.MovieService;
import com.outmao.ebs.studio.vo.MovieEpisodeVO;
import com.outmao.ebs.studio.vo.MovieVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Api(value = "studio-movie", tags = "影视模块-影视")
@RestController
@RequestMapping("/api/studio/movie")
public class MovieAction {

	@Autowired
    private MovieService movieService;


    @ApiOperation(value = "获取影视", notes = "获取影视")
    @PostMapping("/get")
    public MovieVO getMovieVOById(Long id){
        return movieService.getMovieVOById(id);
    }

    @ApiOperation(value = "获取影视集", notes = "获取影视集")
    @PostMapping("/episode/get")
    public MovieEpisodeVO getMovieEpisodeVOById(Long id){
        return movieService.getMovieEpisodeVOById(id);
    }

    @ApiOperation(value = "获取影视列表", notes = "获取影视列表")
    @PostMapping("/page")
    public Page<MovieVO> getMovieVOPage(GetMovieListDTO request, Pageable pageable){
        return movieService.getMovieVOPage(request,pageable);
    }




}
