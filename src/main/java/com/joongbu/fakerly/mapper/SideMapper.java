package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.SideDto;
@Mapper
public interface SideMapper {

	List<SideDto> list(); //게시글 리스트 
	int insert(SideDto side); //게시글 입력 
	int update(SideDto side); //게시글 업데이트
	int delete(int sideBoardNo);
	
	
	
}
