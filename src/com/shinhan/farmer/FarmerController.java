package com.shinhan.farmer;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.shinhan.member.MemberController;
import com.shinhan.member.MemberDTO;
import com.shinhan.member.MemberView;

public class FarmerController {
	
	static Scanner sc = new Scanner(System.in);
	static FarmerService farmService = new FarmerService();
	
	public static void main(String[] args) {
		boolean isStop = false;
		while(!isStop) {
			display();
			int check = sc.nextInt();
			switch(check) {
			case 1 -> {f_fInsert();}
			case 2 -> {f_fdInsert();}
			case 3 -> {m_memInfo();}
			case 4 -> {f_farmerInfo();}
			case 5 -> {f_farmerDetailInfo();}
			case 10 -> {f_fInfo();}
			case 90 -> {f_fDetailDelete();}
			case 91 -> {f_allDelete();}
			case 99 -> {isStop = true;}
			}
		}
		System.out.println("");
		System.out.println("이용해주셔서 갑사합니다.");
	}
	
	private static void display() {
		
		System.out.println("");
		System.out.println("------------------------------------------------------------");
		System.out.println("1.농민기본정보 입력 | 2.농민 추가 농업 정보 입력 | 3.구성원 정보 입력");
		System.out.println("4.농민기본정보 조회 | 5.농민 추가정보 조회 | 10.직불금 정보확인");
		System.out.println("90.농민 추가정보 삭제 | 91.계정삭제 | 99.종료");
		System.out.println("------------------------------------------------------------");
		System.out.print("선택: ");
	}


	private static void f_fDetailDelete() {
		System.out.print("농민 ID>>");
		String farmer_id = sc.next();
		System.out.print("종류>>");
		String farm_type = sc.next();
		
		FarmerDTO total = FarmerDTO.builder()
				.farmer_id(farmer_id)
				.farm_type(farm_type)
				.build();
		
		int result = farmService.farmerDetailDelete(total);
		MemberView.display(result+"삭제 진행중");
		int result2 = farmService.SimpleDelete(total);
		int result1 = farmService.updateFarmCheck(total);
		MemberView.display(result+"수정");
		
		
	}

	private static void f_farmerDetailInfo() {
		System.out.print("농민 ID>>");
		String farmer_id = sc.next();
		
		
		List<FarmerDTO> result = FarmerService.farmerDetailInfo(farmer_id);
		
		for (FarmerDTO dto : result) {
		    System.out.println("farm_type = " + dto.getFarm_type() + ", amount = " + dto.getAmount());
		}
		
	}




	private static void f_farmerInfo() {
		System.out.print("농민 ID>>");
		String farmer_id = sc.next();
		System.out.print("농민 password>>");
		String farmer_password = sc.next();
		
		FarmerDTO total = FarmerDTO.builder()
				.farmer_id(farmer_id)
				.farmer_password(farmer_password)
				.build();
		
		Map<String, String> result = FarmerService.farmerInfo(total);
		
		FarmerView.display(result);
		
	}




	




	private static void f_allDelete() {
		System.out.print("농민 ID>>");
		String farmer_id = sc.next();
		System.out.print("농민 password>>");
		String farmer_password = sc.next();
		
		FarmerDTO total = FarmerDTO.builder()
				.farmer_id(farmer_id)
				.farmer_password(farmer_password)
				.build();
		
		int result = farmService.memDelete(total);
		MemberView.display(result+"삭제 진행중");
		
		int result0 = farmService.farmerDetailDelete(total);
		MemberView.display(result0+"삭제 진행중");
		
		int result1 = farmService.depositDelete(total);
		MemberView.display(result1+"삭제 진행중");
		
		int result2 = farmService.farmerDelete(total);
		MemberView.display(result2+"삭제 완료");		
	}

	private static void m_memInfo() {
		MemberController.main();
	}




	private static void f_fInfo() {
		System.out.print("ID 입력>>");
		String farmer_id = sc.next();
		System.out.print("Password 입력>>");
		String farmer_password = sc.next();
		FarmerDTO farm = FarmerDTO.builder()
				.farmer_id(farmer_id)
				.farmer_password(farmer_password)
				.build();
		int result = FarmerService.InfoUpdate(farm);
//		FarmerView.display(result+"건 업데이트됨");
		Map<String, String> result2 = FarmerService.InfoSelect(farm);
		FarmerView.display(result2+"");
		
		
		boolean isStop = false;
		while(!isStop){
			System.out.println("------------------------------------------------------------");
			System.out.println("1번: 소농직불금 충족 여부 확인 | 2번: 면적직불금 수당 최대치 | 3번: 나가기");
			System.out.print("작업을 선택하세요>>");
			int check = sc.nextInt();
			switch(check) {
			case 1 -> {
				System.out.println("");
				System.out.println("--------------------------------소농직불금 결정 정보----------------------------------");
				System.out.println("");
				Map<String, String> result_detail = FarmerService.InfoDetailSelect(farm);
				FarmerView.display(result_detail);
			}
			case 2 -> {
				System.out.println("");
				System.out.println("--------------------------------면적직불금 금액 정보-----------------------------------");
				System.out.println("************면적직불금 계산 금액은 농업진흥지역의 논과 밭의 면적 구해 최대치 계산하였습니다***********");
				System.out.println("");
				FarmerDTO farm1 = FarmerDTO.builder()
						.farmer_id(farmer_id)
						.build();
				Map<String, String> result_detail = FarmerService.CountMoney(farm1);
				FarmerView.display(result_detail);
			}
			case 3 -> {isStop = true;}
			}
			
		}
	}

	private static void f_fdInsert() {
		System.out.print("농민아이디>>");
		String farmer_id = sc.next();
		System.out.print("농민패스워드>>");
		String farmer_password = sc.next();
		System.out.println("축산의 경우 세부적인 가축을 입력해주시기 바랍니다.");
		System.out.print("농업종류(논,밭,축산,시설)>>");
		String farm_type = sc.next();
		System.out.print("면적 및 수량>>");
		int amount = sc.nextInt();
		
		FarmerDTO farm = FarmerDTO.builder()
				.farmer_id(farmer_id)
				.farm_type(farm_type)
				.amount(amount)
				.farmer_password(farmer_password)
				.build();
		
		
		int result = FarmerService.farmDetailInsert(farm);
		int result1 = FarmerService.SimpleUpdate(farm);
		FarmerView.display(result+"건 삽입");
		FarmerView.display(result1+"건 삽입");
	}

	private static void f_fInsert() {
		System.out.print("아이디>>");
		String farmer_id = sc.next();
		
		
		int result = FarmerService.farmInsert(make(farmer_id));
		
		FarmerView.display(result + "명의 고객의 정보 삽입");
	}
	

	static FarmerDTO make(String id) {
		String farmer_id = id;
		System.out.print("패스워드>>");
		String farmer_password = sc.next();
		System.out.print("이름>>");
		String farmer_name = sc.next();
		System.out.print("농업종사기간(단위/년)>>");
		Integer farmer_date = sc.nextInt();
		System.out.print("농업소득(단위/만)>>");
		Integer farm_earnings = sc.nextInt();
		System.out.print("농업외소득(단위/만)>>");
		Integer nonfarm_earnings = sc.nextInt();
		System.out.print("축산업소득(단위/만)>>");
		Integer animal_earnings = sc.nextInt();
		System.out.print("시설소득(단위/만)>>");
		Integer facility_earnings = sc.nextInt();
		
		FarmerDTO farm = FarmerDTO.builder()
				.farmer_id(farmer_id)
				.farmer_password(farmer_password)
				.farmer_name(farmer_name)
				.farm_earnings(farm_earnings)
				.nonfarm_earnings(nonfarm_earnings)
				.farmer_date(farmer_date)
				.animal_earnings(animal_earnings)
				.facility_earnings(facility_earnings)
				.build();
		return farm;
		
	}
	
	
}
