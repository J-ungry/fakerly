package com.joongbu.fakerly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;
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
		
		int insert = 0;
		System.out.println(user);
		
		//calc age
		Date birth = user.getBirth();
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy"); 
		user.setAge(Integer.parseInt(yearFormat.format(now)) - Integer.parseInt(yearFormat.format(birth)));
		
		//pw 암호화
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		
		try {
			insert = userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(insert>0) {
			return "/user/login";
		}else {
			return "redirect:/user/signup.do";
		}
	}
}
