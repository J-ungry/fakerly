package com.joongbu.fakerly.dto;

import java.util.Date; 

import lombok.Data;

/*
+---------------+--------------+------+-----+-------------------+----------------+
| Field         | Type         | Null | Key | Default           | Extra          |
+---------------+--------------+------+-----+-------------------+----------------+
| main_reply_no | int(11)      | NO   | PRI | NULL              | auto_increment |
| contents      | varchar(255) | NO   |     | NULL              |                |
| hierarchy     | int(11)      | NO   |     | NULL              |                |
| depth         | int(11)      | NO   |     | NULL              |                |
| group_no      | int(11)      | YES  |     | NULL              |                |
| reply_time    | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
| mainboard_no  | int(11)      | NO   |     | NULL              |                |
| user_no       | int(11)      | NO   |     | NULL              |                |
+---------------+--------------+------+-----+-------------------+----------------+
 */
@Data
public class MainReplyDto {

	private int main_reply_no;
	private String contents;
	private int hierarchy;
	private int group_no;
	private Date reply_time;
	private int mainboard_no;
	private int user_no;
	
}
