package com.outmao.ebs.studio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetVideoListDTO {

    private Long orgId;

    private Long movieId;

    private Long episodeId;



}
