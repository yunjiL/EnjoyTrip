package com.ssafy.enjoytrip.board.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.board.model.BoardDto;
//import com.ssafy.enjoytrip.board.model.FileInfoDto;
import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	@Transactional
	public void writeArticle(BoardDto boardDto) throws Exception {
		System.out.println("글입력 전 dto : " + boardDto);
		boardMapper.writeArticle(boardDto);
		System.out.println("글입력 후 dto : " + boardDto);
//		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
//		if (fileInfos != null && !fileInfos.isEmpty()) {
//			boardMapper.registerFile(boardDto);
//		}s
	}

	@Override
	public List<BoardDto> searchList(String key, String word) throws Exception {
		Map<String, String> param = new HashMap<String, String>();

		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", word.isEmpty() ? "" : word);
		if (!key.isEmpty() && !word.isEmpty()) {
			if ("subject".equals(key)) {
				System.out.println("subject");
				return boardMapper.searchListBySubject(param);
			} else {
				System.out.println("else");
				return boardMapper.searchListByOther(param);
			}
		}
		System.out.println("all");
		return boardMapper.searchListAll(param);
	}
	
	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardMapper.updateHit(articleNo);
	}

	@Override
	@Transactional
	public void modifyArticle(BoardDto boardDto) throws Exception {
		boardMapper.modifyArticle(boardDto);
	}

	@Override
	@Transactional
	public void deleteArticle(int articleNo) throws Exception {
//		List<FileInfoDto> fileList = boardMapper.fileInfoList(articleNo);
		boardMapper.deleteArticle(articleNo);
//		boardMapper.deleteArticle(articleNo);
//		for (FileInfoDto fileInfoDto : fileList) {
//			File file = new File(
//					path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
//			file.delete();
//		}
	}

}