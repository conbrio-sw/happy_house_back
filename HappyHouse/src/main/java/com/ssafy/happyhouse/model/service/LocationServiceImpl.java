package com.ssafy.happyhouse.model.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardFileDto;
import com.ssafy.happyhouse.model.BoardParamDto;
import com.ssafy.happyhouse.model.BoardResultDto;
import com.ssafy.happyhouse.model.LocationDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.mapper.LocationMapper;

	@Service
	public class LocationServiceImpl implements LocationService {
	
		@Autowired
		LocationMapper dao;
		
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public LocationDto getLocation(String dongCode) {
		try {
			LocationDto dto = dao.getLocation(dongCode);
			System.out.println("LocationService = " + dto);
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}