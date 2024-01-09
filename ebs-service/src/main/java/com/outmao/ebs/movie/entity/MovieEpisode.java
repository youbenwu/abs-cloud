package com.outmao.ebs.movie.entity;

import javax.persistence.Column;

public class MovieEpisode {


    @Column(name = "_index")
    private int index;

    private String name;

    private String intro;

    private String cover;

}
