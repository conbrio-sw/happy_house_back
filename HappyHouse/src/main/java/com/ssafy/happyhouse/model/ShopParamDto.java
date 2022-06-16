package com.ssafy.happyhouse.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShopParamDto {
	private String dong;
	private String key;
	private String word;
	private ArrayList<Integer> arr;
	
	private double lat;
	private double lng;
}

