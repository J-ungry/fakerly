package com.joongbu.fakerly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	// 인덱스 페이지로 접속하면 로그인 페이지로 redirect합니다.
	@GetMapping("/")
	public String index() {
		return "redirect:/user/login.do";
	}
	
	@GetMapping("/gmail")
	public String goGmail() {
		return "redirect:https://mail.google.com";
	}
	
	@GetMapping("naverMail")
	public String goNaverMail() {
		return "redirect:https://mail.naver.com";
	}
}
