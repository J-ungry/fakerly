package com.joongbu.fakerly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.fakerly.dto.UserDto;

@Mapper
public interface UserMapper {

	List<UserDto> list();
}
