package com.outmao.ebs.bbs.domain.impl;

import com.outmao.ebs.bbs.dao.BoardDao;
import com.outmao.ebs.bbs.domain.BbsDomain;
import com.outmao.ebs.bbs.dto.board.BoardDTO;
import com.outmao.ebs.bbs.entity.Board;
import com.outmao.ebs.bbs.entity.BoardStats;
import com.outmao.ebs.common.base.BaseDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class BbsDomainImpl extends BaseDomain implements BbsDomain {



	@Autowired
	private BoardDao boardDao;


	@Transactional
	@Override
	public Board saveBoard(BoardDTO request) {
		Board board = request.getId()==null?null:boardDao.getOne(request.getId());
		if (board==null) {
			board = new Board();
			board.setCreateTime(new Date());
			BoardStats stats=new BoardStats();
			stats.setBoard(board);
			board.setStats(stats);
		}
		BeanUtils.copyProperties(request,board);
		board.setUpdateTime(new Date());
		boardDao.save(board);

		return board;
	}

	@Override
	public void deleteBoardById(Long id)
	{
		Board board = boardDao.getOne(id);
		boardDao.delete(board);
	}

	@Override
	public Board getBoardByType(String type) {
		return boardDao.findByType(type);
	}

	@Override
	public List<Board> getBoardList() {
		return boardDao.findAll();
	}







}
