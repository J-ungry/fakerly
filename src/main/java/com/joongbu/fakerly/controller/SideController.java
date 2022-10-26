package com.joongbu.fakerly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joongbu.fakerly.dto.RolesDto;
import com.joongbu.fakerly.dto.SideDto;
import com.joongbu.fakerly.mapper.RolesMapper;
import com.joongbu.fakerly.mapper.SideMapper;

@RequestMapping("/sideboard")
@Controller
public class SideController {
	@Autowired
	SideMapper sideMapper;
	
	@Autowired
	RolesMapper roleMapper;
	
	@GetMapping("/list.do") //목록 출력하기 
	public String list(
			Model model,
			String searchRoleNo
			) {
		List<SideDto> sideList = null;
		try {
			sideList = sideMapper.list(searchRoleNo);
			System.out.println("work");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("sideList",sideList); //내용 전달해주기 
		return "/sideboard/list";
	}
	
	@GetMapping("/detail.do")
	public String detail(
			@RequestParam(required=true) int sideBoardNo, 
			Model model
			){
		SideDto side = null;
		List<RolesDto> roles = null;
		try {
			side = sideMapper.detail(sideBoardNo);
			roles = roleMapper.list(sideBoardNo);
			System.out.println("work detail");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("roles",roles);
		model.addAttribute("side",side);
		return "/sideboard/detail";
	}
	
	@GetMapping("/update.do") //update 클릭하자마자 띄우는거
	public String update(
			@RequestParam(required = true) int sideBoardNo,
			Model model
			) {
		SideDto side=null;
		List<RolesDto> roles=null;
		try {
			side=sideMapper.detail(sideBoardNo);

			roles = roleMapper.list(sideBoardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("side",side);
		model.addAttribute("roles",roles);
		System.out.println(side);
		System.out.println("work update");
		return "/sideboard/update";
	}
	
	@PostMapping("/update.do") //update수정 버튼 누르면 실행되는거 
	public String update(
			SideDto side
			) {
		int update=0;
		try {
			
			System.out.println("work123123");
			update = sideMapper.update(side);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(update>0) {
			return "redirect:/sideboard/detail.do?sideBoardNo="+side.getSideBoardNo();
		}else {
			return "redirect:/sideboard/update.do?sideBoardNo="+side.getSideBoardNo();
		}
	}
}
