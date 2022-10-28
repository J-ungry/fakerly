package com.joongbu.fakerly.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.SidePreferDto;

@Mapper
public interface SidePreferMapper {
	
	int like(int user_no,int side_board_no);
	int unlike(int user_no,int side_board_no);
	SidePreferDto check(int user_no,int side_board_no);
}
