package com.joongbu.fakerly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.joongbu.fakerly.dto.MainBoardDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.MainBoardMapper;
import com.joongbu.fakerly.mapper.UserMapper;

import lombok.Data;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequestMapping("/mainboard")	// /mainboard 요청 매핑
@Controller
public class MainBoardController {
	@Autowired
	DataSource dataSource;
	@Autowired
	MainBoardMapper boardMapper;
	@Autowired
	UserMapper userMapper;
	@Value("${spring.servlet.multipart.location}")
	String imgSavePath;
	
	@GetMapping("/main")	// /mainboard/main GET 요청 처리
	public String main(Model model) {
		List<MainBoardDto> mainList=null;
		try {
			mainList=boardMapper.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("mainList", mainList);
		return "/mainboard/main";
	}
	
	//게시글 등록 get
	@GetMapping("/insert")
	public void insert() {}
	
	//게시글 등록 post
	@PostMapping("/insert")
	public String insert(
			MainBoardDto mainboard,
			@SessionAttribute(required=true) UserDto loginUser,
			HttpSession session,
		   	MultipartFile img
			) {
		String msg="";
		int insert=0;
		try {
			if(!img.isEmpty()) {
				String contentTypes[]=img.getContentType().split("/");
				System.out.println(contentTypes[0]);
				if(contentTypes[0].equals("image")) {
					String fileName="mainboard_"+System.currentTimeMillis()+"_"+((int)(Math.random()*10000))+"."+contentTypes[1];
					Path path=Paths.get(imgSavePath+"/"+fileName);
					img.transferTo(path);
					
					mainboard.setMainboardImg(fileName);
				}
			}
			System.out.println(mainboard);
			if(mainboard.getMainboardTitle().isEmpty()) {
				msg="게시글의 제목을 입력하세요.";
				session.setAttribute("msg", msg);
				return "redirect:/mainboard/insert?userNo"+loginUser.getUser_no();
			} else if(mainboard.getMainboardContents().isEmpty()) {
				msg="게시글의 내용을 입력하세요.";
				return "redirect:/mainboard/insert?userNo"+loginUser.getUser_no();
			} else {
				insert=boardMapper.insert(mainboard);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			msg="게시글을 등록했습니다.";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			msg="게시글 등록 실패. 다시 시도하세요.";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/insert?userNo"+loginUser.getUser_no();
		}
	}
	
	@GetMapping("/detail")
	public String detail(	//view가 두 번 더해짐
			@RequestParam(required=true) int mainboardNo,
			Model model
			) {
		System.out.println(mainboardNo);
		MainBoardDto mainboard=null;
		try {
			boardMapper.viewUpdate(mainboardNo);
			mainboard=boardMapper.detailReply(mainboardNo);
			System.out.println("count수 확인"+mainboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(mainboard!=null) {
			model.addAttribute("mainboard",mainboard);
			return "/mainboard/detail";
		}else {
			return "redirect:/mainboard/main";
		}
	}
	
	//mainboard 게시글 삭제 
	@GetMapping("/delete")
	public String delete(
			@RequestParam(required=true) int mainboardNo,
			@SessionAttribute(required=true) UserDto loginUser,
			HttpSession session
			) {
		int delete=0;
		String msg="";
		try {
			MainBoardDto mainboard=boardMapper.detail(mainboardNo);
			if(mainboard.getUserNo()==loginUser.getUser_no()) {
				delete=boardMapper.delete(mainboardNo);
				if(delete>0 && mainboard.getMainboardImg()!=null) {
					File imgFile=new File(imgSavePath+"/"+mainboard.getMainboardImg());
					System.out.println("이미지 파일 삭제: "+imgFile.delete());
				}
			} else {
				msg="글쓴이만 삭제 할 수 있습니다.";
				session.setAttribute("msg", msg);
				return "redirect:/mainboard/main";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(delete>0) {
			msg="삭제 성공";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		}
	}

	@GetMapping("/update")
	public String update(
			@RequestParam(required=true) int mainboardNo,
			Model model,
			@SessionAttribute(required=true) UserDto loginUser,
			HttpSession session
			) {
		MainBoardDto mainboard=null;
		String msg="";
		try {
			mainboard=boardMapper.detail(mainboardNo);
			System.out.println(mainboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(mainboard!=null && mainboard.getUserNo()==loginUser.getUser_no()) {
			model.addAttribute("mainboard", mainboard);
			return "/mainboard/update";
		} else if(mainboard.getUserNo()!=loginUser.getUser_no()) {
			// return 시 메인게시판 깨짐 (redirect로 해결)
			msg="글쓴이만 수정 가능합니다.";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			msg="알 수 없는 오류입니다. 다시 시도해주세요.";
			session.setAttribute("msg", msg);
			return "mainboard/main";
		}
	}
	@PostMapping("/update")
	public String update(
			MainBoardDto mainboard,
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session,
			MultipartFile img
			) {
		int update=0;
		String msg="";
		try {
			if(mainboard.getUserNo()==loginUser.getUser_no()) {
				if(img!=null && !img.isEmpty()) {
					String [] contentType=img.getContentType().split("/");
					if(contentType[0].equals("image")) {
						String fileName="mainboard_"+System.currentTimeMillis()+"."+((int)(Math.random()*10000))+"."+contentType[1];
						Path path=Paths.get(imgSavePath+"/"+fileName);
						img.transferTo(path);
						if(mainboard.getMainboardImg()!=null) {
							File imgFile=new File(imgSavePath+"/"+mainboard.getMainboardImg());
							System.out.println("이미지 파일 삭제: "+imgFile.delete());
						}
						mainboard.setMainboardImg(fileName);
					}
				}
				update=boardMapper.update(mainboard);
			} else {
				msg="글쓴이만 수정 가능합니다.";
				session.setAttribute("msg", msg);
				return "redirect:/mainboard/update?mainboardNo="+mainboard.getMainboardNo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(update>0) {
			msg="수정 성공";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			msg="알 수 없는 오류로 수정을 실패했습니다. 다시 시도해주세요.";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/update?mainboardNo="+mainboard.getMainboardNo();
		}
	}
	
	@PostMapping("/search")
	public String search(
			HttpSession session,
			Model model,
			@RequestParam(required=false) String searchConcept,	//검색 카테고리
			@RequestParam(required=false) String searching		//사용자가 입력한 검색내용
			) {
			String msg="";
			List<MainBoardDto> searchList=null;
			if(searching.isEmpty()) {
				msg="검색할 단어를 입력해주세요.";
				session.setAttribute("msg", msg);
				return "redirect:/mainboard/main";
			} else {
				switch(searchConcept) {
				case "제목":	//"제목" 카테고리로 검색할 경우
					try {
						searchList=boardMapper.searchTitle(searching);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(searchList.isEmpty()) {
						msg="검색 결과가 없습니다.";
						session.setAttribute("msg", msg);
						return "redirect:/mainboard/main";
					} else {
						msg="'"+searchConcept+"'/'"+searching+"' 검색결과 입니다.";
						session.setAttribute("msg", msg);
						model.addAttribute("searchList", searchList);
						return "/mainboard/searchTitle";
					}
				case "내용":	//"내용" 카테고리로 검색할 경우
					try {
						searchList=boardMapper.searchContents(searching);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(searchList.isEmpty()) {
						msg="검색 결과가 없습니다.";
						session.setAttribute("msg", msg);
						return "redirect:/mainboard/main";
					} else {
						msg="'"+searchConcept+"'/'"+searching+"' 검색결과 입니다.";
						session.setAttribute("msg", msg);
						model.addAttribute("searchList", searchList);
						return "/mainboard/searchContents";
					}
				case "작성자명":	//"작성자명" 카테고리로 검색할 경우
					try {
						searchList=boardMapper.searchName(searching);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(searchList.isEmpty()) {
						msg="검색 결과가 없습니다.";
						session.setAttribute("msg", msg);
						return "redirect:/mainboard/main";
					} else {
						msg="'"+searchConcept+"'/'"+searching+"' 검색결과 입니다.";
						session.setAttribute("msg", msg);
						model.addAttribute("searchList", searchList);
						return "/mainboard/searchName";
					}
				case "키워드":	//"키워드" 카테고리로 검색할 경우
					try {
						searchList=boardMapper.searchKeyword(searching);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(searchList.isEmpty()) {
						msg="검색 결과가 없습니다.";
						session.setAttribute("msg", msg);
						return "redirect:/mainboard/main";
					} else {
						msg="'"+searchConcept+"'/'"+searching+"' 검색결과 입니다.";
						session.setAttribute("msg", msg);
						model.addAttribute("searchList", searchList);
						return "/mainboard/searchKeyword";
					}
				}
			}
			return "/mainboard/main";
		}
	
	@GetMapping("/profile.do")
	public void profile(
			@RequestParam(required=false) int user_no,
			Model model
			
			) {
		//유저 정보 출력 (UserDto 저장 후 set)
		//게시글 정보 출력 (MainBoardDto 저장 후 set)
		List<MainBoardDto> profileList = null;
		UserDto user = new UserDto();
		try {	
			profileList= boardMapper.profileList(user_no);
			System.out.println(profileList);
			user = userMapper.profileUser(user_no);
			if(profileList.size()==0) {
				System.out.println("이거 비었는디요 ;;");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(user);
		model.addAttribute("user",user);
		model.addAttribute("profileList",profileList);
		

	}
	
	
}







