

package com.joongbu.fakerly.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import com.joongbu.fakerly.dto.MainBoardDto;
import com.joongbu.fakerly.dto.TempBoardDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.MainBoardMapper;
import com.joongbu.fakerly.mapper.TempBoardMapper;


@RequestMapping("/tempboard")
@Controller
public class TempController {
	@Autowired
	TempBoardMapper tempboardMapper;
	@Autowired
	MainBoardMapper boardMapper;
	@GetMapping("/templist.do")
	public String templist(
			Model model,
			@SessionAttribute(required=true) UserDto loginUser,
			HttpSession session
			){
		String msg="";
		List<TempBoardDto> tempList=null;
		try {
			tempList=tempboardMapper.templist(loginUser.getUser_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempList.isEmpty()) {
			msg="임시보관 한 게시물이 없습니다.";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			msg=loginUser.getUser_name()+"님의 임시보관함입니다.";
			session.setAttribute("msg", msg);
			model.addAttribute("tempList", tempList);
			return "/tempboard/templist";
		}
	}

	@GetMapping("/detail.do")
	public String detail(
			MainBoardDto mainboard,
			@SessionAttribute(required=false) UserDto loginUser,
			Model model,
			HttpSession session
			) {
		String msg="";
		List<MainBoardDto> list=null;
		try {
			list=boardMapper.tempDetail(loginUser.getUser_no());
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list==null) {
			msg="알 수 없는 오류";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			model.addAttribute("tempboard", list);
			return "/tempboard/detail.do";
		}
	}
	@GetMapping("/insert.do")
	public String insert(
			@SessionAttribute(required = false) UserDto loginUser,
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
			MainBoardDto mainboard,
			@SessionAttribute(required = false) UserDto loginUser,
			HttpSession session
			) {
		String msg="";
		int tempInsert=0;
		try {
			tempInsert=boardMapper.tempInsert(mainboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempInsert>0) {
			msg="임시게시판 등록 완료";
			session.setAttribute("msg", msg);
			return "redirect:/tempboard/templist.do";
		} else {
			msg="임시게시판 등록 실패";
			session.setAttribute("msg", msg);
			return "/mainboard/main";
			}
		}
	@GetMapping("/update.do")
	public String update(
			@RequestParam(required=false) int tempNo,
			Model model,
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session
			) {
		System.out.println(tempNo);
		TempBoardDto tempboard=null;
		String msg="";
		try {
			tempboard=tempboardMapper.detail(tempNo);				
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempboard!=null&&tempboard.getTempNo()==tempNo) {
			model.addAttribute("tempboard",tempboard);
			return "/tempboard/update";			
		}else {
			session.setAttribute("msg", msg);
			return "redirect:/tempboard/detail.do?tempNo="+tempNo;
		}
	}
	@PostMapping("/update.do")
	public String update(
			TempBoardDto tempboard,
			@SessionAttribute(required = false) UserDto loginUser,
			HttpSession session
			) {
		int update=0;
		System.out.println(tempboard);
		String msg="";
		try {
			update=tempboardMapper.update(tempboard);					
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(update>0) {
			msg="수정 성공";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";			
		}else {
			msg="수정 실패(db 오류)";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";			
		}
	}
	@GetMapping("/delete.do")
	public String delete(
			@RequestParam(required = true) int tempNo,
			@SessionAttribute(required = false) UserDto loginUser,
			HttpSession session
			) {
		int delete=0;
		String msg="";
		try {
			if(loginUser!=null) {
				TempBoardDto tempboard=tempboardMapper.detail(tempNo);
				if(tempboard.getTempNo()==tempNo) {
					delete=tempboardMapper.delete(tempNo);
				}else {
					msg="글쓴이만 삭제 가능";
				}
			}else {
				msg="로그인해야 게시글 삭제 가능";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(delete>0) {
			msg="삭제 성공";
			session.setAttribute("msg", msg);
			return "redirect:/tempboard/templist.do";			
		}else {
			msg="삭제 실패(db 오류)";
			session.setAttribute("msg", msg);
			return "redirect:/tempboard/update.do?tempNo="+tempNo;			
		}
	}
}