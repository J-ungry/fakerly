
package com.joongbu.fakerly.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


//+-----------+-------------+------+-----+-------------------+-------------------+
//| Field     | Type        | Null | Key | Default           | Extra             |
//+-----------+-------------+------+-----+-------------------+-------------------+
//| user_no   | int         | NO   | PRI | NULL              | auto_increment    |
//| email     | varchar(50) | NO   | UNI | NULL              |                   |
//| user_name | varchar(50) | NO   |     | NULL              |                   |
//| nickname  | varchar(50) | NO   | UNI | NULL              |                   |
//| pw        | varchar(50) | NO   |     | NULL              |                   |
//| auth      | varchar(50) | NO   |     | NULL              |                   |
//| birth     | date        | YES  |     | NULL              |                   |
//| phone     | varchar(50) | NO   | UNI | NULL              |                   |
//| state     | char(1)     | NO   |     | NULL              |                   |
//| signup    | datetime    | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
//| company   | varchar(50) | YES  |     | NULL              |                   |
//| career    | varchar(50) | YES  |     | NULL              |                   |
//| aboutme   | text        | YES  |     | NULL              |                   |
//| sex       | char(1)     | YES  |     | NULL              |                   |
//| age       | int         | NO   |     | NULL              |                   |
//| img       | blob        | YES  |     | NULL              |                   |
//+-----------+-------------+------+-----+-------------------+-------------------+
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

