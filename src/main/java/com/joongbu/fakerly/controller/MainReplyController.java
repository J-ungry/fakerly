package com.joongbu.fakerly.controller;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.fakerly.dto.MainReplyDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.MainBoardMapper;
import com.joongbu.fakerly.mapper.MainReplyMapper;

import lombok.Data;




@RequestMapping("/reply")
@Controller
public class MainReplyController {
	@Autowired
	MainReplyMapper replyMapper;
	
	
	
	
	@PostMapping("/insert")
	public String insert(
			MainReplyDto reply,
			HttpSession session,
			@SessionAttribute(required=false) UserDto loginUser
			) {
		
		System.out.println(reply);
		int insert=0;
		insert=replyMapper.insert(reply);
		String msg="";
		try {
			insert=replyMapper.insert(reply);
			System.out.println(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			msg="댓글등록성공";
			session.setAttribute("msg", msg);
			System.out.println("뇽뇽뇽"+reply);
			return "redirect:/mainboard/detail="+reply.getMainboard_no();
		}else {
			msg="댓글등록실패";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/detail="+reply.getMainboard_no();
		}
	}
	
	
	// 로그인한 유저만 수정 버튼을 생성하여 따로 처리 필요없음.
	@PostMapping("/update")
	public String update(
			MainReplyDto reply,
			HttpSession session,
			@SessionAttribute(required=false) UserDto loginUser
			) {
		System.out.println(reply);
		
		return "";
	}
}

