package com.outmao.ebs.studio.service;


import com.outmao.ebs.mall.order.vo.QyPadToOrderVO;
import com.outmao.ebs.studio.dto.GetQyMoviePlayableDTO;
import com.outmao.ebs.studio.dto.GetQyMovieQrcodeDTO;
import com.outmao.ebs.studio.dto.QyMovieOrderDTO;
import com.outmao.ebs.studio.dto.QyMovieQrcodeScanDTO;
import com.outmao.ebs.studio.vo.QyMoviePlayableVO;
import com.outmao.ebs.studio.vo.QyMovieQrcodeVO;

public interface QyMovieService {


    /**
     *
     * 迁眼平板获取影视二维码
     *
     * */
    public QyMovieQrcodeVO getQyMovieQrcodeVO(GetQyMovieQrcodeDTO request);


    /**
     *
     * 迁眼平板获取影视是否可以播放
     *
     * */
    public QyMoviePlayableVO getQyMoviePlayableVO(GetQyMoviePlayableDTO request);


    /**
     *
     * 扫码后调用
     *
     * */
    public void qyMovieQrcodeScan(QyMovieQrcodeScanDTO request);

    /**
     *
     * 下单
     *
     * */
    public QyPadToOrderVO qyMovieOrder(QyMovieOrderDTO request);


}
