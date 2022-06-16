package com.ssafy.happyhouse.model;

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
public class ShopDto {
	private String shopName;
	private String shopKind;
	private String sidoCode;
	private String gugunCode;
	private String dongCode;
	private String roadAddress;
	private double lat;
	private double lng;
}

