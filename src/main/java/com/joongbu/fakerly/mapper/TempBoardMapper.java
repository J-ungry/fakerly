package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.TempBoardDto;
@Mapper
public interface TempBoardMapper {
	List<TempBoardDto> list();
	int insert(TempBoardDto tempboard);
}
