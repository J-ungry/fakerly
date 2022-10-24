package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.MainBoardDto;

@Mapper
public interface MainBoardMapper {
	List<MainBoardDto> list();
	int insert(MainBoardDto mainboard);
	int update(MainBoardDto mainboard);
}
