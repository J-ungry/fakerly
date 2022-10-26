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
	int update(MainBoardDto mainboard);//아직 구현 안함
	int viewUpdate(int mainboardNo); // 조회수는 아직 구현안함
	int delete(int mainboardNo);
}
