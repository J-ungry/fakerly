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
	List<MainBoardDto> profileList(int user_no); //유저에 대한 게시글 정보 가져오기
}
