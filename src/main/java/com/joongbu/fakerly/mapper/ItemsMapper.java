package com.joongbu.fakerly.mapper;

import java.util.List; 
import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.KeywordDto;
import com.joongbu.fakerly.dto.LicenseDto;
import com.joongbu.fakerly.dto.SkillDto;

@Mapper
public interface ItemsMapper {

	List<KeywordDto> keywordList(int userNo);
	int insertUserKeyword(int userNo, int keywordNo);
	int deleteUserKeyword(int userNo, int keywordNo);
	List<KeywordDto> userKeywordList(int userNo);
	List<SkillDto> skillList(int userNo);
	int insertUserSkill(int userNo, int skillNo);
	List<SkillDto> userSkillList(int userNo);
	int deleteUserSkill(int userNo, int skillNo);
	List<LicenseDto> licenseList(int userNo);
	int insertUserLicense(int userNo, int licenseNo);
	int deleteUserLicense(int userNo, int licenseNo);
	List<LicenseDto> userLicenseList(int userNo);
}
