
package com.joongbu.fakerly.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.UserDto;

@Mapper
public interface UserMapper {

	UserDto login(String email);

	UserDto findUserEmail(String fPhone);

	UserDto findUserPassword(String fEmail);

	int insert(UserDto user);

	UserDto checkNickname(String nickname);

	int updateUserKeyword();



	UserDto profileUser(int user_no);


	int updateUserSkill();

	int updateUserLicense();

	int giveTempPassword(String userEmail, String tempPassword);

}
