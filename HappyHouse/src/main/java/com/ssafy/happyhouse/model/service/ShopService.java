package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.ShopDto;

public interface ShopService {

	List<ShopDto> getShops(Map<String, String> map) throws SQLException;
	DongCodeDto getDongCodes(String dong) throws SQLException;
	

	List<ShopDto> MST(Map<String, String> map, ArrayList<Integer> arr, double lat, double lng);
	
	
}
