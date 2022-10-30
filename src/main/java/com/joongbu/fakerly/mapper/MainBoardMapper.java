package com.joongbu.fakerly.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.MainBoardDto;

@Mapper
public interface MainBoardMapper {
	List<MainBoardDto> list();
	MainBoardDto detail(int mainboardNo);
	MainBoardDto detailReply(int mainboardNo);
	int insert(MainBoardDto mainboard);
	int update(MainBoardDto mainboard);
	int viewUpdate(int mainboardNo); 
	int delete(int mainboardNo);
	
	List<MainBoardDto> searchTitle(String mainboardTitle);
	List<MainBoardDto> searchContents(String mainboardContents);
	List<MainBoardDto> searchName(String userName);
	List<MainBoardDto> searchKeyword(String keywordName);
	
	int tempInsert(MainBoardDto mainboard);
	List<MainBoardDto> tempDetail(int userNo);
}
