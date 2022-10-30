package com.joongbu.fakerly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.fakerly.dto.ReQnaDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.ReQnaMapper;

@RequestMapping("/qnareply")
@Controller
public class QnaReplyController {
	@Autowired 
	ReQnaMapper reQnaMapper;
	
	//댓글 등록
	
	@PostMapping("/insert")
	public String insert(
			ReQnaDto reply,
			HttpSession session,
			@SessionAttribute(required=false) UserDto loginUser
			) {
		
		System.out.println("댓글 등록 : "+reply);
		int insert=0;
		String msg="";
		try {
			insert=reQnaMapper.insert(reply);
			System.out.println(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			msg="댓글등록성공";
			session.setAttribute("msg", msg);
			return "redirect:/qnaboard/qadetail?qadNo="+reply.getQaNo();
		}else {
			msg="댓글등록실패";
			session.setAttribute("msg", msg);
			return "redirect:/qnaboard/qadetail?qaNo="+reply.getQaNo();
		}
	}

}
