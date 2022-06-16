package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.ShopDto;

@Mapper
public interface ShopMapper {
	
	List<ShopDto> getShops(Map<String, String> map) throws SQLException;
	DongCodeDto getDongCodes(String dong) throws SQLException;
	
}
