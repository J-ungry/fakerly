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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.fakerly.dto.RolesDto;
import com.joongbu.fakerly.dto.SideDto;
import com.joongbu.fakerly.dto.SideRoleDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.RolesMapper;
import com.joongbu.fakerly.mapper.SideMapper;
import com.joongbu.fakerly.mapper.SideRoleMapper;

@RequestMapping("/sideboard")
@Controller
public class SideController {
	@Autowired
	SideMapper sideMapper;
	
	@Autowired
	RolesMapper roleMapper;
	
	@Autowired
	SideRoleMapper sideRoleMapper;
	
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
	
	@GetMapping("/detail.do")
	public String detail(
			@RequestParam(required=true) int sideBoardNo, 
			Model model
			){
		SideDto side = null;
		List<RolesDto> roles = null;
		try {
			side = sideMapper.detail(sideBoardNo);
			roles = roleMapper.list(sideBoardNo);
			System.out.println("work detail");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("roles",roles);
		model.addAttribute("side",side);
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
		System.out.println(side);
		System.out.println("work update");
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
		System.out.println(Arrays.toString(roleNo));
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
//	@PostMapping("/insert.do")
//	public String insert(
//			SideDto side,
//			@SessionAttribute(required=false) UserDto loginUser,
//			HttpSession session
//			) {
//		
//	}
	
}
