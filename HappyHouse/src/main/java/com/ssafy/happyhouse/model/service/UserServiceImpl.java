package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserResultDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if( userDao.userRegister(userDto) == 1 ) {
			System.out.println("signup 성공");
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}

	@Override
	public UserDto userDetail(String userEmail) {
		UserDto userDto = userDao.userDetail(userEmail);
		return userDto;
	}
	
	@Override
	public UserResultDto userUpdate(UserDto dto) {
		UserResultDto userResultDto = new UserResultDto();
		if( userDao.userUpdate(dto) == 1 ) {
			System.out.println("update 성공");
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}
	
	@Override
	public UserResultDto userDelete(String userEmail) {
		UserResultDto userResultDto = new UserResultDto();
		
		if( userDao.userDelete(userEmail) == 1 ) {
			System.out.println("delete 성공");
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}
	
}
