package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.MainBoardDto;
import com.joongbu.fakerly.dto.SideDto;
import com.joongbu.fakerly.dto.TempBoardDto;
@Mapper
public interface TempBoardMapper {
	List<TempBoardDto> templist();
	TempBoardDto detail(int tempNo);
	int insert(TempBoardDto tempboard);
}
