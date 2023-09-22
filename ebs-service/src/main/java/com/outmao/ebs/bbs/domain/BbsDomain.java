package com.outmao.ebs.bbs.domain;


import com.outmao.ebs.bbs.dto.board.BoardDTO;
import com.outmao.ebs.bbs.entity.Board;

import java.util.List;

public interface BbsDomain {

	//Board
	/*
	 *
	 * 保存版块信息
	 *
	 * */
	public Board saveBoard(BoardDTO request);
	/*
	 *
	 * 删除版块信息
	 *
	 * */
	public void deleteBoardById(Long id);
	/*
	 *
	 * 获取版块信息
	 *
	 * */
	public Board getBoardByType(String type);
	/*
	 *
	 * 获取版块列表
	 *
	 * */
	public List<Board> getBoardList();














}
