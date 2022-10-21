
package com.joongbu.fakerly.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.UserDto;

@Mapper
public interface UserMapper {
	UserDto login(String email, String pw);

	UserDto findUserEmail(String fName, String fPhone, Date fBirth);

	UserDto findUserPassword(String fEmail, String fName, String fPhone, Date fBirth);
}
