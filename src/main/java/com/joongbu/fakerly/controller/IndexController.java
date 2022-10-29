package com.joongbu.fakerly.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	// 인덱스 페이지로 접속하면 로그인 페이지로 redirect합니다.
	@GetMapping("/")
	public String index(HttpServletRequest req, HttpSession session) {
		System.out.println(req.getSession().getAttribute("loginUser"));
		if (req.getSession().getAttribute("loginUser") != null) {
			return "redirect:/mainboard/main";
		}
		return "redirect:/user/login.do";
	}

	@GetMapping("/gmail")
	public String goGmail() {
		return "redirect:https://mail.google.com";
	}

	@GetMapping("/naverMail")
	public String goNaverMail() {
		return "redirect:https://mail.naver.com";
	}
}
