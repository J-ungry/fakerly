package com.joongbu.fakerly.mapper;

import java.util.List;

import com.joongbu.fakerly.dto.MainReplyDto;

public interface MainReplyMapper {
	List<MainReplyDto> list();
	MainReplyDto detail(int reply_no);
	int insert(MainReplyDto main_reply);
	int update(MainReplyDto main_reply);
	int delete(MainReplyDto main_reply);
}
