package com.ssafy.happyhouse.model;

import java.util.Date;

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
public class DongCodeDto {

	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;

}
