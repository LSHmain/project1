package com.shinhan.farmer;

import java.util.List;
import java.util.Map;



public class FarmerView {
	//여러건 출력
		public static void display(List<FarmerDTO> farm) {
			if(farm.size() == 0 ) {
				display("해당하는 직업이 존재하지 않습니다.");
				return;
			}
			farm.stream().forEach(emp -> System.out.println(emp));
		}
		
		public static void display(FarmerDTO farm) {
			if(farm == null) {
				display("해당 아이디가 존재하지 않습니다.");
				return;
			}
			System.out.println(farm);
		}
		
		
		public static void display(String message) {
//			System.out.println("--------메세지 정보-------");
			System.out.println(message);
		}

		public static void display(Map<String, String> result_detail) {
			System.out.println(result_detail);
			
		}
}
