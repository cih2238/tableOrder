package com.study.tableOrder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.tableOrder.vo.BoardVo;

@Mapper
public interface IboardDao {
	public void insertBoard(BoardVo Vo);
	public List<BoardVo> getBoard();
}
