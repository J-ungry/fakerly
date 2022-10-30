package com.joongbu.fakerly.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.ReQnaDto;


@Mapper
public interface ReQnaMapper {

	int insert(ReQnaDto reply);

}


