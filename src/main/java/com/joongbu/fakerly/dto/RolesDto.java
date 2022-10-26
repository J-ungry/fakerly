package com.joongbu.fakerly.dto;

import lombok.Data;

/*
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| side_role_no | int          | NO   | PRI | NULL    | auto_increment |
| role_name    | varchar(100) | NO   |     | NULL    |                |
+--------------+--------------+------+-----+---------+----------------+
 */
@Data
public class RolesDto {
	private  int sideRoleNo;
	private String roleName;
	
}
