package com.ssafy.happyhouse.controller;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/server")
@CrossOrigin(
	    // localhost:5500 과 127.0.0.1 구분
	    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
	    allowCredentials = "true", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class NaverSearchApiController {

	@Value("${app.search.news.api.key}")
	String apiKey;
	
	@GetMapping("/news")
	public ResponseEntity<Map<String, String>> searchNews(){
		
		String query = "부동산";
		ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
		String encode = StandardCharsets.UTF_8.decode(buffer).toString();
		
		URI uri = UriComponentsBuilder
				.fromUriString("https://openapi.naver.com")
				.path("/v1/search/news.json")
				.queryParam("query", encode)
				.queryParam("display", 10)
				.queryParam("start", 1)
				.queryParam("sort", "sim")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-Naver-Client-Id", "l4BLZ9HggtixSVmFtvAW")
				.header("X-Naver-Client-Secret", apiKey)
				.build();
		
		Map<String, String> map = new HashMap<String, String>();
		ResponseEntity<String> resultEntity = restTemplate.exchange(req, String.class);
		map.put("result", "success");
		map.put("data", resultEntity.getBody());
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		
	}

}
