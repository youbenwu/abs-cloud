package com.outmao.ebs.studio.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.studio.dto.*;
import com.outmao.ebs.studio.service.MovieService;
import com.outmao.ebs.studio.vo.MovieEpisodeVO;
import com.outmao.ebs.studio.vo.MovieVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@AccessPermissionGroup(title="影视模块",url="/studio",name="",children = {

        @AccessPermissionParent(title = "影视管理",url = "/studio/movie",name = "",children = {
                @AccessPermission(title = "保存影视",url = "/studio/movie",name = "save"),
                @AccessPermission(title = "删除影视",url = "/studio/movie",name = "delete"),
                @AccessPermission(title = "读取影视",url = "/studio/movie",name = "read"),
        }),

})


@Api(value = "admin-studio-movie", tags = "后台-影视模块-影视")
@RestController
@RequestMapping("/api/admin/studio/movie")
public class MovieAdminAction {

	@Autowired
    private MovieService movieService;


    @PreAuthorize("hasPermission(#request.orgId,'/studio/movie','save')")
    @ApiOperation(value = "保存影视", notes = "保存影视")
    @PostMapping("/save")
    public void saveMovie(MovieDTO request){
        movieService.saveMovie(request);
    }

    @PreAuthorize("hasPermission('/studio/movie','delete')")
    @ApiOperation(value = "删除影视", notes = "删除影视")
    @PostMapping("/delete")
    public void deleteMovieById(Long id){
        movieService.deleteMovieById(id);
    }

    @PreAuthorize("hasPermission('/studio/movie','read')")
    @ApiOperation(value = "获取影视", notes = "获取影视")
    @PostMapping("/get")
    public MovieVO getMovieVOById(Long id){
        return movieService.getMovieVOById(id);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/studio/movie','read')")
    @ApiOperation(value = "获取影视列表", notes = "获取影视列表")
    @PostMapping("/page")
    public Page<MovieVO> getMovieVOPage(GetMovieListDTO request, Pageable pageable){
        return movieService.getMovieVOPage(request,pageable);
    }


    @PreAuthorize("hasPermission('/studio/movie','save')")
    @ApiOperation(value = "保存影视集", notes = "保存影视集")
    @PostMapping("/episode/save")
    public void saveMovieEpisode(MovieEpisodeDTO request){
        movieService.saveMovieEpisode(request);
    }

    @PreAuthorize("hasPermission('/studio/movie','delete')")
    @ApiOperation(value = "删除影视集", notes = "删除影视集")
    @PostMapping("/episode/delete")
    public void deleteMovieEpisodeById(Long id){
        movieService.deleteMovieEpisodeById(id);
    }

    @PreAuthorize("hasPermission('/studio/movie','read')")
    @ApiOperation(value = "获取影视集", notes = "获取影视集")
    @PostMapping("/episode/get")
    public MovieEpisodeVO getMovieEpisodeVOById(Long id){
        return movieService.getMovieEpisodeVOById(id);
    }


    @PreAuthorize("hasPermission('/studio/movie','save')")
    @ApiOperation(value = "保存影视视频", notes = "保存影视视频")
    @PostMapping("/video/save")
    public void saveVideo(VideoDTO request){
        movieService.saveVideo(request);
    }

    @PreAuthorize("hasPermission('/studio/movie','delete')")
    @ApiOperation(value = "删除影视视频", notes = "删除影视视频")
    @PostMapping("/video/delete")
    public void deleteVideoById(Long id){
        movieService.deleteVideoById(id);
    }


}
