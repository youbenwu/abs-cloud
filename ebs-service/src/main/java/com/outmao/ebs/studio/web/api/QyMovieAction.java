package com.outmao.ebs.studio.web.api;


import com.outmao.ebs.mall.order.vo.QyPadToOrderVO;
import com.outmao.ebs.studio.dto.*;
import com.outmao.ebs.studio.service.MovieService;
import com.outmao.ebs.studio.service.QyMovieService;
import com.outmao.ebs.studio.vo.MovieEpisodeVO;
import com.outmao.ebs.studio.vo.MovieVO;
import com.outmao.ebs.studio.vo.QyMoviePlayableVO;
import com.outmao.ebs.studio.vo.QyMovieQrcodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "studio-movie-qy", tags = "影视模块-影视-迁眼")
@RestController
@RequestMapping("/api/studio/movie/qy")
public class QyMovieAction {

	@Autowired
    private QyMovieService qyMovieService;




    /**
     *
     * 迁眼平板获取影视二维码
     *
     * */
    @ApiOperation(value = "迁眼平板获取影视二维码", notes = "迁眼平板获取影视二维码")
    @PostMapping("/getMovieQrCode")
    public QyMovieQrcodeVO getQyMovieQrcodeVO(GetQyMovieQrcodeDTO request){
        return qyMovieService.getQyMovieQrcodeVO(request);
    }


    /**
     *
     * 迁眼平板获取影视是否可以播放
     *
     * */
    @ApiOperation(value = "迁眼平板获取影视是否可以播放", notes = "迁眼平板获取影视是否可以播放")
    @PostMapping("/getMoviePlayable")
    public QyMoviePlayableVO getQyMoviePlayableVO(GetQyMoviePlayableDTO request){
        return qyMovieService.getQyMoviePlayableVO(request);
    }


    /**
     *
     * 扫码后调用
     *
     * */
    @ApiOperation(value = "扫码后调用", notes = "扫码后调用")
    @PostMapping("/movieQrCodeScan")
    public void qyMovieQrcodeScan(QyMovieQrcodeScanDTO request){
        qyMovieService.qyMovieQrcodeScan(request);
    }

    /**
     *
     * 下单
     *
     * */
    @ApiOperation(value = "下单", notes = "下单")
    @PostMapping("/movieOrder")
    public QyPadToOrderVO qyMovieOrder(QyMovieOrderDTO request){
        return qyMovieService.qyMovieOrder(request);
    }



}
