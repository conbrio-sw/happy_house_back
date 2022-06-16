package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.ShopDto;
import com.ssafy.happyhouse.model.ShopParamDto;
import com.ssafy.happyhouse.model.service.ShopService;

@RestController
@CrossOrigin(
	    // localhost:5500 과 127.0.0.1 구분
	    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
	    allowCredentials = "true", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class ShopController {
	
	private final Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private ShopService service;
	
	
	@GetMapping("/shops")
	public ResponseEntity<List<ShopDto>> apt(@RequestParam Map<String, String> map) throws Exception {
		System.out.println(map);
//		Map<String, String> map = new HashMap<>();
//		map.put("key", obj.getKey());
//		map.put("word", obj.getWord());
		return new ResponseEntity<List<ShopDto>>(service.getShops(map), HttpStatus.OK);
	}
	//@RequestBody String dong, @RequestBody ArrayList<Integer> arr
	@PostMapping("/shops/MST")
	public ResponseEntity<List<ShopDto>> mst(@RequestBody ShopParamDto obj) throws Exception {
		System.out.println(obj);
		String dong = obj.getDong();
		ArrayList<Integer> arr = obj.getArr();
		System.out.println("MST : " + dong);
		System.out.println("MST : " + arr.toString());
		Map<String, String> map = new HashMap<>();
		map.put("key", obj.getKey());
		map.put("word", obj.getWord());
		map.put("dong", obj.getDong());
		System.out.println(map);
		double lat = obj.getLat();
		double lng = obj.getLng();
		return new ResponseEntity<List<ShopDto>>(service.MST(map, arr, lat, lng), HttpStatus.OK);
	}
	

}
