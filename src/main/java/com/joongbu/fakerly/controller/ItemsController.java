package com.joongbu.fakerly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joongbu.fakerly.dto.KeywordDto;
import com.joongbu.fakerly.dto.LicenseDto;
import com.joongbu.fakerly.dto.SkillDto;
import com.joongbu.fakerly.mapper.ItemsMapper;

import lombok.Data;


@RequestMapping("/items")
@Controller
public class ItemsController {

	@Autowired
	ItemsMapper itemsMapper;
	
	@GetMapping("/keywordList.do")
	public @ResponseBody List<KeywordDto> keywordList(int userNo){
		
		List<KeywordDto> keywordList = null;
		
		try {
			keywordList = itemsMapper.keywordList(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return keywordList;
	}
	
	@GetMapping("/skillList.do")
	public @ResponseBody List<SkillDto> skillList(int userNo){
		
		List<SkillDto> skillList = null;
		
		try {
			skillList = itemsMapper.skillList(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return skillList;
	}
	
	@GetMapping("/licenseList.do")
	public @ResponseBody List<LicenseDto> licenseList(int userNo){
		
		List<LicenseDto> licenseList = null;
		
		try {
			licenseList = itemsMapper.licenseList(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return licenseList;
	}
	
	@Data
	class CheckStatus{
		private int status;
	}
	
	@GetMapping("/insertUserKeyword.do")
	public @ResponseBody CheckStatus insertUserKeyword(int userNo, int keywordNo) {
		
		CheckStatus checkStatus = new CheckStatus();
		int insert = 0;
		
		try {
			insert = itemsMapper.insertUserKeyword(userNo, keywordNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		checkStatus.setStatus(insert);
		
		return checkStatus;
	}
	
	@GetMapping("/insertUserSkill.do")
	public @ResponseBody CheckStatus insertUserSkill(int userNo, int skillNo) {
		
		CheckStatus checkStatus = new CheckStatus();
		int insert = 0;
		
		try {
			insert = itemsMapper.insertUserSkill(userNo, skillNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		checkStatus.setStatus(insert);
		
		return checkStatus;
	}
	
	@GetMapping("/insertUserLicense.do")
	public @ResponseBody CheckStatus insertUserLicense(int userNo, int licenseNo) {
		
		CheckStatus checkStatus = new CheckStatus();
		int insert = 0;
		
		try {
			insert = itemsMapper.insertUserLicense(userNo, licenseNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		checkStatus.setStatus(insert);
		
		return checkStatus;
	}
	
	@GetMapping("/deleteUserKeyword.do")
	public @ResponseBody CheckStatus deleteUserKeyword(int userNo, int keywordNo) {
		
		CheckStatus checkStatus = new CheckStatus();
		int delete = 0;
		
		try {
			delete = itemsMapper.deleteUserKeyword(userNo, keywordNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		checkStatus.setStatus(delete);
		
		return checkStatus;
	}
	
	@GetMapping("/deleteUserSkill.do")
	public @ResponseBody CheckStatus deleteUserSkill(int userNo, int skillNo) {
		
		CheckStatus checkStatus = new CheckStatus();
		int delete = 0;
		
		try {
			delete = itemsMapper.deleteUserSkill(userNo, skillNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		checkStatus.setStatus(delete);
		
		return checkStatus;
	}
	
	@GetMapping("/deleteUserLicense.do")
	public @ResponseBody CheckStatus deleteUserLicense(int userNo, int licenseNo) {
		
		CheckStatus checkStatus = new CheckStatus();
		int delete = 0;
		
		try {
			delete = itemsMapper.deleteUserLicense(userNo, licenseNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		checkStatus.setStatus(delete);
		
		return checkStatus;
	}
	
	@GetMapping("/listUserKeyword.do")
	public void listUserKeyword(int userNo, Model model) {
		
		List<KeywordDto> userKeywordList = null;
		
		try {
			userKeywordList = itemsMapper.userKeywordList(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("userKeywordList",userKeywordList);
		model.addAttribute("userNo",userNo);
	}
	
	@GetMapping("/listUserSkill.do")
	public void listUserSkill(int userNo, Model model) {
		
		List<SkillDto> userSkillList = null;
		
		try {
			userSkillList = itemsMapper.userSkillList(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("userSkillList",userSkillList);
		model.addAttribute("userNo",userNo);
	}
	
	@GetMapping("/listUserLicense.do")
	public void listUserLicense(int userNo, Model model) {
		
		List<LicenseDto> userLicenseList = null;
		
		try {
			userLicenseList = itemsMapper.userLicenseList(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("userLicenseList",userLicenseList);
		model.addAttribute("userNo",userNo);
	}
}
