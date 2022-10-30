package com.joongbu.fakerly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.fakerly.dto.MainReplyDto;
import com.joongbu.fakerly.dto.ReQnaDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.ReQnaMapper;

@RequestMapping("/qareply")
@Controller
public class ReQnaController {
	@Autowired
	ReQnaMapper ReQnaMapper;
	
	
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
			insert=ReQnaMapper.insert(reply);
			System.out.println(reply);
			System.out.println(insert);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			return "redirect:/qnaboard/qadetail?qaNo="+reply.getQa_no();
		}else {
			msg="댓글등록실패";
			session.setAttribute("msg", msg);
			System.out.println(reply.getQa_no());
			return "redirect:/qnaboard/qadetail?qaNo="+reply.getQa_no();
		}
	}
	// 로그인한 유저만 수정 버튼을 생성하여 따로 처리 필요없음.
	@PostMapping("/update")
	public String update(
			ReQnaDto reply,
			HttpSession session,
			Model model
			) {
		System.out.println("update.form 출력이다 그지 깽깽이들아"+reply);
		int update=0;
		String msg="";
		try {
			update=ReQnaMapper.update(reply);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(update>0) {
			model.addAttribute("reply",reply);
			return "redirect:/qnaboard/qadetail?qaNo="+reply.getQa_no();
		}else {
			msg="댓글수정 실패";
			session.setAttribute("msg",msg);
			model.addAttribute("reply",reply);
			return "redirect:/qnaboard/qadetail?qaNo="+reply.getQa_no();
		}
		
		
	}
	//댓글 삭제 기능
	@GetMapping("/delete")
	public String delete(
			ReQnaDto reply,
			HttpSession session,
			@SessionAttribute(required=false) UserDto loginUser
			) {
		int delete=0;
		String msg="";
		System.out.println("삭제 후 reply : "+reply);
		try {
			delete=ReQnaMapper.delete(reply.getQa_reply_no());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(delete>0) {
			return "redirect:/qnaboard/qadetail?qaNo="+reply.getQa_no();
		}else {
			msg="댓글삭제실패";
			session.setAttribute("msg", msg);
			return "redirect:/qnaboard/qadetail?qaNo="+reply.getQa_no();
		}
	}

}
