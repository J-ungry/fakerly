package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.MainReplyDto;
@Mapper
public interface MainReplyMapper {
	List<MainReplyDto> list(int mainboard_no);
	MainReplyDto detail(int reply_no);
	int insert(MainReplyDto main_reply);
	int update(MainReplyDto main_reply);
	int delete(int reply);
}
