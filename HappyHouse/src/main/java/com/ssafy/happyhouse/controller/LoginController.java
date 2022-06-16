package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.LoginService;

@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
				RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
public class LoginController {

	@Autowired
	LoginService service;

	@PostMapping(value = "/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserDto dto, HttpSession session) {

		System.out.println(dto);

		UserDto userDto = service.login(dto);
		System.out.println(userDto);
		Map<String, String> map = new HashMap<>();
		if (userDto != null) {
			session.setAttribute("userDto", userDto); // 로그인 성공 시 세션에 넣음
			System.out.println("LoginController user" + userDto);

			map.put("result", "success"); // 성공 메세지
			map.put("userName", userDto.getUserName());
			map.put("userEmail", userDto.getUserEmail());
			map.put("userProfileImageUrl", userDto.getUserProfileImageUrl());
			map.put("isAdmin", Integer.toString(userDto.getIsAdmin()));
			map.put("dongCode", userDto.getDongCode()); // 추가부분(0504)
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		map.put("result", "fail");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}

	// 로그아웃 추가(0523)
	@GetMapping(value = "/logout")
	public void logout(HttpSession session) {
		System.out.println("logout");
		session.invalidate();

	}
}
