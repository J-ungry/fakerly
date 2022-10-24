package com.joongbu.fakerly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	MainBoardMapper mainboardMapper;
	
	
	@GetMapping("/main")	// /mainboard/main GET 요청 처리
	public String main() {
		String page="/mainboard/main";
		return page;
	}
	
	@GetMapping("/insert")
	public String insert() {
		System.out.println("get insert");
		return "/mainboard/insert";
	}
	
	public String list(
			Model model
			) {
		
		List<MainBoardDto> mainboardList=null;
		try {
			mainboardList = mainboardMapper.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("mainboardList",mainboardList);
		return "/mainboard/list";
	}
	
	
}
