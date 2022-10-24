package com.joongbu.fakerly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joongbu.fakerly.dto.MainBoardDto;
import com.joongbu.fakerly.mapper.MainBoardMapper;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequestMapping("/mainboard")	// /mainboard 요청 매핑
@Controller
public class MainBoardController {
	@Autowired
	DataSource dataSource;
	@Autowired
	MainBoardMapper boardMapper;
	
	@GetMapping("/main")	// /mainboard/main GET 요청 처리
	public String main() {
		return "/mainboard/main";
	}
	
	@GetMapping("/insert")
	public void insert() {}
	@PostMapping("/insert")
	public String insert(MainBoardDto mainboard) {
		int insert=0;
		try {
			insert=boardMapper.insert(mainboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			return "redirect:/mainboard/main";
		} else {
			return "redirect:/mainboard/insert";
		}
	}
	
}
