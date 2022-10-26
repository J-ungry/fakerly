package com.joongbu.fakerly.controller;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joongbu.fakerly.dto.TempBoardDto;
import com.joongbu.fakerly.mapper.TempBoardMapper;

@RequestMapping("/tempboard")
@Controller
public class TempBoardController {
	@Autowired
	DataSource dataSource;
	@Autowired
	TempBoardMapper tempMapper;
	
	@GetMapping("/temp")
	public String temp(Model model) {
		List<TempBoardDto> tempList=null;
		try {
			tempList=tempMapper.list();
			System.out.println(tempList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("tempList", tempList);
		return "/tempboard/temp";
	}
	
	@GetMapping("/insert")
	public void insert() {}
	@PostMapping("/insert")
	public String insert(TempBoardDto tempboard) {
		int insert=0;
		try {
			insert=tempMapper.insert(tempboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			return "redirect:/tempboard/temp";
		} else {
			return "redirect:/tempboard/insert";
		}
	}
	
}
