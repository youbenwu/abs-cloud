package com.outmao.ebs.bbs.domain;


import com.outmao.ebs.bbs.common.data.GetSubjectItemList;
import com.outmao.ebs.bbs.common.data.SubjectItem;
import com.outmao.ebs.bbs.dto.subject.*;
import com.outmao.ebs.bbs.entity.*;
import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.bbs.vo.SubjectVO;
import com.outmao.ebs.common.vo.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface SubjectDomain {


    //Subject
    /*
     *
     * 保存主题信息
     *
     * */
    public Subject saveSubject(SubjectDTO request);
    /*
     *
     * 保存主题信息
     *
     * */
    public Subject saveSubject(Long userId, Item item,int type);
    /*
     *
     * 获取主题信息
     *
     * */
    public SubjectVO getSubjectVOById(Long id);
    /*
     *
     * 获取主题分页列表
     *
     * */
    public Page<SubjectVO> getSubjectVOPage(GetSubjectListDTO request, Pageable pageable);

    /*
     *
     * 获取用户收藏的主题分页列表
     *
     * */
    public Page<SubjectVO> getSubjectVOPage(GetCollectionSubjectListDTO request, Pageable pageable);

    /*
     *
     * 获取用户主题数量
     *
     * */
    public Map<String,Long> getSubjectCount(GetSubjectCountDTO request);



    //SubjectCollection
    /*
     *
     * 收藏主题
     *
     * */
    public SubjectCollection saveSubjectCollection(SubjectCollectionDTO request);
    /*
     *
     * 修改收藏主题信息
     *
     * */
    public SubjectCollection modifySubjectCollection(SubjectCollectionDTO request);
    /*
     *
     * 删除收藏主题
     *
     * */
    public void deleteSubjectCollection(Long userId, Long subjectId);


    /*
     *
     * 获取用户收藏夹数量
     *
     * */
    public Map<String,Long> getSubjectCollectionCount(GetSubjectCollectionCountDTO request);

    /*
     *
     * 获取用户收藏夹所有标签
     *
     * */
    public List<String> getSubjectCollectionMarkList(Long userId);


    public <T extends SubjectItem> Page<SubjectCollectionVO<T>> getSubjectCollectionVOPage(Long userId, String itemType, GetSubjectItemList<T> getSubjectItemList, Pageable pageable);



    //SubjectVote
    /*
     *
     * 评价主题
     *
     * */
    public SubjectVote saveSubjectVote(Long userId, Long subjectId, int vote);
    /*
     *
     * 取消评价主题
     *
     * */
    public void deleteSubjectVote(Long userId, Long subjectId, int vote);


    /*
     *
     * 主题评分
     *
     * */
    public SubjectGrade saveSubjectGrade(SubjectGradeDTO request);



    public SubjectBrowse saveSubjectBrowse(Long userId, Long subjectId);

    public void deleteSubjectBrowseById(Long id);

    public void deleteSubjectBrowseList(Long userId, String itemType);

    public Page<SubjectBrowse> getSubjectBrowsePage(Long userId, String itemType, Pageable pageable);

    public <T extends SubjectItem> Page<SubjectBrowseVO<T>> getSubjectBrowseVOPage(Long userId, String itemType, GetSubjectItemList<T> getSubjectItemList, Pageable pageable);




}
