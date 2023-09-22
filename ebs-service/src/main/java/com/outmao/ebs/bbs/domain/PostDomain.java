package com.outmao.ebs.bbs.domain;

import com.outmao.ebs.bbs.dto.post.*;
import com.outmao.ebs.bbs.entity.Post;
import com.outmao.ebs.bbs.entity.PostCollection;
import com.outmao.ebs.bbs.entity.PostVote;
import com.outmao.ebs.bbs.vo.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostDomain {

    //Post
    /*
     *
     * 保存帖子信息
     *
     * */
    public Post savePost(PostDTO request);
    /*
     *
     * 设置帖子在主题中是否置顶
     *
     * */
    public Post setPostTop(SetPostTopDTO request);
    /*
     *
     * 设置帖子状态
     *
     * */
    public Post setPostStatus(SetPostStatusDTO request);
    /*
     *
     * 获取帖子信息
     *
     * */
    public PostVO getPostVOById(Long id);
    /*
     *
     * 获取帖子分页列表
     *
     * */
    public Page<PostVO> getPostVOPage(GetPostListDTO request, Pageable pageable);
    /*
     *
     * 获取用户收藏的帖子分页列表
     *
     * */
    public Page<PostVO> getPostVOPage(GetCollectionPostListDTO request, Pageable pageable);



    //PostCollection
    /*
     *
     * 收藏帖子
     *
     * */
    public PostCollection savePostCollection(Long userId, Long postId);
    /*
     *
     * 取消收藏帖子
     *
     * */
    public void deletePostCollection(Long userId, Long postId);

    //PostVote
    /*
     *
     * 评价帖子
     *
     * */
    public PostVote savePostVote(Long userId, Long postId, int vote);
    /*
     *
     * 取消评价帖子
     *
     * */
    public void deletePostVote(Long userId, Long postId, int vote);



}
