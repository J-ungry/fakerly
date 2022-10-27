package com.joongbu.fakerly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joongbu.fakerly.dto.KeywordDto;
import com.joongbu.fakerly.mapper.ItemsMapper;

import lombok.Data;


@RequestMapping("/items")
@Controller
public class ItemsController {

	@Autowired
	ItemsMapper itemsMapper;
	
	@GetMapping("/keywordList.do")
	public @ResponseBody List<KeywordDto> keywordList(int userNo){
		
		List<KeywordDto> keywordList = null;
		
		try {
			keywordList = itemsMapper.keywordList(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return keywordList;
	}
	
	@Data
	class CheckStatus{
		private int status;//{status : 0:등록실패,1:성공,-1:로그인하세요,-2: 글쓴이만 수정 가능}
	}
	
	@GetMapping("/insertUserKeyword.do")
	public @ResponseBody CheckStatus insertUserKeyword(int userNo, int keywordNo) {
		
		CheckStatus checkStatus = new CheckStatus();
		int insert = 0;
		
		try {
			insert = itemsMapper.insertUserKeyword(userNo, keywordNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		checkStatus.setStatus(insert);
		
		return checkStatus;
	}
}
