package com.joongbu.fakerly.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.fakerly.dto.TempBoardDto;
import com.joongbu.fakerly.dto.UserDto;
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
			System.out.println(tempList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempList==null) {
			return "/mainboard/main";
		}else {
			model.addAttribute("tempList", tempList);
			return "/tempboard/templist";
		}
	}

	@GetMapping("/detail.do")
	public String detail(
			@RequestParam (required = true) int tempNo,
			Model model
			) {
		TempBoardDto tempboard =null;
		try {
			tempboard=tempboardMapper.detail(tempNo);
			System.out.println("detail!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempboard!=null) {
			model.addAttribute("tempboard" ,tempboard);
			return "/tempboard/detail";
		}else {
			System.out.println("ERROR");
			return "redirect:/tempboard/templist.do";
		}
	}
	@GetMapping("/insert.do")
	public String insert(
			@SessionAttribute(required = false)UserDto loginUser,
			HttpSession session
			) {
		String msg="";
		if(loginUser!=null) {
			return"tempboard/insert";
		}else {
			msg="ERROR";
			System.out.println("HEllo");
			session.setAttribute("msg", msg);
			return "redirect:/user/login.do";
		}
	}
	@PostMapping("/insert.do")
	public String insert(
			TempBoardDto tempboard,
			@SessionAttribute(required = false) UserDto user,
			HttpSession session
			) {
		int insert=0;
		String msg="";
		try {
			if(userNo!=null) {
				insert=tempboardMapper.insert(tempboard);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			msg="임시 저장 성공";
			session.setAttribute("msg", msg);
			return "redirect:/tempboard/detail.do?tempNo="+tempboard.getTempNo();
		}else {
				msg="게시글 작성 실패(db 오류)";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main.do";
			}
		}
	}

