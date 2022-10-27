package com.joongbu.fakerly.dto;

import lombok.Data;

/*
+---------------+------+------+-----+---------+-------+
| Field         | Type | Null | Key | Default | Extra |
+---------------+------+------+-----+---------+-------+
| side_board_no | int  | NO   | PRI | NULL    |       |
| side_role_no  | int  | NO   | PRI | NULL    |       |
+---------------+------+------+-----+---------+-------+
 */
@Data
public class SideRoleDto {
	private int sideBoardNo;
	private int sideRoleNo;
}
