package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserResultDto;
import com.ssafy.happyhouse.model.service.UserService;

@RestController
@CrossOrigin(
	    // localhost:5500 과 127.0.0.1 구분
	    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
	    allowCredentials = "true", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class UserController {

	@Autowired
	UserService userService;
	
	private static final int SUCCESS = 1;
//	url 주소가 있으면  ==> 리소스
	// url 주소는 그대로고
	// http method만 바꿔서 CRUD 구현
	
	//회원가입
	//@PostMapping(value="/register")
	@PostMapping(value="/user")
	public ResponseEntity<Map<String, String>> register(@RequestBody UserDto dto, HttpSession session){
		
		UserResultDto userResultDto = userService.userRegister(dto);
		
		Map<String, String> map = new HashMap<>();
		
		if( userResultDto.getResult() == SUCCESS ) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//회원정보 조회
	//@PostMapping(value="/detail")
	@GetMapping(value="/user/{userEmail}")
	public ResponseEntity<Map<String, String>> detail(@PathVariable String userEmail, HttpSession session){
		System.out.println("email "+userEmail);
		UserDto userDto = userService.userDetail(userEmail);
		
		Map<String, String> map = new HashMap<>();
		
		if( userDto != null ) {
			map.put("result", "success"); //성공 메세지
		    map.put("userName", userDto.getUserName());
		    map.put("userPassword", userDto.getUserPassword());
		    map.put("userEmail", userDto.getUserEmail());
		    map.put("dongCode", userDto.getDongCode());
		    map.put("isAdmin", Integer.toString(userDto.getIsAdmin()));
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//회원정보 수정
	//@PutMapping(value="/update")
	@PutMapping(value="/user")
	public ResponseEntity<Map<String, String>> update(@RequestBody UserDto dto, HttpSession session){
		System.out.println("updateDto "+dto);
		UserResultDto userResultDto = userService.userUpdate(dto);
		
		Map<String, String> map = new HashMap<>();
		
		if( userResultDto.getResult() == SUCCESS ) {
			map.put("result", "success"); //성공 메세지
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//회원정보 탈퇴
	//@DeleteMapping(value="/exit")
	@DeleteMapping(value="/user")
	public ResponseEntity<Map<String, String>> delete(UserDto dto, HttpSession session){
		UserResultDto userResultDto = userService.userDelete(dto.getUserEmail());
		System.out.println("exit "+userResultDto);
		
		Map<String, String> map = new HashMap<>();
		
		if( userResultDto.getResult() == SUCCESS ) {
			map.put("result", "success"); //성공 메세지
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
