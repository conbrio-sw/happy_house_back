package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserResultDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
	public UserDto userDetail(String userEmail);
	public UserResultDto userUpdate(UserDto dto);
	public UserResultDto userDelete(String userEmail);
}
