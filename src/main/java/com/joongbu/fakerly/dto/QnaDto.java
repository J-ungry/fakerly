package com.joongbu.fakerly.dto;

import java.util.Date;

import lombok.Data;

//+------------+--------------+------+-----+-------------------+-------------------+
//| Field      | Type         | Null | Key | Default           | Extra             |
//+------------+--------------+------+-----+-------------------+-------------------+
//| qa_no      | int          | NO   | PRI | NULL              | auto_increment    |
//| title      | varchar(50)  | NO   |     | NULL              |                   |
//| contents   | varchar(255) | NO   |     | NULL              |                   |
//| code       | longtext     | YES  |     | NULL              |                   |
//| q_datetime | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
//| views      | int          | NO   |     | 0                 |                   |
//| qa_resolve | tinyint(1)   | NO   |     | 0                 |                   |
//| user_no    | int          | NO   | MUL | NULL              |                   |
//+------------+--------------+------+-----+-------------------+-------------------+
//8 rows in set (0.04 sec)

@Data
public class QnaDto {
	private int qaNo;
	private String title;
	private String contents;
	private String code;
	private Date qaTime;
	private int views;
	private int qa_resolve; 
	private int user_no; //UserDto FK
//	private UserDto user;
	private String nickname;
}
