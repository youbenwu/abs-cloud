package com.outmao.ebs.studio.dto;

import lombok.Data;


/**
 * 迁眼扫电影码
 */
@Data
public class QyMovieQrcodeScanDTO {

    /**
     *
     * 扫码用户ID
     *
     */
    private Long userId;

    /**
     *
     * 二维码ID
     *
     */
    private Long qrCodeId;

}
