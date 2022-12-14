package com.joongbu.fakerly.mapper;
//com.joongbu.fakerly.mapper.QnaMapper

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.QnaDto;
@Mapper
public interface QnaMapper {
	List<QnaDto> qalist();
	QnaDto qadetail(int qaNo);
	int qainsert(QnaDto qna);
	int qaupdate(QnaDto qna);
	int qadelete(int qaNo);
	
	QnaDto qadetailReply (int qaNo);


}