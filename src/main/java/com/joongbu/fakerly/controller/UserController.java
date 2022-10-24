package com.joongbu.fakerly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.UserMapper;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/signup.do")
	public void signup() {}
	
	@PostMapping("/insert.do")
	public String insert(UserDto user) {
		
		System.out.println(user);
		
		return "/";
	}
}
