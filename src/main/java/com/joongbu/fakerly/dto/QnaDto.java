package com.joongbu.fakerly.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

//mysql> desc qna;
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
//| qa_side    | int          | YES  |     | NULL              |                   |
//| user_no    | int          | NO   | MUL | NULL              |                   |
//+------------+--------------+------+-----+-------------------+-------------------+
//9 rows in set (0.00 sec)

@Data
public class QnaDto {
	private int qaNo;
	private String title;
	private String contents;
	private String code;
	private Date qaTime;
	private int views;
	private int qa_resolve; 
//	private int user_no; //UserDto FK
	private UserDto user;
	private int likes; //select count(*) from qna_like weher prefer=1 AND board_no=no
//	private String nickname;
	private int qa_side;
	private QnaRolesDto qroles;
	private List<QnaImgDto> qaImgList;
	
	private String userName;
	private String userCompany;
	
	private List<QnaReply> qareplyList;
}
