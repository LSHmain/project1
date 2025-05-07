package com.shinhan.farmer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDTO {
	private String farmer_id;	
	private String farmer_password;
	private String farmer_name;
//	private int farmer_age;
	private int farm_earnings;
	private int nonfarm_earnings;
	private int farmer_date;
	private String farmer_check;
	private int animal_earnings;
	private int facility_earnings;
	private String deposit_type;
	
	private String nonfarm_info;
	private String farmdate_info;	
	private String area_info;
	private String animal_info;
	private String facility_info;	
	private String totalarea_info; 
	private String totalmon_info;	
	
	
//	private String farmer_id;
	private String farm_type;
	private int amount; 
	private int farm_count;
	private int mem_count;
		
}


	



	
	
