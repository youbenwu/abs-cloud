package com.outmao.ebs.bbs.domain;

import com.outmao.ebs.bbs.dto.comment.CommentDTO;
import com.outmao.ebs.bbs.dto.comment.GetCommentListDTO;
import com.outmao.ebs.bbs.dto.comment.SetCommentStatusDTO;
import com.outmao.ebs.bbs.entity.Comment;
import com.outmao.ebs.bbs.entity.CommentVote;
import com.outmao.ebs.bbs.vo.CommentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentDomain {

    //Comment
    /*
     *
     * 保存评论信息
     *
     * */
    public Comment saveComment(CommentDTO request);
    /*
     *
     * 设置评论状态
     *
     * */
    public Comment setCommentStatus(SetCommentStatusDTO request);
    /*
     *
     * 获取帖子评论分页列表
     *
     * */
    public Page<CommentVO> getCommentVOPage(GetCommentListDTO request, Pageable pageable);


    //CommentVote
    /*
     *
     * 评价评论
     *
     * */
    public CommentVote saveCommentVote(Long userId, Long commentId, int vote);
    /*
     *
     * 取消评价评论
     *
     * */
    public void deleteCommentVote(Long userId, Long commentId, int vote);


}
