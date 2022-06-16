package com.ssafy.happyhouse.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;

@Mapper
public interface UserMapper {
	public int userRegister(UserDto userDto); //회원가입
	public UserDto userDetail(String userEmail); //회원정보 조회
	public int userUpdate(UserDto userDto); //회원정보 수정
	public int userDelete(String userEmail); //회원정보 수정
}
