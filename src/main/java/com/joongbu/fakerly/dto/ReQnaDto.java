package com.joongbu.fakerly.dto;

import java.util.Date;

import lombok.Data;

//+-------------+--------------+------+-----+-------------------+-------------------+
//| Field       | Type         | Null | Key | Default           | Extra             |
//+-------------+--------------+------+-----+-------------------+-------------------+
//| qa_reply_no | int          | NO   | PRI | NULL              | auto_increment    |
//| contents    | varchar(255) | NO   |     | NULL              |                   |
//| q_datetime  | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
//| code        | longtext     | YES  |     | NULL              |                   |
//| user_no     | int          | NO   | MUL | NULL              |                   |
//| qa_no       | int          | NO   | MUL | NULL              |                   |
//+-------------+--------------+------+-----+-------------------+-------------------+
//6 rows in set (0.01 sec)

@Data
public class ReQnaDto {
	private int rqaNo;
	private String contents;
	private Date rqaTime;
	private String code;
	private int qaNo;//qnadto의 qa_no 참조
	private int user_no; //userdto의 user_no참조
}
