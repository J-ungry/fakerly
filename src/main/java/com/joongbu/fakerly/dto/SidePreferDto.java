package com.joongbu.fakerly.dto;

import lombok.Data;

/*
+----------------------+------+------+-----+---------+----------------+
| Field                | Type | Null | Key | Default | Extra          |
+----------------------+------+------+-----+---------+----------------+
| side_board_prefer_no | int  | NO   | PRI | NULL    | auto_increment |
| side_board_no        | int  | NO   | MUL | NULL    |                |
| user_no              | int  | YES  | MUL | NULL    |                |
+----------------------+------+------+-----+---------+----------------+
 */
@Data
public class SidePreferDto {
	
	private int sideBoardPreferNo;
	private int sideBoardNo;
	private int UserNo;
}
