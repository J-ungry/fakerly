package com.joongbu.fakerly.dto;

import java.util.Date;


import lombok.Data;

/*
 +---------------+--------------+------+-----+-------------------+-------------------+
| Field         | Type         | Null | Key | Default           | Extra             |
+---------------+--------------+------+-----+-------------------+-------------------+
| side_board_no | int          | NO   | PRI | NULL              | auto_increment    |
| title         | varchar(100) | NO   |     | NULL              |                   |
| summ          | varchar(255) | NO   |     | NULL              |                   |
| intro         | varchar(255) | NO   |     | NULL              |                   |
| purpose       | varchar(50)  | NO   |     | NULL              |                   |
| sup           | varchar(100) | NO   |     | NULL              |                   |
| attend        | varchar(50)  | NO   |     | NULL              |                   |
| state         | tinyint(1)   | NO   |     | 1                 |                   |
| post_time     | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| report        | int          | NO   |     | 0                 |                   |
| user_no       | int          | YES  | MUL | NULL              |                   |
+---------------+--------------+------+-----+-------------------+-------------------+
 */
@Data
public class SideDto {

	private int sideBoardNo;
	private String title;   
	private String summ;         
	private String intro;        
	private String purpose;      
	private String sup;        
	private String attend;       
	private int state;        
	private Date postTime;
	private int report;   
	private UserDto user; //user.user_no
	
	private int likes; //좋아요 개수 (싫어요는 별도 구현 안함)
    
}
