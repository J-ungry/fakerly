package com.joongbu.fakerly.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.SidePreferDto;

@Mapper
public interface SidePreferMapper {
	
	int like(SidePreferDto sidePrefer);
	int dislike(SidePreferDto sidePrefer);
}
