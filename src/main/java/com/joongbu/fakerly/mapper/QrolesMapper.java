package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.QnaRolesDto;


@Mapper
public interface QrolesMapper {
	List<QnaRolesDto> list(int qaNo);
}

