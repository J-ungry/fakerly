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
| keyword_no | int          | YES  | MUL | NULL              |                   |       
| contents   | varchar(255) | NO   |     | NULL              |                   |       
<<<<<<< HEAD
| img        | varchar(255)         | YES  |     | NULL              |                   |       
=======
| img        | varchar(255) | YES  |     | NULL              |                   |       
>>>>>>> 8cfbc26ab9253405526f24ffa2d8cba4189ab677
| link       | varchar(255) | YES  |     | NULL              |                   |       
| time       | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |      
+------------+--------------+------+-----+-------------------+-------------------+
*/
@Data
public class TempBoardDto {
	private int tempNo;
	private int userNo;
	private String title;
	private int keywordNo;
	private String contents;

	private String img;
	private String link;
	private Date time;
	
	
	private String userName;
	private String userCompany;
	private String keywordName;
}