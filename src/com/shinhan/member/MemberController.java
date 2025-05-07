package com.shinhan.member;

import java.util.Scanner;

public class MemberController {
	static Scanner sc = new Scanner(System.in);
	static MemberService memService = new MemberService();
	public static void main() {
		boolean isStop = false;
		while(!isStop) {
			display();
			int member = sc.nextInt();
			switch(member) {
			case 1 -> {f_memberInsert();}
			case 90 -> {f_memberDelete();}
			case 99 -> {isStop = true;}
			}
		}
		
		
		
	}
	private static void display() {
		System.out.println("");
		System.out.println("------------------------------------------------------------");
		System.out.println("1.구성원정보 입력 | 90.구성원정보 삭제 | 99.구성원 입력 종료");
		System.out.println("------------------------------------------------------------");
		System.out.println("선택: ");
		
	}
	private static void f_memberDelete() {
		System.out.print("농민 ID>>");
		String farmer_id = sc.next();
		System.out.print("농민 password>>");
		String farmer_password = sc.next();
		System.out.print("구성원 ID>>");
		String mem_id = sc.next();
		
		MemberDTO mem = MemberDTO.builder()
				.farmer_id(farmer_id)
				.farmer_password(farmer_password)
				.mem_id(mem_id)
				.build();
		
		int result = memService.MemDelete(mem);
		MemberView.display(result+"건 삭제");
		int result2 = memService.SimpleDelete(mem);
		MemberView.display(result+"건 정보 변경");
		
	}
	private static void f_memberInsert() {
		System.out.print("농민 ID>>");
		String farmer_id = sc.next();
		int result = memService.MemberInsert(make(farmer_id));
		MemberView.display(result+"건 삽입");
		
		MemberDTO mem = MemberDTO.builder().farmer_id(farmer_id).build();
		
		int result2 = memService.SimpleUpdate(mem);
		MemberView.display(result+"건 정보추가");
		
	}
	private static MemberDTO make(String farmer_id2) {
		System.out.print("농민 ID>>");
		String farmer_id = sc.next();
		System.out.print("구성원 ID>>");
		String mem_id = sc.next();
		System.out.print("구성원 이름>>");
		String mem_name = sc.next();
		System.out.print("기본직불제 수령여부>>");
		Integer basic_target = sc.nextInt();
		System.out.print("농사일자>>");
		Integer farm_date = sc.nextInt();
		System.out.print("농사면적>>");
		Integer farm_area = sc.nextInt();
		System.out.print("농업소득>>");
		Integer farm_earnings = sc.nextInt();
		System.out.print("농업외소득>>");
		Integer nonfarm_earnings = sc.nextInt();
		System.out.print("축산소득>>");
		Integer animal_earnings = sc.nextInt();
		System.out.print("시설소득>>");
		Integer facility_earnings = sc.nextInt();
		
		MemberDTO mem = MemberDTO.builder()
				.mem_id(mem_id)
				.farmer_id(farmer_id)
				.mem_name(mem_name)
				.basic_target(basic_target)
				.farm_date(farm_date)
				.farm_earnings(farm_earnings)
				.nonfarm_earnings(nonfarm_earnings)
				.animal_earnings(animal_earnings)
				.facility_earnings(facility_earnings)
				.build();
		
		return mem;
	}
}
