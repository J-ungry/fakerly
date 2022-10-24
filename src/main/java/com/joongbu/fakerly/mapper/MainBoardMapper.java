package com.joongbu.fakerly.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.MainBoardDto;

@Mapper
public interface MainBoardMapper {
	int insert(MainBoardDto mainboard);
}
