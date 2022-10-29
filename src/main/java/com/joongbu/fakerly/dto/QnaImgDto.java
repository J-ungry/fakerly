package com.joongbu.fakerly.dto;
//+-----------+--------------+------+-----+---------+----------------+
//| Field     | Type         | Null | Key | Default | Extra          |
//+-----------+--------------+------+-----+---------+----------------+
//| qa_img_no | int          | NO   | PRI | NULL    | auto_increment |
//| img_path  | varchar(255) | YES  |     | NULL    |                |
//| qa_no     | int          | NO   | MUL | NULL    |                |
//+-----------+--------------+------+-----+---------+----------------+
//3 rows in set (0.02 sec)

public class QnaImgDto {
	private int qaImgNo;
	private String img_path;
	private QnaDto qna;
}
