package com.ssafy.enjoytrip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardDto;

@Mapper
public interface BoardMapper {
	void writeArticle(BoardDto boardDto) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;

	List<BoardDto> searchListBySubject(String word) throws SQLException;
	List<BoardDto> searchListAll() throws SQLException;
}
