package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParamDto;
import com.ssafy.happyhouse.model.BoardResultDto;
import com.ssafy.happyhouse.model.LocationDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.LocationService;

@RestController
@CrossOrigin(
	    // localhost:5500 과 127.0.0.1 구분
	    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
	    allowCredentials = "true", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class LocationController {

	@Autowired
	LocationService service;
	
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/location")
	private ResponseEntity<LocationDto> getLocation(String dongCode){
	
		LocationDto dto = service.getLocation(dongCode);
		if( dto !=null ) {
			return new ResponseEntity<LocationDto>(dto, HttpStatus.OK);
		}else {
			System.out.println("boardResultDto.getResult() : fail");
			return new ResponseEntity<LocationDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
