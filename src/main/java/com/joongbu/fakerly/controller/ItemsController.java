package com.joongbu.fakerly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		private int status;
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
	
	@GetMapping("/deleteUserKeyword.do")
	public @ResponseBody CheckStatus deleteUserKeyword(int userNo, int keywordNo) {
		
		CheckStatus checkStatus = new CheckStatus();
		int delete = 0;
		
		try {
			delete = itemsMapper.deleteUserKeyword(userNo, keywordNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		checkStatus.setStatus(delete);
		
		return checkStatus;
	}
	
	@GetMapping("/listUserKeyword.do")
	public void listUserKeyword(int userNo, Model model) {
		
		List<KeywordDto> userKeywordList = null;
		
		try {
			userKeywordList = itemsMapper.userKeywordList(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("userKeywordList",userKeywordList);
		model.addAttribute("userNo",userNo);
	}
}
