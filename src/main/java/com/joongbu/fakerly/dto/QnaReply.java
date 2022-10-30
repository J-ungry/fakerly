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
public class QnaReply {
	private int qa_reply_no;
	private String contents;
	private Date q_datetime;
	private String code;
	private int user_no;
	private int qa_no;
}
