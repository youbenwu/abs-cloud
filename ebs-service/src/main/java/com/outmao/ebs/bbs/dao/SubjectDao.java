package com.outmao.ebs.bbs.dao;


import com.outmao.ebs.bbs.entity.Subject;
import com.outmao.ebs.common.vo.BindingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectDao extends JpaRepository<Subject, Long> {


	@Query("select s.user.id from Subject s where s.id=?1")
	public Long findUserIdById(Long id);

	@Query("select s.item from Subject s where s.id=?1")
	public BindingItem findItemById(Long id);

}
