package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.LocationDto;


@Mapper
public interface LocationMapper {

	LocationDto getLocation(String dongCode) throws SQLException;
}
