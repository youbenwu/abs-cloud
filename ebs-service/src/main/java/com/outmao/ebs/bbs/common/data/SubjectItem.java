package com.outmao.ebs.bbs.common.data;


import com.outmao.ebs.bbs.entity.SubjectStats;

public interface SubjectItem {

    public Long getSubjectId();

    public void setStats(SubjectStats stats);
    public void setFav(Boolean fav);
    public void setLike(Boolean like);
    public void setDislike(Boolean dislike);


}
