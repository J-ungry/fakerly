package com.joongbu.fakerly.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.ReQnaDto;

@Mapper
public interface ReQnaMapper {
	int insert(ReQnaDto qa_reply_no);
	int update(ReQnaDto qa_reply_no);
	int delete(int reply);
}
