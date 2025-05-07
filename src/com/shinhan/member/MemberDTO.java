package com.shinhan.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private String mem_id; 	
	private String farmer_id;	
	private String mem_name;
	private int basic_target;	
	private int farm_date;
	private int farm_area;	
	private int farm_earnings;
	private int nonfarm_earnings;
	private int animal_earnings;
	private int facility_earnings;
	
	
	private String farmer_password;

}
