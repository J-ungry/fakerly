package com.joongbu.fakerly.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.SideRoleDto;

@Mapper
public interface SideRoleMapper {
	int insert(SideRoleDto sideRole);
	int delete(int sideBoardNo);
	
}
