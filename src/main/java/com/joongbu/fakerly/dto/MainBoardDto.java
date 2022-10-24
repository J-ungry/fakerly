package com.joongbu.fakerly.dto;

import java.sql.Blob;
import java.util.Date;

import lombok.Data;

/* mainboard 테이블 구조
+--------------------+--------------+------+-----+-------------------+-------------------+
| Field              | Type         | Null | Key | Default           | Extra             |
+--------------------+--------------+------+-----+-------------------+-------------------+
| mainboard_no       | int          | NO   | PRI | NULL              | auto_increment    |
| user_no            | int          | NO   | MUL | NULL              |                   |
| mainboard_title    | varchar(100) | NO   |     | NULL              |                   |
| keyword_no         | int          | NO   | MUL | NULL              |                   |
| mainboard_contents | varchar(255) | NO   |     | NULL              |                   |
| mainboard_view     | int          | YES  |     | NULL              |                   |
| mainboard_img      | blob         | YES  |     | NULL              |                   |
| mainboard_link     | varchar(255) | YES  |     | NULL              |                   |
| mainboard_time     | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
+--------------------+--------------+------+-----+-------------------+-------------------+
* */

@Data
public class MainBoardDto {
	private int mainboardNo;
	private int userNo;
	private String mainboardTitle;
	private int keywordNo;
	private String mainboardContents;
	private int mainboardView;
	private Blob mainboardImg;
	private String mainboardLink;
	private Date mainboardTime;
}
