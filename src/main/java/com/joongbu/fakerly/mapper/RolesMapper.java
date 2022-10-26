package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.RolesDto;
@Mapper
public interface RolesMapper {
	List<RolesDto> list(int sideBoardNo);
}
