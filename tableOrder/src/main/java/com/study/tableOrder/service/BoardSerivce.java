package com.study.tableOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.tableOrder.dao.IboardDao;
import com.study.tableOrder.vo.BoardVo;

@Service
public class BoardSerivce {

	@Autowired
	IboardDao boardDao;

	public void insertBoard(BoardVo Vo) {
		boardDao.insertBoard(Vo);
	}

	public List<BoardVo> getBoard() {
		return boardDao.getBoard();
	}
}
