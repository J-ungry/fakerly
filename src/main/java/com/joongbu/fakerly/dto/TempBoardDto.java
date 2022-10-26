package com.joongbu.fakerly.dto;

import java.sql.Blob;
import java.util.Date;

import lombok.Data;

/* tempboard 테이블 구조
+------------+--------------+------+-----+-------------------+-------------------+ 
| Field      | Type         | Null | Key | Default           | Extra             |
+------------+--------------+------+-----+-------------------+-------------------+
| temp_no    | int          | NO   | PRI | NULL              | auto_increment    |
| user_no    | int          | NO   | MUL | NULL              |                   |                                      
| title      | varchar(100) | NO   |     | NULL              |                   |         
| keyword_no | int          | NO   | MUL | NULL              |                   |       
| contents   | varchar(255) | NO   |     | NULL              |                   |       
| img        | blob         | YES  |     | NULL              |                   |       
| link       | varchar(255) | YES  |     | NULL              |                   |       
| time       | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |      
+------------+--------------+------+-----+-------------------+-------------------+
*/
@Data
public class TempBoardDto {
	private int tempNo;
	private int userId;
	private String title;
	private int keywordNo;
	private String contents;
	//private Blob img;
	private String link;
	private Date dataTime;
}
