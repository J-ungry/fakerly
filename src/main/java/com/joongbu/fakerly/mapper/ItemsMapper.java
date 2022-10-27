package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.KeywordDto;

@Mapper
public interface ItemsMapper {

	List<KeywordDto> keywordList(int userNo);
	int insertUserKeyword(int userNo, int keywordNo);
}
