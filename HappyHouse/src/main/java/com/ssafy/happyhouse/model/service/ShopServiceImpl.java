package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.algo.Dijkstra;
import com.ssafy.happyhouse.algo.MST;
import com.ssafy.happyhouse.model.DongCodeDto;
import com.ssafy.happyhouse.model.ShopDto;
import com.ssafy.happyhouse.model.mapper.ShopMapper;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopMapper dao;


	@Override
	public List<ShopDto> getShops(Map<String, String> map) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getShops(map);
	}





	@Override
	public List<ShopDto> MST(Map<String, String> map, ArrayList<Integer> arr, double lat, double lng) {
		List<ShopDto> tempList = null;
		//System.out.println("MST 서비스 중 ---" + map);
		try {
			tempList = dao.getShops(map);
		} catch (SQLException e) {
			System.out.println("MST 오류 발생");
			e.printStackTrace();
		}
		ShopDto marker = new ShopDto();
		marker.setLat(lat);
		marker.setLng(lng);
		//System.out.println("MST 서비스 중 ---" + arr.size() + " / " + tempList.size());
		List<ShopDto> beforeList = new ArrayList<ShopDto>();
		beforeList.add(marker);
		for(int i = 0; i < arr.size(); i++) {
			beforeList.add(tempList.get(arr.get(i)));
		}
		try {
			List<ShopDto> afterList = new ArrayList<ShopDto>();
			List<Integer> mst = MST.mst(beforeList);
			
			for(int i = 0; i < beforeList.size(); i++) {
				afterList.add(beforeList.get(mst.get(i)));
			}
			afterList.remove(0);
			return afterList;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public DongCodeDto getDongCodes(String dong) throws SQLException {
		
		DongCodeDto dongCodeDto = dao.getDongCodes(dong);
		return dongCodeDto;
		
	}
	
	

}
