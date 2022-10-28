package com.joongbu.fakerly.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.QnaImgDto;
//com.joongbu.fakerly.mapper.QnaImgMapper

@Mapper
public interface QnaImgMapper{
	int qainsert(QnaImgDto qna_img);
}
