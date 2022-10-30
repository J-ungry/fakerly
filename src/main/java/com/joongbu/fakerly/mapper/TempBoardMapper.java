package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.TempBoardDto;
@Mapper
public interface TempBoardMapper {
	List<TempBoardDto> templist(int userNo);
	TempBoardDto detail(int tempNo);
	int insert(TempBoardDto tempboard);
	int update(TempBoardDto tempboard);
	int delete(TempBoardDto tempboard);
}
