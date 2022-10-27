package com.joongbu.fakerly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joongbu.fakerly.dto.TempBoardDto;
import com.joongbu.fakerly.mapper.TempBoardMapper;

@RequestMapping("/tempboard")
@Controller
public class TempController {
	@Autowired
	TempBoardMapper tempboardMapper;
	@GetMapping("/templist.do")
	public String templist(Model model){
		System.out.println("work!");
		List<TempBoardDto> tempList=null;
		try {
			tempList=tempboardMapper.templist();
			System.out.println("work!"+tempList);
			return "/tempboard/templist";
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("tempList", tempList);
		return "/mainboard/main";
	}	
 }

