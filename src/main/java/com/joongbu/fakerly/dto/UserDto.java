
package com.joongbu.fakerly.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserDto {
	private int user_no;
	private String email;
	private String user_name;
	private String nickname;
	private String pw;
	private String auth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	private String phone;
	private char state;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date signup;
	private String company;
	private String career;
	private String aboutme;
	private char sex;
	private int age;
	private String img;
}	
