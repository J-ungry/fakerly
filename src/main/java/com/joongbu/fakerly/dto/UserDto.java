
package com.joongbu.fakerly.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserDto {
	private int user_no;
	private String user_email;
	private String user_nickname;
	private String user_password;
	private String user_type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date user_birth;
	private String user_phone;
	private char user_state;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date user_signup;
	private String user_company;
	private String user_career;
	private String user_skill;
	private String user_license;
	private String user_aboutme;
	private char user_sex;
	private int user_age;
	private String user_img;
}
