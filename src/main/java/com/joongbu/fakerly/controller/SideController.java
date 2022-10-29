package com.joongbu.fakerly.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.fakerly.dto.RolesDto;
import com.joongbu.fakerly.dto.SideDto;
import com.joongbu.fakerly.dto.SidePreferDto;
import com.joongbu.fakerly.dto.SideRoleDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.RolesMapper;
import com.joongbu.fakerly.mapper.SideMapper;
import com.joongbu.fakerly.mapper.SidePreferMapper;
import com.joongbu.fakerly.mapper.SideRoleMapper;

import lombok.Getter;
import lombok.Setter;

@RequestMapping("/sideboard")
@Controller
public class SideController {
	@Autowired
	SideMapper sideMapper;
	
	@Autowired
	RolesMapper roleMapper;
	
	@Autowired
	SideRoleMapper sideRoleMapper;
	
	@Autowired
	SidePreferMapper sidePreferMapper;
	
	@GetMapping("/list.do") //목록 출력하기 
	public String list(
			Model model,
			String searchRoleNo,
			//별도의 선언 없이 세션내용 바로 가져오기 가능 
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session
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
	
	@Getter@Setter
	class CheckPrefer{ //좋아요 확인을 위한 class 
		private int check;
		private SidePreferDto sidePrefer;
	}
	
	@GetMapping("/detail.do")
	public String detail(
			@RequestParam(required=true) int sideBoardNo, 
			@SessionAttribute(required=true) UserDto loginUser, // 로그인 유저 정보 가져오
			Model model
			){
		SideDto side = null;
		List<RolesDto> roles = null;
		CheckPrefer checkPrefer = null; //좋아요 확인을 위한 class 
		SidePreferDto sidePrefer = null; //checkPrefer 에 넣어줄 sidePrefer
		try {
			checkPrefer = new CheckPrefer();
			
			side = sideMapper.detail(sideBoardNo);
			roles = roleMapper.list(sideBoardNo);
			System.out.println("loginUserno"+loginUser.getUser_no()+"sideBoardNo"+sideBoardNo);
			sidePrefer = sidePreferMapper.check(loginUser.getUser_no(),sideBoardNo);
			System.out.println("sidePrefer="+sidePrefer);
			
			if(sidePrefer!=null) { // 만약 좋아요 정보가 있는 경우 
				checkPrefer.setCheck(1);
				checkPrefer.setSidePrefer(sidePrefer);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			checkPrefer.setCheck(-1);
			
		}
		model.addAttribute("roles",roles);
		model.addAttribute("side",side);
		model.addAttribute("prefer",checkPrefer);
		return "/sideboard/detail";
	}
	
	@GetMapping("/update.do") //update 클릭하자마자 띄우는거
	public String update(
			@RequestParam(required = true) int sideBoardNo,
			Model model,
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session
			) {
		SideDto side=null;
		List<RolesDto> roles=null;
		List<RolesDto> rolesList=null; //select * from roles;
		try {



			side=sideMapper.detail(sideBoardNo);
			roles = roleMapper.list(sideBoardNo);
			rolesList = roleMapper.getRoles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(RolesDto r: rolesList) {
			for(RolesDto r2: roles) {
				if(r.getRoleName().equals(r2.getRoleName())) {
					r.setChecked(true);
				}
			}
		}
		model.addAttribute("side",side);
		model.addAttribute("rolesList", rolesList);
		return "/sideboard/update";
	}
	
	@PostMapping("/update.do") //update수정 버튼 누르면 실행되는거 
	public String update(
			SideDto side,
			int [] roleNo,
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session
			) {
		String msg="";
		int delete=0; // side_role 삭제용 
		int update=0; //  전체 내용 Update 용 
		SideRoleDto sideRole = null;
		try {
			if(loginUser==null) { //로그인세션 없는 경우 
				msg="로그인 하세요";
				return "redirect:/user/login.do";
			}
			delete = sideRoleMapper.delete(side.getSideBoardNo());
			sideRole = new SideRoleDto();
			if(roleNo!=null) {	
				for(int no:roleNo) {
					
					sideRole.setSideBoardNo(side.getSideBoardNo());
					sideRole.setSideRoleNo(no);
					
					sideRoleMapper.insert(sideRole);
					
				}
			}
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
	
	@GetMapping("/delete.do")
	public String delete(
			@RequestParam(required=true) int sideBoardNo
			) 
	{
		int delete=0;
		try {
			delete = sideMapper.delete(sideBoardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(delete>0) {
			return "redirect:/sideboard/list.do";
		} else {
			return "redirect:/sideboard/update.do?sideBoardNo="+sideBoardNo;
		}
	}
	
	@GetMapping("/insert.do")
	public String insert(
			SideDto side,
			@SessionAttribute(required = false) UserDto loginUser,
			HttpSession session,
			Model model
			) {
		String msg="";
		List<RolesDto> rolesList=null;

		if(loginUser!=null) {
			rolesList = roleMapper.getRoles();
			model.addAttribute("rolesList", rolesList);
			return "/sideboard/insert";
		}else {
			msg="로그인 하셔야 게시글 작성이 가능합니다";
			session.setAttribute("msg", msg);
			return "redirect:/sideboard/list.do";
		}
		

	}
	@PostMapping("/insert.do") //insert 버튼 누르면 실행되는 쿼리 
	//input시 role 과 sideboard 에 동시에 내용이 들어가야한다 
	public String insert(
			SideDto side,
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session,
			int[] roleNo
			) {
		System.out.println(Arrays.toString(roleNo));
		int insert=0;
		String msg="";
		SideRoleDto sideRole = null;
		side.setUser(loginUser);
		try {
			if(loginUser!=null) {
				insert = sideMapper.insert(side); //userGenerated 로 인해 sideBoardNo 가 넘어와져있음
				if(roleNo!=null) {
					sideRole = new SideRoleDto();
					for(int no:roleNo) {
						
						sideRole.setSideBoardNo(side.getSideBoardNo());
						sideRole.setSideRoleNo(no);
						
						sideRoleMapper.insert(sideRole);
						
					}
				}
			}else {
				msg="로그인 한 유저만 게시글 작성 가능";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("side : "+side);
		return "redirect:/sideboard/detail.do?sideBoardNo="+side.getSideBoardNo();
	}
	
	
	@GetMapping("/like.do")
	public String like(
			@RequestParam(required = false) int user_no,
			@RequestParam(required = false) int side_board_no,
			@SessionAttribute(required = false) UserDto loginUser,
			HttpSession session
			) {
		String msg="";
		try {
			if(loginUser ==null) {
				msg ="로그인 후 이용 가능합니다";
				return "/user/login.do";
			}
			sidePreferMapper.like(user_no,side_board_no);
			return "redirect:/sideboard/detail.do?sideBoardNo="+side_board_no;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("msg", msg);
		return "/user/login.do";
	}
	
	@GetMapping("/unlike.do")
	public String unlike(
			@RequestParam(required = false) int user_no,
			@RequestParam(required = false) int side_board_no,
			@SessionAttribute(required = false) UserDto loginUser,
			HttpSession session
			) {
		String msg="";
		try {
			if(loginUser ==null) {
				msg ="로그인 후 이용 가능합니다";
				return "/user/login.do";
			}
			sidePreferMapper.unlike(user_no,side_board_no);
			return "redirect:/sideboard/detail.do?sideBoardNo="+side_board_no;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("msg", msg);
		return "/user/login.do";
	}
	

}
