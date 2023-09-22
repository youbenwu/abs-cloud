package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.SubjectBrowse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectBrowseDao extends JpaRepository<SubjectBrowse,Long> {


    public SubjectBrowse findByUserIdAndSubjectId(Long userId, Long subjectId);

    public void deleteAllByUserIdAndItemType(Long userId, String itemType);

    public Page<SubjectBrowse> findAllByUserIdAndItemTypeOrderByCreateTimeDesc(Long userId, String itemType, Pageable pageable);

}
