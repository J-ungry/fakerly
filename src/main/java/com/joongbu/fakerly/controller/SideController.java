package com.joongbu.fakerly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.joongbu.fakerly.dto.SideDto;
import com.joongbu.fakerly.mapper.SideMapper;

@RequestMapping("/sideboard")
@Controller
public class SideController {
	@Autowired
	SideMapper sideMapper;
	
	@GetMapping("/list.do") //목록 출력하기 
	public String list(
			Model model
			) {
		List<SideDto> sideList = null;
		try {
			sideList = sideMapper.list();
			System.out.println("work");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("sideList",sideList); //내용 전달해주기 
		return "/sideboard/list";
	}
}
