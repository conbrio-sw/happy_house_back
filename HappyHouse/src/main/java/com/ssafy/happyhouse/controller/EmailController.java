package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.EmailService;

@RestController
@CrossOrigin(
	    // localhost:5500 과 127.0.0.1 구분
	    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
	    allowCredentials = "true", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/emailConfirm")
    public ResponseEntity<Map<String, String>> emailConfirm(@RequestBody String email) throws Exception {
 
		System.out.println("confirm Email");
        String confirm = emailService.sendSimpleMessage(email);
        System.out.println(confirm);
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "success");
        map.put("confirm", confirm);
 
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }

}
